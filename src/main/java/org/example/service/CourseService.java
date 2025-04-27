package org.example.service;


import org.example.model.Course;
import org.example.model.CourseRegistry;
import org.example.repository.CourseReg;
import org.example.repository.Courserepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    Courserepo courserepo;

    @Autowired
    CourseReg coursereg;

    public List<Course> availableCourses() {
        return courserepo.findAll();
    }

    public List<CourseRegistry> enrolledStudents() {
        return coursereg.findAll();
    }

    public void enrollCourse(String name, String emailId, String courseName) {
        CourseRegistry courseRegistry = new CourseRegistry(name,emailId,courseName);
        coursereg.save(courseRegistry);
    }

    public String deletebyid(int id) {
        if (coursereg.existsById(id)) {
            coursereg.deleteById(id);
            return "Deleted Successfully";
        } else {
            return "Course with name '" + id + "' not found.";
        }
    }


    public String deleteall() {
        coursereg.deleteAll();
        return "Deleted all Success";
    }

    public String deleteallcourse() {
        courserepo.deleteAll();
        return "Deleted all course success";
    }

    public Optional<CourseRegistry> getById(int id) {
        return coursereg.findById(id);
    }

    public String deletebytrainerid(String id) {
        if(courserepo.existsById(id)) {
            courserepo.deleteById(id);
        }
            return "No data Exists";
    }

    public String addTrainer(String id, String name, String trainer, int weeks) {
        Course course=new Course(id,name,trainer,weeks);
        courserepo.save(course);
        return "saved Success";

    }

    public String updateStudent(CourseRegistry reg) {
        Optional<CourseRegistry> existingCourse=coursereg.findById(reg.getId());
        if(existingCourse.isPresent()){
            coursereg.save(reg);
            return "data updated successfully";
        }
        else{
            return "no data";
        }
    }

    public String updateCourses(Course course) {
        Optional<Course> existId=courserepo.findById(course.getCourseId());
        if(existId.isPresent()){
            courserepo.save(course);
            return "updated Success";
        }
        else{
            return "No data exists";
        }
    }
}

