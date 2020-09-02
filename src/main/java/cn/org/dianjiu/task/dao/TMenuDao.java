package cn.org.dianjiu.task.dao;

import cn.org.dianjiu.task.entity.TMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (TMenu)表数据库访问层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:30
 */
@Mapper
public interface TMenuDao {
    
    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    TMenu getById(@NotNull Integer id);
    
    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tMenu 实例对象
     * @return 对象列表
     */
    List<TMenu> listByEntity(TMenu tMenu);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tMenu
     * @return 实例对象
     */
    TMenu getByEntity(TMenu tMenu);

    /**
     * 通过Id列表作为筛选条件查询对象列表，列表长度不为0
     *
     * @param list 实例对象
     * @return 对象列表
     */
    List<TMenu> listByIds(@NotEmpty List<Integer> list);

    /**
     * 新增实体属性不为null的记录
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int insert(@NotNull TMenu tMenu);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<TMenu> list);
    
    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int update(@NotNull TMenu tMenu);
    
    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 实例对象
     * @param set 实例对象
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") TMenu where, @NotNull @Param("set") TMenu set);
    
    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<TMenu> list);
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Integer id);
    
    /**
     * 通过实体非空属性删除
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int deleteByEntity(@NotNull TMenu tMenu);
    
    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Integer> list);
    
    int countAll();
    
    int countByEntity(TMenu tMenu);

    @Select(" select m.* from t_menu m,t_role_menus rm,t_user_roles ur where m.id=rm.menu_id and rm.role_id=ur.role_id and ur.user_id==#{adminId} ")
    List<TMenu> getMenuListByUserID(@Param("adminId")Integer adminId);
}