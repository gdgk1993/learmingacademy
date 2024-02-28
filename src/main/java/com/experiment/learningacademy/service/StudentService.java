package com.experiment.learningacademy.service;

import com.experiment.learningacademy.model.Student;
import com.experiment.learningacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JavaMailSender mailSender;

      public String registerStudent(Student student){

        String toEmail =student.getEmail();
        String message1 = "Hi Mr"+student.getFirstName()+"Thank you for registration. please confirm your registration by clicking below link";





        student.setRegistrationConfirmation(false);
        this.studentRepository.save(student);


          String link = "http://localhost:8080/"+"confirm/"+student.getStudentId();


//        Util util = new Util();
//        util.sendmail(toEmail,message,link);
//        this.studentRepository.save(student);

          SimpleMailMessage message = new SimpleMailMessage();

          message.setFrom("gdgk2022@gmail.com");
          message.setTo(toEmail);
          message.setSubject("mail for confirmation regarding your registration for learning academy");
          message.setText(message1+link);

          this.mailSender.send(message);

          return"registration mail sent";
    }

    public String confirmRegistration(Integer myid) {

        Student studentdata = this.studentRepository.findById(myid).get();
           studentdata.setRegistrationConfirmation(true);
            this.studentRepository.save(studentdata);


          return"thank you for your confirmation your successfully enrolled ";
    }
}
