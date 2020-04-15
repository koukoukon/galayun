package me.hibiki.galayun.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 作业实体类扩展 用于向客户端返回json
 *
 * @author 高弘昆
 * @date 2020/3/26 17:52
 */
@Data
public class WorkExtend implements Serializable {
    /**
     * 作业集合
     */
    @Expose
    @SerializedName(value = "works", alternate = {"rows"})
    private List<Work> works;
    /**
     * 作业总记录条数
     */
    @Expose
    @SerializedName(value = "count", alternate = {"total"})
    private int count;
}
