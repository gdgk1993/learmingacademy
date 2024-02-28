package com.experiment.learningacademy.controller;

import com.experiment.learningacademy.entity.LoginEntity;
import com.experiment.learningacademy.model.Student;
import com.experiment.learningacademy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {


    @Autowired
    private StudentService studentService;
    @PostMapping("/register")
   public String registerNewStudent(@RequestBody Student student){
       return this.studentService.registerStudent(student);
   }
   @GetMapping("/confirm/{confirmToken}")
   public String confirmRegistration(@PathVariable Integer confirmToken){
        return this.studentService.confirmRegistration(confirmToken);
   }

   @PostMapping("/auth/user")
   public String getLoggedInUserDetails(){

        return"test";

   }
}
