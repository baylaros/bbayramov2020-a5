package bbayramov2020a5.bbayramov2020a5.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        return "/login";
    }


    @RequestMapping("stloginpamel")
    public String getIndexPageLog() {
        return "stlogin";
    }


    @RequestMapping("admindash")
    public String getAdminDash() {
        return "admindash";
    }




}

