package cn.org.dianjiu.job.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Auther: Point9
 * @Date: 2020/1/14 21:44
 */
@Getter
@Setter
public class RespVO<T> implements Serializable {

    private static final long serialVersionUID = 2914853172898904181L;

    @ApiModelProperty("响应编码")
    private String code;
    @ApiModelProperty("提示信息")
    private String msg;
    @ApiModelProperty("响应数据")
    private T data;

    public RespVO() {
    }

    public RespVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
