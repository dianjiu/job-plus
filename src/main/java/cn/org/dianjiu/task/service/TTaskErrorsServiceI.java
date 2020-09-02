package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TTaskErrorsReq;
import cn.org.dianjiu.task.common.resp.TTaskErrorsResp;
import cn.org.dianjiu.task.dao.TTaskErrorsDao;

import java.util.List;

/**
 * 定时任务出错现场信息表(TTaskErrors)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TTaskErrorsServiceI {
   
    TTaskErrorsResp getById(Integer id);

    TTaskErrorsResp getByEntity(TTaskErrorsReq tTaskErrorsReq);

    List<TTaskErrorsResp> listByEntity(TTaskErrorsReq tTaskErrorsReq);

    List<TTaskErrorsResp> listByIds(List<Integer> ids);

    int insert(TTaskErrorsReq tTaskErrorsReq);

    int insertBatch(List<TTaskErrorsReq> list);

    int update(TTaskErrorsReq tTaskErrorsReq);

    int updateBatch(List<TTaskErrorsReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TTaskErrorsReq tTaskErrorsReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TTaskErrorsReq tTaskErrorsReq);
}