package bbayramov2020a5.bbayramov2020a5.controllers;

import bbayramov2020a5.bbayramov2020a5.domain.Student;
import bbayramov2020a5.bbayramov2020a5.domain.Admin;
import bbayramov2020a5.bbayramov2020a5.repositories.CourseRepository;
import bbayramov2020a5.bbayramov2020a5.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    Logger log = LoggerFactory.getLogger("StudentLog");

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }



    @RequestMapping(value = "/students/{id}")
    public String showStudent(@PathVariable(name = "id", required = true) String stId, Model model, HttpSession session) {
        if(session.getAttribute("admin")!=null) {
            Optional<Student> std = studentRepository.findById(Long.parseLong(stId));
            log.debug("Student ID = " + std.get().getId());
            model.addAttribute("studentInfo", std.get());
            log.debug("Added to model");

            model.addAttribute("all_courses", courseRepository.findAll());
            log.debug("Course is added to model as well");
            return "student_info";
        }else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @RequestMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id", required = true) String stId, Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {

            Optional<Student> std = studentRepository.findById(Long.parseLong(stId));
            studentRepository.delete(std.get());

            return "redirect:/students/all";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @RequestMapping("/students/all")
    public String showAllStudents(Model model, Admin admin, HttpSession session) {
        if (session.getAttribute("admin") != null) {

            Iterable<Student> stdList = studentRepository.findAll();
            model.addAttribute("studentList", stdList);
            System.out.println("errorsuz");


            return "student_list";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }

    }
    @PostMapping("/stdologin")
    public String doLogin(@ModelAttribute("st") Student admin, Model model,HttpSession session) {
        Student student1 = studentRepository.findByCredentials(admin.getLogin(), admin.getPassword());
        if (student1==null){
            model.addAttribute("error",true);
            model.addAttribute("errorinfo","User not found");
            return "login";
        }
        session.setAttribute("admin", student1);
        model.addAttribute("admin", student1);

        return "redirect:/students/"+student1.getId()










                ;


    }


    @RequestMapping("/students/new")
    public String newStudent(Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {

            Student std = new Student();
            model.addAttribute("student", std);

            return "std_data";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @RequestMapping("/students/update/{id}")
    public String updateStudent(@PathVariable(name = "id", required = true) Student std, Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {

            model.addAttribute("student", std);

            return "std_data";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @PostMapping("/students/save/")
    public String saveStudent(@Valid @ModelAttribute Student std, BindingResult bindingResult,HttpSession session,Model model) {
        if (session.getAttribute("admin")!=null){
            if (bindingResult.hasErrors()) {
                return "std_data";
            }
            studentRepository.save(std);

            return "redirect:/students/all";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }


}

