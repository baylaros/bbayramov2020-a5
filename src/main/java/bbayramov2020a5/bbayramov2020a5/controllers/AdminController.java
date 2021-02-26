package bbayramov2020a5.bbayramov2020a5.controllers;

import bbayramov2020a5.bbayramov2020a5.domain.Admin;
import bbayramov2020a5.bbayramov2020a5.repositories.AdminRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }




    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("admin") Admin admin, Model model,HttpSession session) {
        Admin admn = adminRepository.findByCredentials(admin.getLogin(), admin.getPassword());
        if (admn==null){
            model.addAttribute("error",true);
            model.addAttribute("errorinfo","User not found");
            return "login";
        }
        session.setAttribute("admin", admn);
        model.addAttribute("admin", admn);

        return "redirect:admindash";


    }
}


