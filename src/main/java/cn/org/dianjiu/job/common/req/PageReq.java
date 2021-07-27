package cn.org.dianjiu.job.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value="分页入参对象",description="分页入参对象")
public class PageReq<T> implements Serializable {
    /**
     * 生成的serialVersionUID
     */
    private static final long serialVersionUID = 5231134212346077681L;

    @ApiModelProperty(value="当前页")
    @NotEmpty(message = "当前页不能为空")
    private int pageNum;
    @ApiModelProperty(value="页大小")
    @NotEmpty(message = "页大小不能为空")
    private int pageSize;
    @ApiModelProperty("其他查询条件")
    private T data;

    public PageReq() {
    }

    public PageReq(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageReq(int pageNum, int pageSize, T data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
    }
}
