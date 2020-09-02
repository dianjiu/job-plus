package cn.org.dianjiu.task.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.entity
 * @ClassName: User
 * @Author: MengWei
 * @Description: SpringSecurity需要的用户详情
 * @Date: 2020/8/3 21:44
 * @Version: 1.0
 */
@Getter
@Setter
public class User implements UserDetails {
    /**
     * 修改时间
     */
    private TUser tuser;
    /**
     * 用户权限集合
     */
    private List<TRole> userRoles;
    /**
     * 角色菜单集合
     */
    private List<TMenu> roleMenus;

    public User() {
    }

    public User(TUser tuser, List<TRole> userRoles, List<TMenu> roleMenus) {
        this.tuser = tuser;
        this.userRoles = userRoles;
        this.roleMenus = roleMenus;
    }

    public User(TUser tuser, List<TRole> userRoles) {
        this.tuser = tuser;
        this.userRoles = userRoles;
    }

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (TRole role : userRoles) {
            authorities.add( new SimpleGrantedAuthority( role.getRoleName() ) );
        }
        return authorities;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return userRoles.stream() // 创建流
                .filter(userRoles -> userRoles.getRoleName() !=null)// 执行过滤，过滤出userRoles.getName()为空的角色
                .map(userRoles->new SimpleGrantedAuthority(userRoles.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tuser.getPassword();
    }

    @Override
    public String getUsername() {
        return tuser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return tuser.getStatus().equals(1);
    }
}
