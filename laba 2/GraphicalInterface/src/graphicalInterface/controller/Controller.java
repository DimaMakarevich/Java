package graphicalInterface.controller;

import graphicalInterface.model.Model;
import graphicalInterface.model.FullName;
import graphicalInterface.model.Student;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public List<Student> getStudentList() {
        return model.getStudentList();
    }

    public void newDoc(int entitiesNumber) {
        this.model = new Model(entitiesNumber);
    }

    public void addStudent(String surname, String name, String patronymic, String groupNumber, int courseNumber, int totalWorksNumber, int finishedWorksCounter, String computerLanguage) {
        model.addStudent(new Student(new FullName(surname, name, patronymic), groupNumber, courseNumber, totalWorksNumber, finishedWorksCounter, computerLanguage));
    }

    public void openDoc(File file) {
        try {
            model.setStudentList(DocOpener.openDoc(file));
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public void saveDoc(File file) {
        List<Student> studentList = model.getStudentList();
        Element students, student, snp, group, course, totalWorks, finishedWorks, computerLanguage;
        Attr surname, name, patronymic, groupNumber, courseNumber, totalWorksNumber, finishedWorksCounter, tittle;

        Document document;
        DocumentBuilderFactory documentBuilderFactory;
        DocumentBuilder documentBuilder;
        TransformerFactory transformerFactory;
        Transformer transformer;
        DOMSource source;
        StreamResult streamResult;

        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            students = document.createElement("students");
            document.appendChild(students);

            for (Student studentCounter : studentList) {
                surname = document.createAttribute("surname");
                surname.setValue(studentCounter.getFullName().getSurname());

                name = document.createAttribute("name");
                name.setValue(studentCounter.getFullName().getName());

                patronymic = document.createAttribute("patronymic");
                patronymic.setValue(studentCounter.getFullName().getPatronymic());

                snp = document.createElement("snp");
                snp.setAttributeNode(surname);
                snp.setAttributeNode(name);
                snp.setAttributeNode(patronymic);


                group = document.createElement("group");
                groupNumber = document.createAttribute("groupNumber");
                groupNumber.setValue(studentCounter.getGroupNumber());
                group.setAttributeNode(groupNumber);

                course = document.createElement("course");
                courseNumber = document.createAttribute("courseNumber");
                courseNumber.setValue(((Integer) studentCounter.getCourseNumber()).toString());
                course.setAttributeNode(courseNumber);

                totalWorks = document.createElement("totalWorks");
                totalWorksNumber = document.createAttribute("totalWorksNumber");
                totalWorksNumber.setValue(((Integer) studentCounter.getTotalWorksNumber()).toString());
                totalWorks.setAttributeNode(totalWorksNumber);

                finishedWorks = document.createElement("finishedWorks");
                finishedWorksCounter = document.createAttribute("finishedWorksCounter");
                finishedWorksCounter.setValue(((Integer) studentCounter.getFinishedWorksCounter()).toString());
                finishedWorks.setAttributeNode(finishedWorksCounter);

                computerLanguage = document.createElement("computerLanguage");
                tittle = document.createAttribute("tittle");
                tittle.setValue((studentCounter.getComputerLanguage()));
                computerLanguage.setAttributeNode(tittle);

                student = document.createElement("student");
                student.appendChild(snp);
                student.appendChild(course);
                student.appendChild(group);
                student.appendChild(totalWorks);
                student.appendChild(finishedWorks);
                student.appendChild(computerLanguage);

                students.appendChild(student);

            }

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(document);
            streamResult = new StreamResult(file);

            transformer.transform(source, streamResult);

        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }

    }

    public List<Student> search(String selectedItem, List<String> criteriaList) {
        final String CRITERIA_1 = "ФИО студента или группа",
                CRITERIA_2 = "Курсу или языку программирования",
                CRITERIA_3 = "Количество выполненных работ или общее число работ",
                CRITERIA_4 = "Количество не выполненных работ";

        List<Student> studentList = getStudentList();
        List resultList = new ArrayList<Student>();

        switch (selectedItem) {
            case CRITERIA_1:
                final FullName fullName = new FullName(criteriaList.get(0));
                final String groupNumber = criteriaList.get(1);

                if (groupNumber == null)// если строка пуста то значит мы передаил имя и фамилию
                {
                    for (Student studentCounter : studentList) {
                        if (studentCounter.getFullName().getSurname().equals(fullName.getSurname()) &&
                                studentCounter.getFullName().getName().equals(fullName.getName()) && studentCounter.getFullName().getPatronymic().equals(fullName.getPatronymic())) {
                            resultList.add(studentCounter);
                        }
                    }
                } else {
                    for (Student studentCounter : studentList) {
                        if (studentCounter.getGroupNumber().equals(groupNumber)) {
                            resultList.add(studentCounter);
                        }
                    }
                }
                break;
            case CRITERIA_2:
                final String courseNumber = criteriaList.get(2);
                final String computerLanguage = criteriaList.get(3);

                if (courseNumber == null) {
                    for (Student studentCounter : studentList) {
                        if (studentCounter.getComputerLanguage().equals(computerLanguage)) {
                            resultList.add(studentCounter);
                        }
                    }
                } else {
                    for (Student studentCounter : studentList) {
                        if (((Integer) studentCounter.getCourseNumber()).toString().equals(courseNumber)) {
                            resultList.add(studentCounter);
                        }
                    }
                }

                break;
            case CRITERIA_3:
                final String finishedWorksCounter = criteriaList.get(4);
                final String totalWorksNumber = criteriaList.get(5);

                if (finishedWorksCounter == null) {
                    for (Student studentCounter : studentList) {
                        if (studentCounter.getTotalWorksNumber() == (Integer.parseInt(totalWorksNumber))) {
                            resultList.add(studentCounter);
                        }
                    }
                } else {
                    for (Student studentCounter : studentList) {
                        if (studentCounter.getFinishedWorksCounter() == Integer.parseInt(finishedWorksCounter)) {
                            resultList.add(studentCounter);
                        }
                    }
                }
                break;
            case CRITERIA_4:
                final String notCompletedWorksNumber = criteriaList.get(6);

                for (Student studentCounter : studentList) {
                    int deferenceOfTotalAndFinishedWorksNumber = studentCounter.getTotalWorksNumber() - studentCounter.getFinishedWorksCounter();
                    String tempNumber = ((Integer) deferenceOfTotalAndFinishedWorksNumber).toString();
                    if (tempNumber.equals(notCompletedWorksNumber)) {
                        resultList.add(studentCounter);
                    }
                }
                break;

        }

        return resultList;
    }

    public void delete(List<Student> indexList) {
        for (Student studentCounter : indexList) {
            getStudentList().remove(studentCounter);
        }
    }
}
