package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@ResponseBody
@Controller
public class Test {
    @RequestMapping("/test")
    public String test(@RequestParam("data") String data){
        return data;
    }
}
