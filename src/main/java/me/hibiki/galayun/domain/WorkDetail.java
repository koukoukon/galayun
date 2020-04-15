package me.hibiki.galayun.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 作业详细信息实体类 用于向客户端返回json
 *
 * @author 高弘昆
 * @date 2020/3/26 22:34
 */
@Data
public class WorkDetail implements Serializable {
    /**
     * 注解@SerializedName value用于指定序列化后的字段名  alternate用于反序列化传参
     * objectiveQuestions : 客观题集合
     * subjectiveQuestions : 主观题集合
     */
    @Expose
    @SerializedName(value = "objectives", alternate = {"objs"})
    private List<Objective> objectives;
    @Expose
    @SerializedName(value = "subjectives", alternate = {"subs"})
    private List<Subjective> subjectives;
}
