package cn.org.dianjiu.job.service.impl;

import cn.org.dianjiu.job.common.exception.BusinessException;
import cn.org.dianjiu.job.common.req.TMenuReq;
import cn.org.dianjiu.job.common.resp.TMenuResp;
import cn.org.dianjiu.job.common.util.ObjectUtils;
import cn.org.dianjiu.job.dao.TMenuDao;
import cn.org.dianjiu.job.service.TMenuServiceI;
import cn.org.dianjiu.job.entity.TMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TMenu)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:31
 */
@Slf4j
@Service
public class TMenuServiceImpl implements TMenuServiceI {

    @Autowired
    private TMenuDao tMenuDao;

    @Override
    public TMenuResp getById(Integer id) {
        TMenuResp tMenuResp = new TMenuResp();
        TMenu tMenu = tMenuDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenu)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            throw new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tMenu,tMenuResp);
        return tMenuResp;
    }

    @Override
    public TMenuResp getByEntity(TMenuReq tMenuReq) {
      TMenuResp tMenuResp = new TMenuResp();
        TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        TMenu tMenu1 = tMenuDao.getByEntity(tMenu);
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenu1)){
            log.error("根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tMenu1,tMenuResp);
        return tMenuResp;
    }

    @Override
    public List<TMenuResp> listByEntity(TMenuReq tMenuReq) {
        List<TMenuResp> list = new ArrayList<>();
        TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        List<TMenu> tMenus = tMenuDao.listByEntity(tMenu);
        if(null == tMenus || tMenus.isEmpty()){
            log.error("根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
        }
        for (TMenu tMenu1 : tMenus ) {
            TMenuResp tMenuResp = new TMenuResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tMenu1)){
                log.error("根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
                throw new BusinessException("400","根据tMenuReq【"+tMenuReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tMenu1,tMenuResp);
            list.add(tMenuResp);
        }
        return list;
    }

    @Override
    public List<TMenuResp> listByIds(List<Integer> ids) {
      List<TMenuResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        List<TMenu> tMenus  = tMenuDao.listByIds(ids);
        if(null != tMenus && !tMenus.isEmpty()){
            for (TMenu tMenu1 : tMenus ) {
                TMenuResp tMenuResp = new TMenuResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tMenu1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    throw new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tMenu1,tMenuResp);
                list.add(tMenuResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TMenuReq tMenuReq) {
      TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        Date date = new Date();
        tMenu.setCreateTime(date);
        tMenu.setUpdateTime(date);
        return tMenuDao.insert(tMenu);
    }

    @Override
    public int insertBatch(List<TMenuReq> list) {
      List<TMenu> tMenus = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TMenuReq tMenuReq : list) {
            TMenu tMenu = new TMenu();
            if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tMenuReq,tMenu);
            tMenus.add(tMenu);
        }
        return tMenuDao.insertBatch(tMenus);
    }

    @Override
    public int update(TMenuReq tMenuReq) {
      TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        tMenu.setUpdateTime(new Date());
        return tMenuDao.update(tMenu);
    }

    @Override
    public int updateBatch(List<TMenuReq> list) {
      List<TMenu> tMenus = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TMenuReq tMenuReq : list) {
            TMenu tMenu = new TMenu();
            if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tMenuReq,tMenu);
            tMenus.add(tMenu);
        }
        return tMenuDao.updateBatch(tMenus);
    }

    @Override
    public int deleteById(Integer id) {
        return tMenuDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TMenuReq tMenuReq) {
      TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        return tMenuDao.deleteByEntity(tMenu);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        return tMenuDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tMenuDao.countAll();
    }

    @Override
    public int countByEntity(TMenuReq tMenuReq) {
      TMenu tMenu = new TMenu();
        if(ObjectUtils.checkObjAllFieldsIsNull(tMenuReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tMenuReq,tMenu);
        return tMenuDao.countByEntity(tMenu);
    }

}