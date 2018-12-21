package com.example.demo.config;

import com.example.demo.Entry.SysPermission;
import com.example.demo.Entry.SysRole;
import com.example.demo.Entry.SysUser;
import com.example.demo.service.LoginService;
import com.example.demo.unitl.JWTToken;
import com.example.demo.unitl.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by next on 2018/10/22.
 */
@Configuration
public class MyShiroRealm extends AuthorizingRealm{
//
//    @Autowired
//    private userInfoDao userInfoDao;
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
//            throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
//        //获取用户的输入的账号.
//        String username = (String)token.getPrincipal();
//        System.out.println(token.getCredentials());
//        //通过username从数据库中查找 User对象，如果找到，没找到.
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        UserInfo userInfo = userInfoDao.findByUsername(username);
//        System.out.println("----->>userInfo="+userInfo.toString());
//        if(userInfo == null){
//            return null;
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userInfo, //用户名
//                userInfo.getPassword(), //密码
//                ByteSource.Util.bytes(userInfo.getUsername()),//salt=username+salt
//                getName()  //realm name
//        );
////        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
////                userInfo.getUsername(), //用户名
////                userInfo.getPassword(), //密码
////                userInfo.getUsername()
////        );
//        return authenticationInfo;
//    }
//实现AuthorizingRealm接口用户用户认证

    //用于用户查询
    @Autowired
    private LoginService loginService;
    private static final Logger _logger= LoggerFactory.getLogger(MyShiroRealm.class);
    //角色权限和对应权限添加
    public static final String SECRET="9281e268b77b7c439a20b46fd1483b9a";

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        _logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        String name= (String) JWTUtil.getUsername(principalCollection.toString());
        //查询用户名称
        SysUser user = loginService.findByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (SysPermission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        _logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String token = authenticationToken.getPrincipal().toString();
        String name= JWTUtil.getUsername(token);
        SysUser user = loginService.findByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token, token, "my_realm");
            return simpleAuthenticationInfo;
        }
    }

}
