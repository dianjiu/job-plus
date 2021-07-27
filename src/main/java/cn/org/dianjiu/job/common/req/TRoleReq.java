package cn.org.dianjiu.job.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (TRoleReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TRoleReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("角色id")
    private Integer id;
    @ApiModelProperty("角色名称")
    private String roleName;

}