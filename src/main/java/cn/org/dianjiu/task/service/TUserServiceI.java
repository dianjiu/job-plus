package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TUserReq;
import cn.org.dianjiu.task.common.resp.TUserResp;
import cn.org.dianjiu.task.dao.TUserDao;
import cn.org.dianjiu.task.entity.TMenu;
import cn.org.dianjiu.task.entity.TRole;
import cn.org.dianjiu.task.entity.TUser;

import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TUserServiceI {

    /**
     * 根据用户名获取后台管理员
     */
    TUser getUserByUsername(String username);

    /**
     * 注册功能
     */
    TUser register(TUser umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有角色
     */
    List<TRole> getRoleListByUserID(Integer adminId);

    /**
     * 获取用户所有菜单
     */
    List<TMenu> getMenuListByUserID(Integer adminId);
   
    TUserResp getById(Integer id);

    TUserResp getByEntity(TUserReq tUserReq);

    List<TUserResp> listByEntity(TUserReq tUserReq);

    List<TUserResp> listByIds(List<Integer> ids);

    int insert(TUserReq tUserReq);

    int insertBatch(List<TUserReq> list);

    int update(TUserReq tUserReq);

    int updateBatch(List<TUserReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TUserReq tUserReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TUserReq tUserReq);

}