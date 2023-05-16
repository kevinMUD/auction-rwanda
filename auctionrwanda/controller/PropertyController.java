package com.auctionrwanda.controller;
import com.auctionrwanda.model.Property;
import com.auctionrwanda.services.implementation.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PropertyController {

    @Autowired
    PropertyServiceImpl propertyService;

    @GetMapping("/home")
    public String homePage(Model model){

        model.addAttribute("home",propertyService.studentList());

        return "home-page";
    }
@GetMapping("/inde")
    public String ind()
{
        return "index1";
    }
    @GetMapping("/registration1")
    public String registerStudentPage(Model model){
        Property stud = new Property();
        model.addAttribute("student", stud);
        return "registrar";
    }
    @GetMapping("/student-page")
    public String studentpage(Model model){
        Property stud = new Property();
        model.addAttribute("student", stud);
        return "student";
    }

    @PostMapping("/register")
    public String saveProperty1(@ModelAttribute("student") Property theStudent){
        Property savedStudent = propertyService.registerStudent(theStudent);
        if(savedStudent != null){
            return "redirect:/registration1?success";
        }
        return "redirect:/registration1?error";
    }

    @PostMapping("/register1")
    public String saveProperty2(@ModelAttribute("student") Property theStudent){
        Property savedStudent = propertyService.registerStudent(theStudent);
        if(savedStudent != null){
            return "redirect:/student-page?success";
        }
        return "redirect:/student-page?error";
    }
    @GetMapping("/home/update/{id}")
    public String editStudent(@PathVariable String id, Model model){
        model.addAttribute("student", propertyService.findStudentByStudentId(id));
        return "update";
    }
      @PostMapping("/home/{id}")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute("student") Property student, Model model)
      {
          Property existingStudent=propertyService.findStudentByStudentId(id);
          existingStudent.setTel(student.getTel());
          existingStudent.setId(student.getId());
          existingStudent.setName(student.getName());
          existingStudent.setEmail(student.getEmail());
          existingStudent.setDpt(student.getDpt());
          propertyService.updateStudent(existingStudent);
           return "redirect:/home";
      }
 @GetMapping("/home/{id}")
    public String deleteStudent(@PathVariable String id)
 {
     propertyService.deleteStudent(id);
     return "redirect:/home";
 }
 @GetMapping("/sear")
 public String search(Model model)
 {
     Property student=new Property();
     model.addAttribute("search",student);

     return "search";
 }
 @PostMapping("/sear")
 public String searchh(@ModelAttribute("search") Property student, Model model)
 {
     Property student1=propertyService.findStudentByStudentId(student.getId());
     if (student1!=null){
         model.addAttribute("student1",student1);
         return "search";
     }
     else {
         model.addAttribute("error","is not in the system");
         return "search";
     }
 }
 @GetMapping("/pack")
 public String photo(){
     return "package";}
    @GetMapping("/user")
    public String us(){
        return "user";
    }
}
