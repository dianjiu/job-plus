package cn.org.dianjiu.job.dao;

import cn.org.dianjiu.job.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;

/**
 * (TUser)表数据库访问层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Mapper
public interface TUserDao {
    
    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser getById(@NotNull Integer id);
    
    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tUser 实例对象
     * @return 对象列表
     */
    List<TUser> listByEntity(TUser tUser);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tUser
     * @return 实例对象
     */
    TUser getByEntity(TUser tUser);

    /**
     * 通过Id列表作为筛选条件查询对象列表，列表长度不为0
     *
     * @param list 实例对象
     * @return 对象列表
     */
    List<TUser> listByIds(@NotEmpty List<Integer> list);

    /**
     * 新增实体属性不为null的记录
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(@NotNull TUser tUser);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<TUser> list);
    
    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(@NotNull TUser tUser);
    
    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 实例对象
     * @param set 实例对象
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") TUser where, @NotNull @Param("set") TUser set);
    
    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<TUser> list);
    
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
     * @param tUser 实例对象
     * @return 影响行数
     */
    int deleteByEntity(@NotNull TUser tUser);
    
    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Integer> list);
    
    int countAll();
    
    int countByEntity(TUser tUser);
    
}