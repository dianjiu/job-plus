package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.dao.TRoleDao;
import cn.org.dianjiu.task.dao.TUserDao;
import cn.org.dianjiu.task.entity.TRole;
import cn.org.dianjiu.task.entity.TUser;
import cn.org.dianjiu.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.service.impl
 * @ClassName: UserDetailService
 * @Author: MengWei
 * @Description:
 * @Date: 2020/8/3 21:26
 * @Version: 1.0
 */
@Service
public class TUserDetailService implements UserDetailsService {

    @Autowired
    private TUserDao tUserDao;
    @Autowired
    private TRoleDao tRoleDao;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser tUserReq = new TUser();
        tUserReq.setUsername(username);
        TUser userResp = tUserDao.getByEntity(tUserReq);
        if (null == userResp) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //TODO 查询用户所有角色
        User user = new User();
        user.setTuser(userResp);
        return user;
    }

}
