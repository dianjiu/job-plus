package cn.org.dianjiu.task.entity;

import lombok.Data;

import java.util.Date;

/**
 * (TUserRoles)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TUserRoles {
    /**
    * 用户角色对照ID
    */
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updator;
    /**
     * 修改时间
     */
    private Date updateTime;
}