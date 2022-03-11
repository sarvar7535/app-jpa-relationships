package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.repository.StudentRepository;
import uz.pdp.appjparelationships.response.Apiresponse;
import uz.pdp.appjparelationships.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @GetMapping
    public Apiresponse all(){
        return studentService.getAll();
    }
    @PostMapping
    public Apiresponse add(@RequestBody StudentDto dto){
        return studentService.addStudent(dto);
    }
    @PutMapping("/{id}")
    public Apiresponse edit(@PathVariable Integer id,@RequestBody StudentDto dto){
        return studentService.editStudent(id, dto);
    }
    @DeleteMapping("/{id}")
    public Apiresponse delete(@PathVariable Integer id){
        return studentService.deleteStudent(id);

    }
    @GetMapping("/{id}")
    public Apiresponse one(@PathVariable Integer id){
        return studentService.getOne(id);
        
    }
    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    //3. FACULTY DEKANAT
    //4. GROUP OWNER


}
