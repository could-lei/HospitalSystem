package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.result.Result;
import com.example.demo.service.LoginService;
import com.example.demo.Entry.SysRole;
import com.example.demo.Entry.SysUser;
import com.example.demo.unitl.JWTUtil;
import com.example.demo.unitl.MD5Util;
import com.example.demo.unitl.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/10/22.
 */
@RestController
//@CrossOrigin
@Api(value = "User")
public class shiroController {
    @Autowired
    private LoginService loginService;


    //退出的时候是get请求，主要是用于退出
//    @ApiOperation(value = "登入 登出",notes = "")
//    @ResponseBody
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(){
//        return "login";
//    }

    //post登录
    @ApiOperation(value = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username",value = "用户名 root",required = true,dataType = "String"),
                        @ApiImplicitParam(name = "password",value = "密码 123456",required = true,dataType = "String")
    })
//    @CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
//
//            RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
//
//            RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody String cs){
        //添加用户认证信息
        Map<String,String>map=JSON.parseObject(cs,Map.class);
        String username=map.get("username").toString();
        String password=MD5Util.md5Password(map.get("password").toString());
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
//                map.get("username").toString(),
//                MD5Util.md5Password(map.get("password").toString()));
        SysUser sysUser=loginService.findByName(username);
        if (sysUser.getPassword().equals(password)){
            String token=JWTUtil.sign(username,password);
            HashMap<String,String>tokens=new HashMap<>();
            tokens.put("token",token);
            return ResultUtil.success(tokens);
        }
        //进行验证，这里可以捕获异常，然后返回对应信息
//        subject.login(usernamePasswordToken);
        return ResultUtil.error("login not ok");
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token",value = "token",required = true,dataType = "String")
    })
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result getInfo(){
//        Map<String,String>map=JSON.parseObject(cs,Map.class);
//        String token=map.get("X-Token").toString();
//        HttpServletRequest req = (HttpServletRequest) request;
//        String token=req.getHeader("Authorization");
//        System.out.println(token);
//        String username=JWTUtil.getUsername(token);
        String username="root";
        SysUser sysUser=loginService.findByName(username);
        System.out.println(JSON.toJSONString(sysUser));
        List<SysRole> roles=sysUser.getRoles();
        sysUser.setRoles(null);
        String gif="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        HashMap<String,Object>params=new HashMap<>();
        params.put("user",JSON.toJSONString(sysUser));
        params.put("roles",JSON.toJSONString(roles.get(0).getRoleName()));
        params.put("avatar",JSON.toJSONString(gif));
//        params.put("")
        return ResultUtil.success(params);
    }

    @ApiOperation(value = "登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Result logout(){

        return ResultUtil.success("logout");
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody Map<String,Object> map){
        SysUser user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }
    @ApiOperation(value = "添加角色")
    //角色初始化
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(@RequestBody Map<String,Object> map){
        SysRole role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public Result create(ServletRequest servletRequest){
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        System.out.println(request.getHeader("Authorization"));
        String token=request.getHeader("Authorization");
        String username=JWTUtil.getUsername(token);
        SysUser sysUser=loginService.findByName(username);
        System.out.println(JSON.toJSONString(sysUser));
        List<SysRole> roles=sysUser.getRoles();
        sysUser.setRoles(null);
        String gif="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        HashMap<String,Object>params=new HashMap<>();
//        params.put("user",JSON.toJSONString(sysUser));
        String[] role=new String[2];
        role[0]=roles.get(0).getRoleName();
        params.put("name",sysUser.getName());
        params.put("roles",JSON.toJSONString(role));
        params.put("avatar",gif);
        return ResultUtil.success(params);
    }
    @RequestMapping(value = "/401",method = RequestMethod.GET)
    public Result errors(){
        return ResultUtil.error();
    }

}
