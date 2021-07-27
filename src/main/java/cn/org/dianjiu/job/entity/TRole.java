package cn.org.dianjiu.job.entity;

import lombok.Data;

import java.util.Date;

/**
 * (TRole)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TRole {
    /**
    * 角色id
    */
    private Integer id;
    /**
    * 角色名称
    */
    private String roleName;
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