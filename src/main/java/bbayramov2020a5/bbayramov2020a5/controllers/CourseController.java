package bbayramov2020a5.bbayramov2020a5.controllers;

import bbayramov2020a5.bbayramov2020a5.repositories.CourseRepository;
import bbayramov2020a5.bbayramov2020a5.repositories.StudentRepository;
import bbayramov2020a5.bbayramov2020a5.domain.Course;
import bbayramov2020a5.bbayramov2020a5.domain.Student;
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
public class CourseController {
    CourseRepository courseRepository;
    StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository,StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository=studentRepository;
    }
    @RequestMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable(name="id", required = true) Course crs, Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            model.addAttribute("course", crs);

            return "crs_data";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }
    @RequestMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable(name="id", required = true) String crnId, Model model,HttpSession session) {
        if (session.getAttribute("admin") != null) {

            Optional<Course> crs = courseRepository.findById(Long.parseLong(crnId));
            courseRepository.delete(crs.get());

            return "course_list";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @RequestMapping("/courses/all")
    public String showAllCourses(Model model,HttpSession session) {
        if (session.getAttribute("admin")!=null){
            Iterable<Course> stdList = courseRepository.findAll();
            model.addAttribute("courseList",stdList);

            return "course_list";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @RequestMapping("/courses/new")
    public String newCourse(Model model,HttpSession session) {
        if (session.getAttribute("admin") != null) {
            Course crs = new Course();
            model.addAttribute("course", crs);

            return "crs_data";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }

    @PostMapping("/courses/save/")
    public String saveCourse(@Valid @ModelAttribute Course crs, BindingResult bindingResult,Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            if (bindingResult.hasErrors()) {
                return "crs_data";
            }
            courseRepository.save(crs);

            return "redirect:/courses/all";
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }
    @RequestMapping("/courses/enroll/{stId}/{CRN}")
    public String showAllStudents(@PathVariable(name="stId", required=true) Student std,
                                  @PathVariable(name="CRN", required=true) Course crn,
                                  Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            std.enrollToCourse(crn);
            crn.enrollStudent(std);
            studentRepository.save(std);
            courseRepository.save(crn);


            return "redirect:/students/" + std.getId();
        } else {

            model.addAttribute("error", true);
            model.addAttribute("errorinfo", "Please Login...");
            return "login";
        }
    }



}

