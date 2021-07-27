package cn.org.dianjiu.job.common.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TUserResp) Resp
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TUserResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("创建时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("修改人")
    private String updator;
    @ApiModelProperty("修改时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}