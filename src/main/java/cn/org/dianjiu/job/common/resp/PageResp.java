package cn.org.dianjiu.job.common.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by limengwei on 2019-11-25
 **/
@Getter
@Setter
@ApiModel(value="分页响应对象",description="分页响应对象")
public class PageResp<T> implements Serializable {
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 5231134212346077681L;

    @ApiModelProperty("当前页")
    private int pageNum;
    @ApiModelProperty("页大小")
    private int pageSize;
    @ApiModelProperty("总条数")
    private long total;
    @ApiModelProperty("总页数")
    private int pages;
    @ApiModelProperty("结果集")
    private T data;
}
