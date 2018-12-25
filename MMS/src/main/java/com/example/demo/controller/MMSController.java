package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entry.SysUser;
import com.example.demo.enums.*;
import com.example.demo.mapper.fdaMapper;
import com.example.demo.mapper.receptorMapper;
import com.example.demo.model.*;
import com.example.demo.result.Result;
import com.example.demo.service.*;
import com.example.demo.unitl.JWTUtil;
import com.example.demo.unitl.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by next on 2018/12/3.
 */
@CrossOrigin
@Api(value = "MMS")
@RestController
public class MMSController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private itemsService itemsService;
    @Autowired
    private prescriptionService prescriptionService;
    @Autowired
    private departmentService departmentService;
    @Resource
    private fdaMapper fdaMapper;
    @Resource
    private receptorMapper receptorMapper;
    @Autowired
    private checkService checkService;
    @Autowired
    private LoginService loginService;
    @Value("${di}")
    private String filePath;
    @ApiOperation(value = "查询检查项目字典")
    @RequestMapping(value = "/search_test")
    public Result search_test(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                              @RequestParam Map map){
        List<String>tests=new ArrayList<>();
        List<department>departmentList=departmentService.get_departments();
        for (department d:departmentList
             ) {
            tests.add(d.getDepartment());
        }
        return ResultUtil.success(tests);
    }
    @RequestMapping(value = "/search_user",method = RequestMethod.POST)
    public Result search_user(@RequestBody String cs){
        Map<String,String>map=JSON.parseObject(cs,Map.class);
        int type=Integer.valueOf(map.get("type")).intValue();
        String target=map.get("target");
        try {
            patient p=patientService.searchPatient(type,target);
            HashMap<String,List>response=new HashMap<>();
            List<PatientResponse>list=new ArrayList<>();
            list.add(patientService.Serialize(p));
            response.put("patient",list);
            return ResultUtil.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("用户不存在");
        }
    }
    @RequestMapping(value = "/prescriptionOrderDetail",method = RequestMethod.POST)
    public Result prescriptionOrderDetail(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long orderId=Long.valueOf(map.get("orderId").toString());
        System.out.println("orderId:"+orderId);
        return ResultUtil.success(prescriptionService.PRESCRIPTION_ORDER_DETAILS(orderId));
    }

    @RequestMapping(value = "/applyPrescription",method = RequestMethod.POST)
    public Result applyPrescription(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        Long eId= Long.valueOf(1);
        String fdas=map.get("prescriptionList").toString();
        List<PrescriptionRequest>fda_list= JSON.parseArray(fdas,PrescriptionRequest.class);
        try {
            prescriptionService.prescription(medicareId,eId,fda_list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("开处方单失败");
        }
        return ResultUtil.success("成功");
    }
    @ApiOperation(value = "查询处方")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/prescriptionOrder",method = RequestMethod.POST)
    public Result prescriptionOrder(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        System.out.println(map.get("medicareId"));
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        List<PrescriptionOrderResponse>prescriptionOrderResponses=new ArrayList<>();
        PrescriptionOrderResponse p=prescriptionService.SearchPrescription(medicareId);
        if (p==null){
            return ResultUtil.error("处方不存在");
        }
        prescriptionOrderResponses.add(p);
        HashMap<String,List<PrescriptionOrderResponse>>response=new HashMap<>();
        response.put("order",prescriptionOrderResponses);
        return ResultUtil.success(response);
    }

    @ApiOperation(value = "查询药物字典")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/search_fda",method = RequestMethod.POST)
    public Result search_fda(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                             @RequestParam Map map){
        List<fda>fdas=fdaMapper.selectAll();
        List<ResponseTemplate>fda_respons=new ArrayList<>();
        for (fda f:fdas
             ) {
            ResponseTemplate fdaRespons=new ResponseTemplate();
            fdaRespons.setValue(f.getDrug());
            fdaRespons.setId(f.getId());
            fda_respons.add(fdaRespons);
        }
        return ResultUtil.success(fda_respons);
    }
    @RequestMapping(value = "/checkOrder",method = RequestMethod.POST)
    public Result checkOrder(ServletRequest servletRequest, @RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        Long type=Long.valueOf(map.get("type").toString());
        List<TestOrderResponse>testOrderResponses=checkService.FindTest(type,medicareId);
        HashMap<String,List<TestOrderResponse>>m=new HashMap<>();
        m.put("order",testOrderResponses);
        return ResultUtil.success(m);
    }
    @ApiOperation(value = "开检查单")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String"),
            @ApiImplicitParam(name = "departmentIds",value = "检查项目id数组",required = true,dataType = "String")
    })
    @RequestMapping(value = "/applyCheck",method = RequestMethod.POST)
    public Result applyCheck(ServletRequest servletRequest, @RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        List<Long>ids=JSON.parseArray(map.get("checkList").toString(),Long.class);
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        String token=request.getHeader("Authorization");
        String username= JWTUtil.getUsername(token);
        user sysUser=patientService.FindUserByName(username);
        System.out.println(sysUser.getName());
        try {
            departmentService.applyCheck(ids,medicareId,sysUser.getId().intValue());
            return ResultUtil.success("申请成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("申请失败");
        }
    }
    @ApiOperation(value = "配药")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/finishPrescription",method = RequestMethod.POST)
    public Result finishPrescription(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long orderId=Long.valueOf(map.get("orderId").toString());
        try {
            Boolean flag=prescriptionService.finishPrescription(orderId);
            if (flag){
                return ResultUtil.success("配药成功");
            }else {
                return ResultUtil.error("库存不足");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("配药出错");
        }
    }
    @ApiOperation(value = "取药")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/finishDispensary",method = RequestMethod.POST)
    public Result finishDispensary(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long medicareId=Long.valueOf(map.get("orderId").toString());
        try {
            Boolean flag=prescriptionService.finishDispensary(medicareId);
            if (flag){
                return ResultUtil.success("取药成功");
            }else {
                return ResultUtil.error("库存不足");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("取药失败");
        }
    }
    @ApiOperation(value = "排队")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String"),
            @ApiImplicitParam(name = "department",value = "检查项目id",required = true,dataType = "String")
    })
    @RequestMapping(value = "/arranging",method = RequestMethod.POST)
    public Result arranging(ServletRequest servletRequest,@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                            @RequestParam Map map){
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        Long departmentId=Long.valueOf(map.get("department").toString());
        List<Long>ids=new LinkedList<>();
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        System.out.println(request.getHeader("Authorization"));
        String token=request.getHeader("Authorization");
        String username= JWTUtil.getUsername(token);
        SysUser sysUser=loginService.findByName(username);
        ids.add(medicareId);
        try {
            departmentService.applyCheck(ids,departmentId,sysUser.getId().intValue());
            return ResultUtil.success("排队成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("排队失败");
        }
    }
    @ApiOperation(value = "结束检查")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/finishCheck",method = RequestMethod.POST)
    public Result finishCheck(@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long medicareId=Long.valueOf(map.get("orderId").toString());
        try {
            checkService.finishCheck(medicareId);
            return  ResultUtil.success("检查结束");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error("结束失败");
        }
    }
    @ApiOperation(value = "取检查")
    @ApiImplicitParams({@ApiImplicitParam(name = "medicareId",value = "医疗卡号",required = true,dataType = "String")
    })
    @RequestMapping(value = "/checkDoneOrder",method = RequestMethod.POST)
    public Result takeResults(HttpServletResponse response,@RequestBody String cs){
        Map<String,Object>map=JSON.parseObject(cs,Map.class);
        Long medicareId=Long.valueOf(map.get("medicareId").toString());
        Long medicareId1=Long.valueOf(medicareId);
        Word d=new Word();
        List<String>list=departmentService.getProject(medicareId1);
        d.createWord(list);
        String filename="1.doc";
//        filePath = "F:/test" ;
        File file = new File(filePath + "/" + filename);
        String stringBuilder=new String();
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

//            OutputStream os = null; //输出流
            try {
//                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    stringBuilder+=buffer;
//                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ResultUtil.success(stringBuilder);
    }

//    public String createWord(List<String>projects){
//        Map<String,Object>dataMap=new HashMap<>();
////        dataMap.put()
//        String[]pj=new String[4];
//        pj[0]="身体很好";
//        pj[1]="好的一批";
//        pj[2]="身体好的岂止一批";
//        pj[3]="这情况简直结束牛皮";
//        List<Map<String,Object>>listInfo=new ArrayList<Map<String, Object>>();
//        int i=0;
//        for (String s:projects
//             ) {
//            Map<String,Object>map=new HashMap<>();
//            map.put("no",i+"");
//            map.put("project",s);
////            int i1=
//            map.put("describe",pj[i%4]);
//        }
//        dataMap.put("listInfo",listInfo);
//        String name="1";
//
//        return dataMap.toString();
//    }
}