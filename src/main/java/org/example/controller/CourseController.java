package org.example.controller;

import org.example.service.CourseService;
import org.example.model.Course;
import org.example.model.CourseRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/courses")
    public List<Course> availableCourses(){
        return service.availableCourses();
    }

    @GetMapping("/admin/courses/enrolled")
    public List<CourseRegistry> enrolledStudents(){
        return service.enrolledStudents();
    }
    @GetMapping("/admin/courses/enrolled/{id}")
    public Optional<CourseRegistry> getById(@PathVariable("id") int id){
        return service.getById(id);
    }

    @PostMapping("/courses/register")
    public String enrollCourse(@RequestParam("name") String name,
                               @RequestParam("emailId") String emailId,
                               @RequestParam("courseName") String courseName){
        service.enrollCourse(name,emailId,courseName);
        return "Congratulations! "+name+" Enrollment Successful for "+courseName;

    }
    @PostMapping("courses/trainer")
    public String addTrainer(@RequestParam("courseId") String id,@RequestParam("courseName") String name,@RequestParam("trainer") String trainer,@RequestParam("durationInWeeks") int weeks){
        return service.addTrainer(id,name,trainer,weeks);
    }

    @DeleteMapping("/admin/delete/student_id/{id}")
    public String deletebyid(@PathVariable("id") int id ){
        return service.deletebyid(id);

    }
    @DeleteMapping("/admin/delete/trainer_id/{id}")
    public String deletebytrainerid(@PathVariable("id") String id ) {
        service.deletebytrainerid(id);
        return "Trainer Deleted";
    }
    @DeleteMapping("/admin/deleteall/student")
    public String deleteallstudent(){
        return service.deleteall();
    }
    @DeleteMapping("/admin/deleteall/courses")
    public String deleteallcourse(){
        return service.deleteallcourse();
    }
    @PutMapping("/admin/put/student")
    public String updateStudent(@RequestBody CourseRegistry reg){
        return service.updateStudent(reg);

    }
    @PutMapping("/admin/put/courses")
    public String updateCourses(@RequestBody Course course){
        return service.updateCourses(course);

}}