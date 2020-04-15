var copy = null;
var saveKeyword = null;
$(function () {
    //ajax全局设置
    $.ajaxSetup({
        url: "work",
        type: "post",
        dataType: "json"
    });
    /**
     * 点击导航栏时 ajax异步加载作业列表
     */
    $("ul.nav > li > a").click(function () {
        if ($(this).parent().hasClass("active")) {
            return;
        } else {
            $(this).parent().siblings().removeClass("active");
            $(this).parent().addClass("active");
        }
        var id = $(this).attr("id");
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
    /**
     * 点击作业列表时加载作业详细信息
     */
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
        $(copy).html("复制答案");
    });
    /**
     * 粘贴板
     */
    var clipboard = new ClipboardJS('.copy', {
        text: function (trigger) {
            copy = trigger;
            return $(trigger).parent().next("pre.correct").children("input").val();
        }
    });

    clipboard.on('success', function (e) {
        $(copy).html("已复制到粘贴板");
    });

    clipboard.on('error', function (e) {
        console.log("发生错误 复制失败");
    });
});

/**
 * 请求作业列表
 * @param subjectId
 */
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
                            html += "<pre class='work'><p id='" + data[i][j]["chapterId"] + "'>第" + (parseInt(j) + 1) + "作业&nbsp;&nbsp;&nbsp;" + data[i][j]["chapterName"];
                            html += "</p></pre><div></div>"
                        }
                    }
                }
            }
            $("div.jumbotron").html(html);
        }
    })
}

/**
 * 解析作业详细信息json
 * @param data
 * @returns {string}
 */
function parseWorkDetailJson(data) {
    var html = "";
    for (var i in data) {
        if (i == "objectives") {
            html += "<div id='objectiveQuestion'>";
            html += "<div class='objective'>客观题</div>";
            html += "<div id='objectives'>";
            for (var j in data[i]) {
                html += "<pre class='title'>" + (parseInt(j) + 1) + "." + data[i][j]["objectiveTitle"] + "</pre>";
                html += "<pre>";
                switch (data[i][j]["objectiveAnswer"]) {
                    case "1":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                    case "2":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                    case "3":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                    case "4":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                    case "1、2":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                    case "1，3":
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "A." + data[i][j]["objectiveOptionA"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "B." + data[i][j]["objectiveOptionB"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option correct'>" + "C." + data[i][j]["objectiveOptionC"] + "</div></div>";
                        html += "<div class='items col-lg-3 col-md-3 col-sm-3 col-xs-3'><div class='option'>" + "D." + data[i][j]["objectiveOptionD"] + "</div></div>";
                        break;
                }
                html += "</pre>";
            }
            html += "</div>";
            html += "</div>";
            html += "</div>";
        } else if (i == "subjectives") {
            html += "<div id='subjectiveQuestion'>";
            html += "<div class='subjective'>主观题</div>";
            html += "<div id='subjectives'>";
            for (j in data[i]) {
                html += "<pre class='title'>" + (parseInt(j) + 1) + "." + data[i][j]["subjectiveTitle"] + "<div class='copy glyphicon glyphicon-copy'>复制答案</div></pre>";
                html += "<pre class='correct'>" + data[i][j]["subjectiveParsing"] + "<input type='hidden' value='" + data[i][j]["subjectiveParsing"] + "'></pre>";
            }
            html += "</div>";
            html += "</div>";
            html += "</div>";
        }
    }
    return html;
}


