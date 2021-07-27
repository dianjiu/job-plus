package cn.org.dianjiu.job.common.resp;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (TMenuResp) Resp
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:33
 */
@Data
public class TMenuResp implements Serializable {
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
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty("修改人")
    private String updator;
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty("删除状态：0-存在1-已删除")
    private Integer deleted;

}