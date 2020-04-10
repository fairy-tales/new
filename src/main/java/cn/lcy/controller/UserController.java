package cn.lcy.controller;


import cn.lcy.domain.Percent;
import cn.lcy.domain.User;
import cn.lcy.service.UserService;

import cn.lcy.utils.POI.ExcelPoi;
import cn.lcy.utils.PageBean;
import cn.lcy.utils.ResponseUtil;
import cn.lcy.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log= Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    //存储预返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();

    /*@RequestMapping("/showUser.do")
    public String toIndex(HttpServletRequest request, Model model){
        System.out.println("lcytest");
        return "showUser";
    }
    // /user/test.do?id=1
    @RequestMapping(value="/test.do",method= RequestMethod.GET)
    public String test(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
            user = new User();
            user.setName("测试");
            user.setId(1);
            user.setNumber("测试1");
        }
        log.debug(user.toString());
        model.addAttribute("user", user);
        return "index";
    }
    // /user/showUser.do?id=1
    @RequestMapping(value="/showUser.do",method=RequestMethod.GET)
    public String toindex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);
        log.debug(user.toString());
        model.addAttribute("user", user);
        return "showUser";
    }

    // /user/showUser2.do?id=1
    @RequestMapping(value="/showUser2.do",method=RequestMethod.GET)
    public String toIndex2(@RequestParam("id") String id,Model model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:" + userId);
        User user = this.userService.getUserById(userId);
        log.debug(user.toString());
        model.addAttribute("user", user);
        return "showUser";
    }

    // /user/jsontype.do?id=1
    @RequestMapping(value="/jsontype.do",method=RequestMethod.GET)
    public @ResponseBody
    User getUserInJson(@RequestParam("id") String id, Map<String, Object> model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);
        log.info(user.toString());
        return user;
    }
    // /user/jsontype2.do?id=1
    @RequestMapping(value="/jsontype2.do",method=RequestMethod.GET)
    public ResponseEntity<User> getUserInJson2(@RequestParam("id") String id, Map<String, Object> model){
        int userId = Integer.parseInt(id);
        System.out.println("userId:"+userId);
        User user = this.userService.getUserById(userId);
        log.info(user.toString());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }*/

    //文件上传页面
    @RequestMapping(value="/upload.do")
    public String showUploadPage(){
        return "file";
    }
    //文件上传
    @RequestMapping(value="/doUpload.do",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            //log.info("Process file:{}",file.getOriginalFilename());
        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C:\\",System.currentTimeMillis()+file.getOriginalFilename()));
        return "succes";
    }

    /**
     * 保存
     * @param user
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(User user, Percent percent,HttpServletResponse res) throws Exception{
        //进行核算
       /* System.out.println(percent.getConclusionp());
        System.out.println(percent.getPrep());
        System.out.println(percent.getOperatep());*/
        double result = user.getPre() * percent.getPrep() + user.getOperate() * percent.getOperatep() + user.getConclusion()*percent.getConclusionp();
        user.setResult(result);
        //操作记录条数，初始化为0
        int resultTotal = 0;
        if (user.getId() == null) {
            resultTotal = userService.add(user);
        }else{
            resultTotal = userService.update(user);
        }
        JSONObject jsonObject = new JSONObject();
        if(resultTotal > 0){   //说明修改或添加成功
            jsonObject.put("success", true);
        }else{
            jsonObject.put("success", false);
        }
        ResponseUtil.write(res, jsonObject);
        return null;
    }

    /**
     * 用户分页查询
     * @param page
     * @param rows
     * @param s_user
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping("/list.do")
    public String list(@RequestParam(value="page",required=false) String page,@RequestParam(value="rows",required=false) String rows,User s_user,HttpServletResponse res) throws Exception{
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name", StringUtil.formatLike(s_user.getName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<User> userList=userService.find(map);
        String jsonString= JSON.toJSONString(userList);
        JSONArray jsonArray=JSONArray.parseArray(jsonString);

        Long total=userService.getTotal(map);
        JSONObject result=new JSONObject();
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(res, result);
        return null;
    }

    /**
     * 删除用户

     * @return
     * @throws Exception
     * @param id
     */


    @RequestMapping("delete.do")
    @ResponseBody
    public Map<String, Object> deleteUser(Integer[] id) {
        try {
            userService.delete(id);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "(ಥ_ಥ)服务器端发生异常 !");
            e.printStackTrace();
        }
        return result;
    }





    /**
     * 修改
     * @param id
     * @return
     */
    @RequestMapping("/editUser.do")
    @ResponseBody
    public User editFriend(Integer id) {
        return userService.getUserById(id);
    }


    /**
     * 导出
     * @param response
     * @param search
     * @return
     */
    @RequestMapping(value = "/export.do")
    @ResponseBody
    public String exportCustomerList(HttpServletResponse response, String search) {
        try {
            List<User> date = userService.getAll();
            //customService.getCustomList();
            String[] titles = {"姓名","学号", "实验名称", "预习","操作","结论","核算结果"};
            String filename = "成绩表";
            ExcelPoi.exportObject(response, date, titles, filename);
        } catch (Exception e) {
            log.error(e);
            /*System.out.println("controller层有问题");*/
        }
        return "";
    }


    /**
     * 导入
     * @param file
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/import.do",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request) {
        String statis="02";//导入失败
        try {
            if (file == null || file.getSize() == 0 || !file.getOriginalFilename().contains(".xls")) {
                return "文件无效";
            }
            List<User> list = new ExcelPoi<User>().importObjectList(file.getInputStream(), file.getOriginalFilename(), User.class);
            //list就是获取的数据
            Iterator<User> iterator=list.iterator();
            while (iterator.hasNext())
            {
                User user=(User)iterator.next();
                //可以在这里添加方法判断要导入的数据是否符合要求
                userService.add(user);//插入语句
            }
        } catch (Exception e) {
            //log.error(e);
            e.printStackTrace();
        }
        return statis;
    }









}
