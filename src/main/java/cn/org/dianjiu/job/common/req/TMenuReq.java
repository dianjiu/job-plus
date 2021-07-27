package cn.org.dianjiu.job.common.req;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (TMenuReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:33
 */
@Data
public class TMenuReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("菜单ID")
    private Integer id;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("Controller路径")
    private String menuUrl;
    @ApiModelProperty("菜单编码")
    private String menuCode;
    @ApiModelProperty("父菜单ID")
    private Integer parentId;
    @ApiModelProperty("菜单类型：0-菜单1-按钮")
    private Integer menuType;
    @ApiModelProperty("显示序号")
    private Integer orderNum;
    @ApiModelProperty("删除状态：0-存在1-已删除")
    private Integer deleted;

}