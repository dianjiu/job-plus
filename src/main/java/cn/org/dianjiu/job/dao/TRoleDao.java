package cn.org.dianjiu.job.dao;

import cn.org.dianjiu.job.entity.TRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.*;
import java.util.List;

/**
 * (TRole)表数据库访问层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Mapper
public interface TRoleDao {
    
    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    TRole getById(@NotNull Integer id);
    
    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tRole 实例对象
     * @return 对象列表
     */
    List<TRole> listByEntity(TRole tRole);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tRole
     * @return 实例对象
     */
    TRole getByEntity(TRole tRole);

    /**
     * 通过Id列表作为筛选条件查询对象列表，列表长度不为0
     *
     * @param list 实例对象
     * @return 对象列表
     */
    List<TRole> listByIds(@NotEmpty List<Integer> list);

    /**
     * 新增实体属性不为null的记录
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int insert(@NotNull TRole tRole);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<TRole> list);
    
    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int update(@NotNull TRole tRole);
    
    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 实例对象
     * @param set 实例对象
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") TRole where, @NotNull @Param("set") TRole set);
    
    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<TRole> list);
    
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
     * @param tRole 实例对象
     * @return 影响行数
     */
    int deleteByEntity(@NotNull TRole tRole);
    
    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Integer> list);
    
    int countAll();
    
    int countByEntity(TRole tRole);

    @Select(" select r.* from t_role r,t_user_roles ur where r.id=ur.role_id and ur.user_id=#{adminId} ")
    List<TRole> getRoleListByUserID(@Param("adminId") Integer adminId);
}