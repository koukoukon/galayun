package me.hibiki.galayun.domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 作业实体类
 *
 * @author 高弘昆
 * @date 2020/3/26 17:54
 */
@Data
public class Work implements Serializable {
    /**
     * 作业id
     */
    @Expose(serialize = false)
    @SerializedName(value = "workId", alternate = {"Id"})
    private Integer workId;
    /**
     * 班级名称
     */
    @Expose(serialize = false)
    @SerializedName(value = "className", alternate = {"ClassId"})
    private String className;
    /**
     * 第几次作业
     */
    @Expose(serialize = false)
    @SerializedName(value = "sortId", alternate = {"SortId"})
    private String sortId;
    /**
     * 章节id
     */
    @Expose
    @SerializedName(value = "chapterId", alternate = {"ChapterId"})
    private Integer chapterId;
    /**
     * 章节名称
     */
    @Expose
    @SerializedName(value = "chapterName", alternate = {"ChapterName"})
    private String chapterName;
    /**
     * 科目名称
     */
    @Expose(serialize = false)
    @SerializedName(value = "subjectName", alternate = {"SubjectName"})
    private String subjectName;
    /**
     * 是否布置
     */
    @Expose(serialize = false)
    @SerializedName(value = "isAssign", alternate = {"IsAssign"})
    private String isAssign;
    /**
     * 科目id
     */
    @Expose(serialize = false)
    private Integer subjectId;
}
