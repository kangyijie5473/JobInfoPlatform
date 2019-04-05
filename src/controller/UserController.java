package controller;

import Service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.JobDetail;
import entity.User;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public String registerUser(@RequestParam("user") String name, @RequestParam("password") String passwd) {

        String result = null;
        JSONObject resultObject = new JSONObject();
        if (userService.queryUser(name) != null) { //检查是否已经存在用户名
            System.out.println("register error");
            resultObject.put("result", false);
            resultObject.put("detail", "name is dup");
        } else {
            User newUser = new User();
            System.out.println("register true");

            newUser.setName(name);
            newUser.setPasswd(passwd);
            userService.insertUser(newUser);
            resultObject.put("result",true);
        }
        result = resultObject.toJSONString();
        System.out.println(result);
        return result;
    }
    @RequestMapping("/login")
    public String loginUser(HttpSession session, @RequestParam("password") String passwd, @RequestParam("user") String name) {

        String result = null;
        JSONObject resultObject = new JSONObject();
        User user = userService.queryUser(name);
        if (user == null || !passwd.equals(user.getPasswd())) {
            System.out.println(user.getPasswd());
            System.out.println(passwd);
            resultObject.put("result", false);
        } else {
            resultObject.put("id", user.getId());
            resultObject.put("result", true);
            System.out.println("set session" + name);
            session.setAttribute("userName",name);
            session.setAttribute("id", user.getId());

        }
        result = resultObject.toJSONString();
        return result;
    }
    @RequestMapping("/isLogin")
    public String isLogin(HttpSession session, @RequestParam("userName") String userName) {
        System.out.println("is login " + userName);
        JSONObject js = new JSONObject();
        if (session.getAttribute("userName") == null) {
            System.out.println("no session");
            js.put("result", false);
        } else {
            User user = userService.queryUser(userName);
            System.out.println("you session");
            js.put("result", true);
            js.put("id", user.getId());
        }
        String result = js.toString();
        System.out.println("isLogin result " + result);
        return result;
    }
    @RequestMapping("/loginOut")
    public String logOut(HttpSession session) {
        JSONObject js = new JSONObject();
        js.put("result", true);
        session.removeAttribute("userName");
        session.removeAttribute("id");
        return js.toJSONString();
    }
//    @RequestMapping("/liulei")
//    public void liulei(HttpServletRequest request1, HttpServletResponse resp, HttpSession session) {
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
//
//    }
//    @RequestMapping("/liuleitest")
//    public String liuleitest(@RequestParam("data") String data) {
//        List<User> list = userService.display();
//        System.out.println(JSON.toJSONString(list));
//        return data;
//    }

}
