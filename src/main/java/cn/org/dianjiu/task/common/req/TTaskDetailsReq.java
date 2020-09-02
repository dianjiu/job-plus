package cn.org.dianjiu.task.common.req;

import java.util.Date;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 定时任务信息表(TTaskDetailsReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TTaskDetailsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("任务id")
    private Integer id;
    @ApiModelProperty("任务编号")
    private String taskNo;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("分组编号")
    private String groupNo;
    @ApiModelProperty("分组名称")
    private String groupName;
    @ApiModelProperty("任务描述")
    private String taskDesc;
    @ApiModelProperty("CRON表达式")
    private String cornRule;
    @ApiModelProperty("请求方式")
    private String sendType;
    @ApiModelProperty("请求地址")
    private String sendUrl;
    @ApiModelProperty("请求参数")
    private String sendParam;
    @ApiModelProperty("任务状态 0-停用 1-启用")
    private String status;
    @ApiModelProperty("下次执行时间")
    private Date nextExecuteTime;

}