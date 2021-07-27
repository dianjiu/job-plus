package cn.org.dianjiu.job.common.resp;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 定时任务出错现场信息表(TTaskErrorsResp) Resp
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TTaskErrorsResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("任务执行记录Id")
    private String taskexecuterecordid;
    @ApiModelProperty("信息关键字")
    private String errorkey;
    @ApiModelProperty("信息内容")
    private String errorvalue;
    @ApiModelProperty("创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("修改人")
    private String updator;
    @ApiModelProperty("修改时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}