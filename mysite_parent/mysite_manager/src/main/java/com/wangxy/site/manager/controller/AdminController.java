package com.wangxy.site.manager.controller;


import com.github.pagehelper.PageInfo;
import com.wangxy.site.manager.entity.Admin;
import com.wangxy.site.manager.service.IAdminService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author wangxy
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/manager/admin")
public class AdminController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IAdminService adminService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",adminService.list());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",adminService.getById(id));
    }

    /**
     * 获取用户信息
     * @param id ID
     * @return
     */
    @RequestMapping(value="/info",method= RequestMethod.GET)
    public Result getInfoById(String id){
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        Admin admin = adminService.findByLoginName(claims.getSubject());
        Map map=new HashMap();
        map.put("name",admin.getLoginname());
        map.put("id",admin.getId());
        map.put("roles","admin");

        return new Result(true,StatusCode.OK,"查询成功",map);
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map<String,Object> searchMap , @PathVariable int page, @PathVariable int size){
        PageInfo<Admin> pageList = adminService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Admin>(pageList.getTotal(),pageList.getList()) );
    }

//    /**
//     * 根据条件查询
//     * @param searchMap
//     * @return
//     */
//    @RequestMapping(value="/search",method = RequestMethod.POST)
//    public Result findSearch( @RequestBody Map searchMap){
//        return new Result(true,StatusCode.OK,"查询成功",adminService.findSearch(searchMap));
//    }

    /**
     * 增加
     * @param admin
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Admin admin  ){
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param admin
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Admin admin, @PathVariable String id ){
        admin.setId(id);
        adminService.updateById(admin);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        adminService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }


    /**
     * 管理员登陆
     * @param loginMap
     * @return
     */
    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public Result login(@RequestBody  Map<String,String> loginMap){

        Admin admin = adminService.findByLoginnameAndPassword(loginMap.get("username"), loginMap.get("password"));
        if(admin!=null){//登陆成功
            //签发token
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");
            Map map=new HashMap();
            map.put("token",token);
            map.put("name",admin.getLoginname());
            return new Result(true,StatusCode.OK,"登陆成功",map);
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }

    }


}
