package uz.pdp.appjparelationships.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.entity.Student;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.payload.StudentDto;
import uz.pdp.appjparelationships.repository.AddressRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;
import uz.pdp.appjparelationships.repository.StudentRepository;
import uz.pdp.appjparelationships.repository.SubjectRepository;
import uz.pdp.appjparelationships.response.Apiresponse;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;

    public Apiresponse getAll(){
        List<Student> all = studentRepository.findAll();
        return new Apiresponse("List of Students",true,all);
    }
    public Apiresponse addStudent(StudentDto dto){
        Address address=new Address();
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        List<Subject> allById = subjectRepository.findAllById(dto.getSubjectsId());
        Group group = groupRepository.findById(dto.getGroupID()).get();
        Student student=new Student();
        student.setAddress(save);
        student.setGroup(group);
        student.setSubjects(allById);
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        Student save1 = studentRepository.save(student);
        return new Apiresponse("Added successfully",true,save1);

    }
    public Apiresponse editStudent(Integer id,StudentDto dto){
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            Address address = student.getAddress();
            address.setCity(dto.getCity());
            address.setDistrict(dto.getDistrict());
            address.setStreet(dto.getStreet());
            Address save = addressRepository.save(address);
            student.setAddress(save);
            Group group = groupRepository.findById(dto.getGroupID()).get();
            List<Subject> allById = subjectRepository.findAllById(dto.getSubjectsId());
            student.setFirstName(dto.getFirstName());
            student.setLastName(dto.getLastName());
            student.setSubjects(allById);
            student.setGroup(group);
            Student save1 = studentRepository.save(student);
            return new Apiresponse("Found and updated",true,save1);
        }
        return new Apiresponse("Student with this id not found",false);
    }
    public Apiresponse deleteStudent(Integer id){
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return new Apiresponse("Found and deleted",true);
        }
        return new Apiresponse("Student with this id not found",false);

    }
    public Apiresponse getOne(Integer id){
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            return new Apiresponse("Student",true,student);
        }
        return new Apiresponse("Student with this id not found",false);

    }
}
