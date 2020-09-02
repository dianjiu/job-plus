package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.req.TUserReq;
import cn.org.dianjiu.task.common.resp.TUserResp;
import cn.org.dianjiu.task.common.util.JwtTokenUtil;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.dao.TMenuDao;
import cn.org.dianjiu.task.dao.TRoleDao;
import cn.org.dianjiu.task.dao.TUserDao;
import cn.org.dianjiu.task.entity.TMenu;
import cn.org.dianjiu.task.entity.TRole;
import cn.org.dianjiu.task.entity.User;
import cn.org.dianjiu.task.service.TUserServiceI;
import cn.org.dianjiu.task.entity.TUser;
import lombok.Value;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TUser)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TUserServiceImpl implements TUserServiceI {
    @Autowired
    private TUserDetailService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private TUserDao tUserDao;
    @Autowired
    private TRoleDao tRoleDao;
    @Autowired
    private TMenuDao tMenuDao;

    @Override
    public TUser getUserByUsername(String username) {
        TUser tUserReq = new TUser();
        tUserReq.setUsername(username);
        TUser userResp = tUserDao.getByEntity(tUserReq);
        if(null != userResp){
            throw new UsernameNotFoundException("用户不存在");
        }
        return userResp;
    }

    @Override
    public TUser register(TUser userParam) {
        if(ObjectUtils.checkObjAllFieldsIsNull(userParam)){
            //TODO 抛出自定义异常，对象或者属性为空的异常
        }
        //查询是否有相同用户名的用户
        TUser tUser = new TUser();
        tUser.setUsername(userParam.getUsername());
        TUser userResp = tUserDao.getByEntity(tUser);
        if(null != userResp){
            //TODO 抛出自定义异常，添加数据重复的异常
        }
        ObjectUtils.copyProperties(userParam,tUser);
        tUser.setCreateTime(new Date());
        tUser.setStatus(1);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userParam.getPassword());
        tUser.setPassword(encodePassword);
        int insert = tUserDao.insert(tUser);
        if(insert>0){
            return tUser;
        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            User user = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(user);
        } catch (AuthenticationException e) {
            log.error("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<TRole> getRoleListByUserID(Integer adminId) {
        return tRoleDao.getRoleListByUserID(adminId);
    }

    @Override
    public List<TMenu> getMenuListByUserID(Integer adminId) {
        return tMenuDao.getMenuListByUserID(adminId);
    }

    @Override
    public TUserResp getById(Integer id) {
        TUserResp tUserResp = new TUserResp();
        TUser tUser = tUserDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tUser)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            throw new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUser,tUserResp);
        return tUserResp;
    }

    @Override
    public TUserResp getByEntity(TUserReq tUserReq) {
      TUserResp tUserResp = new TUserResp();
        TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        TUser tUser1 = tUserDao.getByEntity(tUser);
        if(ObjectUtils.checkObjAllFieldsIsNull(tUser1)){
            log.error("根据tUserReq【"+tUserReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tUserReq【"+tUserReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUser1,tUserResp);
        return tUserResp;
    }

    @Override
    public List<TUserResp> listByEntity(TUserReq tUserReq) {
        List<TUserResp> list = new ArrayList<>();
        TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        List<TUser> tUsers = tUserDao.listByEntity(tUser);
        if(null == tUsers || tUsers.isEmpty()){
            log.error("根据tUserReq【"+tUserReq+"】没有查到相关记录！");
            throw new BusinessException("400","根据tUserReq【"+tUserReq+"】没有查到相关记录！");
        }
        for (TUser tUser1 : tUsers ) {
            TUserResp tUserResp = new TUserResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUser1)){
                log.error("根据tUserReq【"+tUserReq+"】没有查到相关记录！");
                throw new BusinessException("400","根据tUserReq【"+tUserReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tUser1,tUserResp);
            list.add(tUserResp);
        }
        return list;
    }

    @Override
    public List<TUserResp> listByIds(List<Integer> ids) {
      List<TUserResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        List<TUser> tUsers  = tUserDao.listByIds(ids);
        if(null != tUsers && !tUsers.isEmpty()){
            for (TUser tUser1 : tUsers ) {
                TUserResp tUserResp = new TUserResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tUser1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    throw new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tUser1,tUserResp);
                list.add(tUserResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TUserReq tUserReq) {
      TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        Date date = new Date();
        tUser.setCreateTime(date);
        tUser.setUpdateTime(date);
        return tUserDao.insert(tUser);
    }

    @Override
    public int insertBatch(List<TUserReq> list) {
      List<TUser> tUsers = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TUserReq tUserReq : list) {
            TUser tUser = new TUser();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tUserReq,tUser);
            tUsers.add(tUser);
        }
        return tUserDao.insertBatch(tUsers);
    }

    @Override
    public int update(TUserReq tUserReq) {
      TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        tUser.setUpdateTime(new Date());
        return tUserDao.update(tUser);
    }

    @Override
    public int updateBatch(List<TUserReq> list) {
      List<TUser> tUsers = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TUserReq tUserReq : list) {
            TUser tUser = new TUser();
            if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tUserReq,tUser);
            tUsers.add(tUser);
        }
        return tUserDao.updateBatch(tUsers);
    }

    @Override
    public int deleteById(Integer id) {
        return tUserDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TUserReq tUserReq) {
      TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        return tUserDao.deleteByEntity(tUser);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            throw new BusinessException("400","id集合不能为空！");
        }
        return tUserDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tUserDao.countAll();
    }

    @Override
    public int countByEntity(TUserReq tUserReq) {
      TUser tUser = new TUser();
        if(ObjectUtils.checkObjAllFieldsIsNull(tUserReq)){
            log.error("入参对象不能为空！");
            throw new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq,tUser);
        return tUserDao.countByEntity(tUser);
    }

}