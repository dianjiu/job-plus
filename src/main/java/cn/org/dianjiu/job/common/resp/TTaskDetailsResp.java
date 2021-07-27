package cn.org.dianjiu.job.common.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 定时任务信息表(TTaskDetailsResp) Resp
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TTaskDetailsResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("ID")
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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date nextExecuteTime;
    @ApiModelProperty("创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("修改人")
    private String updator;
    @ApiModelProperty("修改时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}