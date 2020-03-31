package me.hibiki.galayun.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 主观题实体类
 * 注解 @SerializedName value用于指定序列化后的字段名  alternate用于反序列化传参
 *
 * @author 高弘昆
 * @date 2020/3/26 20:17
 */
@Data
public class SubjectiveQuestion implements Serializable {
    /**
     * 主观题id
     */
    @Expose
    private Integer subjectiveId;
    /**
     * 主观题标题
     */
    @SerializedName(value = "subjectiveTitle", alternate = {"Title"})
    private String subjectiveTitle;

    /**
     * 主观题解析类型
     */
    @SerializedName(value = "subjectiveParsingType", alternate = {"ParsingType"})
    private Integer subjectiveParsingType;

    /**
     * 主观题解答
     */
    @SerializedName(value = "subjectiveParsing", alternate = {"Parsing"})
    private String subjectiveParsing;

    /**
     * 章节id
     */
    @Expose
    private Integer chapterId;

}
