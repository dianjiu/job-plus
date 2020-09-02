package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.req.TUserRolesReq;
import cn.org.dianjiu.task.common.resp.TUserRolesResp;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.dao.TUserRolesDao;
import cn.org.dianjiu.task.service.TUserRolesServiceI;
import cn.org.dianjiu.task.entity.TUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TUserRoles)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TUserRolesServiceImpl implements TUserRolesServiceI {

    @Autowired
    private TUserRolesDao tUserRolesDao;

    @Override
    public TUserRolesResp getById(Integer id) {
        TUserRolesResp tUserRolesResp = new TUserRolesResp();
        TUserRoles tUserRoles = tUserRolesDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRoles)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            throw new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUserRoles,tUserRolesResp);
        return tUserRolesResp;
    }

    @Override
    public TUserRolesResp getByEntity(TUserRolesReq tUserRolesReq) {
      TUserRolesResp tUserRolesResp = new TUserRolesResp();
        TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        TUserRoles tUserRoles1 = tUserRolesDao.getByEntity(tUserRoles);
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRoles1)){
            log.error("根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUserRoles1,tUserRolesResp);
        return tUserRolesResp;
    }

    @Override
    public List<TUserRolesResp> listByEntity(TUserRolesReq tUserRolesReq) {
        List<TUserRolesResp> list = new ArrayList<>();
        TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        List<TUserRoles> tUserRoless = tUserRolesDao.listByEntity(tUserRoles);
        if(null == tUserRoless || tUserRoless.isEmpty()){
            log.error("根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
        }
        for (TUserRoles tUserRoles1 : tUserRoless ) {
            TUserRolesResp tUserRolesResp = new TUserRolesResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUserRoles1)){
                log.error("根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
                throw new BusinessException("400","根据tUserRolesReq【"+tUserRolesReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tUserRoles1,tUserRolesResp);
            list.add(tUserRolesResp);
        }
        return list;
    }

    @Override
    public List<TUserRolesResp> listByIds(List<Integer> ids) {
      List<TUserRolesResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        List<TUserRoles> tUserRoless  = tUserRolesDao.listByIds(ids);
        if(null != tUserRoless && !tUserRoless.isEmpty()){
            for (TUserRoles tUserRoles1 : tUserRoless ) {
                TUserRolesResp tUserRolesResp = new TUserRolesResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tUserRoles1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    throw new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tUserRoles1,tUserRolesResp);
                list.add(tUserRolesResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TUserRolesReq tUserRolesReq) {
      TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        Date date = new Date();
        tUserRoles.setCreateTime(date);
        tUserRoles.setUpdateTime(date);
        return tUserRolesDao.insert(tUserRoles);
    }

    @Override
    public int insertBatch(List<TUserRolesReq> list) {
      List<TUserRoles> tUserRoless = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TUserRolesReq tUserRolesReq : list) {
            TUserRoles tUserRoles = new TUserRoles();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
            tUserRoless.add(tUserRoles);
        }
        return tUserRolesDao.insertBatch(tUserRoless);
    }

    @Override
    public int update(TUserRolesReq tUserRolesReq) {
      TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        tUserRoles.setUpdateTime(new Date());
        return tUserRolesDao.update(tUserRoles);
    }

    @Override
    public int updateBatch(List<TUserRolesReq> list) {
      List<TUserRoles> tUserRoless = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TUserRolesReq tUserRolesReq : list) {
            TUserRoles tUserRoles = new TUserRoles();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
            tUserRoless.add(tUserRoles);
        }
        return tUserRolesDao.updateBatch(tUserRoless);
    }

    @Override
    public int deleteById(Integer id) {
        return tUserRolesDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TUserRolesReq tUserRolesReq) {
      TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        return tUserRolesDao.deleteByEntity(tUserRoles);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        return tUserRolesDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tUserRolesDao.countAll();
    }

    @Override
    public int countByEntity(TUserRolesReq tUserRolesReq) {
      TUserRoles tUserRoles = new TUserRoles();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserRolesReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserRolesReq,tUserRoles);
        return tUserRolesDao.countByEntity(tUserRoles);
    }

}