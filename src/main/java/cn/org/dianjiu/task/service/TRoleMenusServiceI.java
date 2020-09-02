package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TRoleMenusReq;
import cn.org.dianjiu.task.common.resp.TRoleMenusResp;
import cn.org.dianjiu.task.dao.TRoleMenusDao;

import java.util.List;

/**
 * (TRoleMenus)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TRoleMenusServiceI {
   
    TRoleMenusResp getById(Integer id);

    TRoleMenusResp getByEntity(TRoleMenusReq tRoleMenusReq);

    List<TRoleMenusResp> listByEntity(TRoleMenusReq tRoleMenusReq);

    List<TRoleMenusResp> listByIds(List<Integer> ids);

    int insert(TRoleMenusReq tRoleMenusReq);

    int insertBatch(List<TRoleMenusReq> list);

    int update(TRoleMenusReq tRoleMenusReq);

    int updateBatch(List<TRoleMenusReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TRoleMenusReq tRoleMenusReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TRoleMenusReq tRoleMenusReq);
}