package cn.org.dianjiu.job.common.req;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 定时任务出错现场信息表(TTaskErrorsReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TTaskErrorsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("Id")
    private Integer id;
    @ApiModelProperty("任务执行记录Id")
    private String taskexecuterecordid;
    @ApiModelProperty("信息关键字")
    private String errorkey;
    @ApiModelProperty("信息内容")
    private String errorvalue;

}