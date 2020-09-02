package cn.org.dianjiu.task.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (TUserReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TUserReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}