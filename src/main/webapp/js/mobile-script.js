var copy = null;
var saveKeyword = null;
$(function () {
    $.ajaxSetup({
        url: "work",
        type: "post",
        dataType: "json"
    });
    $("ul.nav > li > a").click(function () {
        var id = $(this).attr("id");
        if ($(this).parent().hasClass("active")) {
            return;
        } else {
            $(this).parent().siblings().removeClass("active");
            $(this).parent().addClass("active");
        }
        switch (id) {
            case "homePage":
                $("div.jumbotron").html("");
                break;
            case "zysy":
                requestWorkList(238);
                break;
            case "spring":
                requestWorkList(254);
                break;
            case "mybatis":
                requestWorkList(253);
                break;
            case "springMvc":
                requestWorkList(252);
                break;
            default:
        }
    });
    $(document).on("click", ".jumbotron>pre", function () {
        var $this = $(this);
        if ($this.next("div").html() != "") {
            $this.next("div").slideToggle("fast");
            return;
        }
        var chapterId = $(this).children("p").attr("id");
        $.ajax({
            data: {
                "handler": "getWorkDetail",
                "chapterId": chapterId
            },
            success: function (data) {
                var html = parseWorkDetailJson(data);
                $this.next("div").append(html);
            }
        })
    });
    $("form").submit(function () {
        $(this).parent().siblings().remove("active");
        $(this).parent().siblings("#homePage").addClass("active");
        var keyword = $("#keyword").val();
        if (keyword.length == 0) {
            alert("请输入关键字后在进行搜索");
            return false;
        }
        if (keyword.length < 2) {
            alert("搜索关键字最少为两个字符");
            return false;
        }
        if (keyword == saveKeyword) {
            alert("请不要重复进行搜索");
            return false;
        }
        $.ajax({
            data: {
                "handler": "searchWorkDetail",
                "keyword": keyword
            },
            success: function (data) {
                if (data["objectives"].length == 0 && data["subjectives"].length == 0) {
                    alert("没有搜索到题目")
                    return false;
                }
                var html = parseWorkDetailJson(data);
                $(".jumbotron").html(html);
            }
        });
        saveKeyword = keyword;
        return false;
    });
    $(document).on("mouseover", ".title", function () {
        $(this).children("div.copy").css({"display": "block"})
    });
    $(document).on("mouseleave", ".title", function () {
        $(this).children("div.copy").css({"display": "none"})
    });
    var clipboard = new ClipboardJS('.copyM', {
        // 通过target指定要复印的节点
        text: function (trigger) {
            copy = trigger;
            return $(trigger).parent().next("pre.correct").children("input").val();
        }
    });
    clipboard.on('success', function (e) {
        $(copy).html("Copied!");
        setTimeout(function () {
            $(copy).html("Copy");
        }, 500)
    });

    clipboard.on('error', function (e) {
        console.log("Error");
    });
});

function requestWorkList(subjectId) {
    $.ajax({
        data: {
            "handler": "getWorkList",
            "subjectId": subjectId
        },
        success: function (data) {
            var html = "";
            for (var i in data) {
                if (i == "works") {
                    for (var j in data[i]) {
                        if (j < 9) {
                            html += "<pre class='work'><p id='" + data[i][j]["chapterId"] + "'>第" + (parseInt(j) + 1) + "次作业&nbsp;&nbsp;&nbsp;&nbsp;" + data[i][j]["chapterName"];
                            html += "</p></pre><div></div>"
                        } else {
                            html += "<pre class='work'><p id='" + data[i][j]["chapterId"] + "'>第" + (parseInt(j) + 1) + "次作业&nbsp;&nbsp;&nbsp;" + data[i][j]["chapterName"];
                            html += "</p></pre><div></div>"
                        }
                    }
                }
            }
            $("div.jumbotron").html(html);
        }
    })
}

function parseWorkDetailJson(data) {
    var html = "";
    for (var i in data) {
        if (i == "objectives") {
            html += "<div id='objectiveQuestion'>";
            html += "<div class='objective'>客观题</div>";
            if (data[i].length==0){
                html +="<div>客观题为空</div>"
            }else {
                html += "<div id='objectives'>";
                for (var j in data[i]) {
                    html += "<pre class='title'>" + (parseInt(j) + 1) + "." + data[i][j]["objectiveTitle"] + "</pre>";
                    html += "<pre>";
                    switch (data[i][j]["objectiveAnswer"]) {
                        case "1":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionA"] + "</div></div>";
                            break;
                        case "2":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionB"] + "</div></div>";
                            break;
                        case "3":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionC"] + "</div></div>";
                            break;
                        case "4":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionD"] + "</div></div>";
                            break;
                        case "1、2":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionA"] + "</div></div>";
                            html += "<div class='items'><div class='option correct'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                            break;
                        case "1，3":
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionA"] + "</div></div>";
                            html += "<div class='items'><div class='option correct'>" + data[i][j]["objectiveOptionC"] + "</div></div>";
                            break;
                    }
                    html += "</pre>";
                }
                html += "</div>";
                html += "</div>";
                html += "</div>";
            }
        } else if (i == "subjectives") {
            html += "<div id='subjectiveQuestion'>";
            html += "<div class='subjective'>主观题</div>";
            html += "<div id='subjectives'>";
            for (j in data[i]) {
                html += "<pre clas s='title'>" + (parseInt(j) + 1) + "." + data[i][j]["subjectiveTitle"] + "<div class='copyM glyphicon glyphicon-copy'>Copy</div></pre>";
                html += "<pre class='correct'>" + data[i][j]["subjectiveParsing"] + "<input type='hidden' value='" + data[i][j]["subjectiveParsing"] + "'></pre>";
            }
            html += "</div>";
            html += "</div>";
            html += "</div>";
        }
    }
    return html;
}