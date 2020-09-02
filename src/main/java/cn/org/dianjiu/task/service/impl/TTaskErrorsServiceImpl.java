package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.req.TTaskErrorsReq;
import cn.org.dianjiu.task.common.resp.TTaskErrorsResp;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.dao.TTaskErrorsDao;
import cn.org.dianjiu.task.service.TTaskErrorsServiceI;
import cn.org.dianjiu.task.entity.TTaskErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务出错现场信息表(TTaskErrors)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TTaskErrorsServiceImpl implements TTaskErrorsServiceI {

    @Autowired
    private TTaskErrorsDao tTaskErrorsDao;

    @Override
    public TTaskErrorsResp getById(Integer id) {
        TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
        TTaskErrors tTaskErrors = tTaskErrorsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            throw new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskErrors,tTaskErrorsResp);
        return tTaskErrorsResp;
    }

    @Override
    public TTaskErrorsResp getByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
        TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        TTaskErrors tTaskErrors1 = tTaskErrorsDao.getByEntity(tTaskErrors);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
            log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
        return tTaskErrorsResp;
    }

    @Override
    public List<TTaskErrorsResp> listByEntity(TTaskErrorsReq tTaskErrorsReq) {
        List<TTaskErrorsResp> list = new ArrayList<>();
        TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        List<TTaskErrors> tTaskErrorss = tTaskErrorsDao.listByEntity(tTaskErrors);
        if(null == tTaskErrorss || tTaskErrorss.isEmpty()){
            log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
        }
        for (TTaskErrors tTaskErrors1 : tTaskErrorss ) {
            TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
                log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
                throw new BusinessException("400","根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
            list.add(tTaskErrorsResp);
        }
        return list;
    }

    @Override
    public List<TTaskErrorsResp> listByIds(List<Integer> ids) {
      List<TTaskErrorsResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        List<TTaskErrors> tTaskErrorss  = tTaskErrorsDao.listByIds(ids);
        if(null != tTaskErrorss && !tTaskErrorss.isEmpty()){
            for (TTaskErrors tTaskErrors1 : tTaskErrorss ) {
                TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    throw new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
                list.add(tTaskErrorsResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        Date date = new Date();
        tTaskErrors.setCreateTime(date);
        tTaskErrors.setUpdateTime(date);
        return tTaskErrorsDao.insert(tTaskErrors);
    }

    @Override
    public int insertBatch(List<TTaskErrorsReq> list) {
      List<TTaskErrors> tTaskErrorss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TTaskErrorsReq tTaskErrorsReq : list) {
            TTaskErrors tTaskErrors = new TTaskErrors();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
            tTaskErrorss.add(tTaskErrors);
        }
        return tTaskErrorsDao.insertBatch(tTaskErrorss);
    }

    @Override
    public int update(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        tTaskErrors.setUpdateTime(new Date());
        return tTaskErrorsDao.update(tTaskErrors);
    }

    @Override
    public int updateBatch(List<TTaskErrorsReq> list) {
      List<TTaskErrors> tTaskErrorss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TTaskErrorsReq tTaskErrorsReq : list) {
            TTaskErrors tTaskErrors = new TTaskErrors();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
            tTaskErrorss.add(tTaskErrors);
        }
        return tTaskErrorsDao.updateBatch(tTaskErrorss);
    }

    @Override
    public int deleteById(Integer id) {
        return tTaskErrorsDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        return tTaskErrorsDao.deleteByEntity(tTaskErrors);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        return tTaskErrorsDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskErrorsDao.countAll();
    }

    @Override
    public int countByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        return tTaskErrorsDao.countByEntity(tTaskErrors);
    }


}