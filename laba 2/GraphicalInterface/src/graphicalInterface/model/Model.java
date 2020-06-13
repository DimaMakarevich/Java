package graphicalInterface.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Student> studentList;

    public Model(int studentsNumber) {
        if (studentsNumber > 0) {
            studentList = generateStudents(studentsNumber);
        } else {
            studentList = new ArrayList<>();
        }
    }

    private List<Student> generateStudents(int studentsNumber) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < studentsNumber; i++) {
            students.add(new Student(new FullName(RandomizationData.reqSurname(), RandomizationData.reqName(), RandomizationData.reqPatronymic()),
                    RandomizationData.reqGroupNumber(),
                    RandomizationData.reqCourseNumber(),
                    RandomizationData.reqTotalWorksNumber(),
                    RandomizationData.reqFinishedWorksCounter(),
                    RandomizationData.reqComputerLanguage()
                   ));
        }
        return students;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }
}
