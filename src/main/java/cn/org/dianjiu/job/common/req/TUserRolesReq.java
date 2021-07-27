package cn.org.dianjiu.job.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (TUserRolesReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TUserRolesReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("用户角色对照ID")
    private Integer id;
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("角色ID")
    private Integer roleId;

}