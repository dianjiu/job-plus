package cn.org.dianjiu.job.service.impl;

import cn.org.dianjiu.job.common.exception.BusinessException;
import cn.org.dianjiu.job.common.req.TTaskRecordsReq;
import cn.org.dianjiu.job.common.resp.TTaskDetailsResp;
import cn.org.dianjiu.job.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.job.common.util.ObjectUtils;
import cn.org.dianjiu.job.dao.TTaskRecordsDao;
import cn.org.dianjiu.job.service.TTaskDetailsServiceI;
import cn.org.dianjiu.job.service.TTaskRecordsServiceI;
import cn.org.dianjiu.job.entity.TTaskRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务执行情况记录表(TTaskRecords)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TTaskRecordsServiceImpl implements TTaskRecordsServiceI {

    @Autowired
    private TTaskRecordsDao tTaskRecordsDao;
    @Autowired
    private TTaskDetailsServiceI tTaskDetailsService;

    @Override
    public TTaskRecordsResp getById(Integer id) {
        TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
        TTaskRecords tTaskRecords = tTaskRecordsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            throw new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskRecords,tTaskRecordsResp);
        return tTaskRecordsResp;
    }

    @Override
    public TTaskRecordsResp getByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
        TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        TTaskRecords tTaskRecords1 = tTaskRecordsDao.getByEntity(tTaskRecords);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
            log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
        return tTaskRecordsResp;
    }

    @Override
    public List<TTaskRecordsResp> listByEntity(TTaskRecordsReq tTaskRecordsReq) {
        List<TTaskRecordsResp> list = new ArrayList<>();
        TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        List<TTaskRecords> tTaskRecordss = tTaskRecordsDao.listByEntity(tTaskRecords);
        if(null == tTaskRecordss || tTaskRecordss.isEmpty()){
            log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
        }
        for (TTaskRecords tTaskRecords1 : tTaskRecordss ) {
            TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
                log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
                throw new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
            list.add(tTaskRecordsResp);
        }
        return list;
    }

    @Override
    public List<TTaskRecordsResp> listByIds(List<Integer> ids) {
      List<TTaskRecordsResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        List<TTaskRecords> tTaskRecordss  = tTaskRecordsDao.listByIds(ids);
        if(null != tTaskRecordss && !tTaskRecordss.isEmpty()){
            for (TTaskRecords tTaskRecords1 : tTaskRecordss ) {
                TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    throw new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
                list.add(tTaskRecordsResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        Date date = new Date();
        tTaskRecords.setCreateTime(date);
        tTaskRecords.setUpdateTime(date);
        return tTaskRecordsDao.insert(tTaskRecords);
    }

    @Override
    public int insertBatch(List<TTaskRecordsReq> list) {
      List<TTaskRecords> tTaskRecordss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TTaskRecordsReq tTaskRecordsReq : list) {
            TTaskRecords tTaskRecords = new TTaskRecords();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
            tTaskRecordss.add(tTaskRecords);
        }
        return tTaskRecordsDao.insertBatch(tTaskRecordss);
    }

    @Override
    public int update(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        tTaskRecords.setUpdateTime(new Date());
        return tTaskRecordsDao.update(tTaskRecords);
    }

    @Override
    public int updateBatch(List<TTaskRecordsReq> list) {
      List<TTaskRecords> tTaskRecordss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TTaskRecordsReq tTaskRecordsReq : list) {
            TTaskRecords tTaskRecords = new TTaskRecords();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
            tTaskRecordss.add(tTaskRecords);
        }
        return tTaskRecordsDao.updateBatch(tTaskRecordss);
    }

    @Override
    public int deleteById(Integer id) {
        return tTaskRecordsDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        return tTaskRecordsDao.deleteByEntity(tTaskRecords);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        return tTaskRecordsDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskRecordsDao.countAll();
    }

    @Override
    public int countByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        return tTaskRecordsDao.countByEntity(tTaskRecords);
    }

    @Override
    public TTaskRecordsResp addTaskRecords(Integer id) {
        TTaskRecordsReq tTaskRecordsReq = new TTaskRecordsReq();
        //根据ID查询TaskDetail信息
        TTaskDetailsResp taskDetailsResp = tTaskDetailsService.getById(id);
        //对象拷贝，新增到执行记录表中
        ObjectUtils.copyProperties(taskDetailsResp,tTaskRecordsReq);
        tTaskRecordsReq.setId(null);
        tTaskRecordsReq.setExecuteTime(new Date());
        insert(tTaskRecordsReq);
        //查询改记录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TTaskRecordsResp tTaskRecordsResp = null;
        try {
            tTaskRecordsResp = getByEntity(new TTaskRecordsReq(tTaskRecordsReq.getTaskName(),tTaskRecordsReq.getGroupName(),sdf.parse(sdf.format(tTaskRecordsReq.getExecuteTime()))));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsResp)) {
            throw new BusinessException("400", "新增定时任务执行记录异常！TaskDetail-ID【"+id+"】");
        }
        return tTaskRecordsResp;
    }

    @Override
    public int updateRecordById(int num, Integer id,String result) {
        TTaskRecordsReq tTaskRecordsReq = new TTaskRecordsReq();
        tTaskRecordsReq.setId(id);
        tTaskRecordsReq.setReturnInfo(result);
        if(0 == num){ //错误次数为0，则成功
            tTaskRecordsReq.setTaskStatus("1");
            return update(tTaskRecordsReq);
        }
        tTaskRecordsReq.setTaskStatus("0");
        return update(tTaskRecordsReq);
    }

}