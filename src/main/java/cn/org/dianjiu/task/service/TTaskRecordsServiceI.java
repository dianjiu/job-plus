package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TTaskRecordsReq;
import cn.org.dianjiu.task.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.task.dao.TTaskRecordsDao;

import java.text.ParseException;
import java.util.List;

/**
 * 定时任务执行情况记录表(TTaskRecords)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TTaskRecordsServiceI {
   
    TTaskRecordsResp getById(Integer id);

    TTaskRecordsResp getByEntity(TTaskRecordsReq tTaskRecordsReq);

    List<TTaskRecordsResp> listByEntity(TTaskRecordsReq tTaskRecordsReq);

    List<TTaskRecordsResp> listByIds(List<Integer> ids);

    int insert(TTaskRecordsReq tTaskRecordsReq);

    int insertBatch(List<TTaskRecordsReq> list);

    int update(TTaskRecordsReq tTaskRecordsReq);

    int updateBatch(List<TTaskRecordsReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TTaskRecordsReq tTaskRecordsReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TTaskRecordsReq tTaskRecordsReq);

    TTaskRecordsResp addTaskRecords(Integer id) throws ParseException;

    int updateRecordById(int num, Integer id,String result);
}