package me.hibiki.galayun.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 客观题实体类
 * 注解 @SerializedName value用于指定序列化后的字段名  alternate用于反序列化传参
 *
 * @author 高弘昆
 * @date 2020/3/26 20:17
 */
@Data
public class Objective implements Serializable {


//    /**
//     * 客观题id
//     */
//    @Expose
//    private Integer objectiveId;

    /**
     * 客观题id
     */
    @Expose
    private Integer objectiveId;


    /**
     * 客观题标题
     */
    @SerializedName(value = "objectiveTitle", alternate = {"Title"})
    private String objectiveTitle;

    /**
     * 客观题选项A
     */
    @SerializedName(value = "objectiveOptionA", alternate = {"Item1"})
    private String objectiveOptionA;


//    /**
//     * 客观题选项A图片
//     */
//    @SerializedName(value = "objectiveOptionImageA", alternate = {"Image1"})
//    private String objectiveOptionImageA;

    /**
     * 客观题选项A图片
     */
    @SerializedName(value = "objectiveOptionImageA", alternate = {"Image1"})
    private String objectiveOptionImageA;


    /**
     * 客观题选项B
     */
    @SerializedName(value = "objectiveOptionB", alternate = {"Item2"})
    private String objectiveOptionB;


//    /**
//     * 客观题选项B图片
//     */
//    @SerializedName(value = "objectiveOptionImageB", alternate = {"Image2"})
//    private String objectiveOptionImageB;

    /**
     * 客观题选项B图片
     */
    @SerializedName(value = "objectiveOptionImageB", alternate = {"Image2"})
    private String objectiveOptionImageB;


    /**
     * 客观题选项C
     */
    @SerializedName(value = "objectiveOptionC", alternate = {"Item3"})
    private String objectiveOptionC;


//    /**
//     * 客观题选项C图片
//     */
//    @SerializedName(value = "objectiveOptionImageC", alternate = {"Image3"})
//    private String objectiveOptionImageC;

    /**
     * 客观题选项C图片
     */
    @SerializedName(value = "objectiveOptionImageC", alternate = {"Image3"})
    private String objectiveOptionImageC;


    /**
     * 客观题选项D
     */
    @SerializedName(value = "objectiveOptionD", alternate = {"Item4"})
    private String objectiveOptionD;


//    /**
//     * 客观题选项D图片
//     */
//    @SerializedName(value = "objectiveOptionImageD", alternate = {"Image4"})
//    private String objectiveOptionImageD;

    /**
     * 客观题选项D图片
     */
    @SerializedName(value = "objectiveOptionImageD", alternate = {"Image4"})
    private String objectiveOptionImageD;


    /**
     * 客观题解答
     */
    @SerializedName(value = "objectiveAnswer", alternate = {"Answer"})
    private String objectiveAnswer;


//    /**
//     * 章节id
//     */
//    @Expose
//    private Integer chapterId;

    /**
     * 章节id
     */
    @Expose
    private Integer chapterId;


}
