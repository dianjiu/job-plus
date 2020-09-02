package cn.org.dianjiu.task.entity;

import java.util.Date;
import lombok.Data;
                                    import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * (TMenu)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:29
 */
@Data 
public class TMenu {
    /**
    * 菜单ID
    */
    private Integer id;
    /**
    * 菜单名称
    */
    private String menuName;
    /**
    * Controller路径
    */
    private String menuUrl;
    /**
    * 菜单编码
    */
    private String menuCode;
    /**
    * 父菜单ID
    */
    private Integer parentId;
    /**
    * 菜单类型：0-菜单1-按钮
    */
    private Integer menuType;
    /**
    * 显示序号
    */
    private Integer orderNum;
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
    /**
    * 删除状态：0-存在1-已删除
    */
    private Integer deleted;

}