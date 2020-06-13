package graphicalInterface.model;

public class Student {

    private FullName fullName;
    private String groupNumber;
    private int courseNumber;
    private String computerLanguage;
    private int finishedWorksCounter;
    private int totalWorksNumber;

    public Student(FullName fullName, String groupNumber, int courseNumber, int totalWorksNumber, int finishedWorksCounter, String computerLanguage) {
        this.fullName = fullName;
        this.groupNumber = groupNumber;
        this.courseNumber = courseNumber;
        this.totalWorksNumber = totalWorksNumber;
        this.finishedWorksCounter = finishedWorksCounter;
        this.computerLanguage = computerLanguage;

    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getAlignSnp(){
        return fullName.getSurname() + " " + fullName.getName() + " " + fullName.getPatronymic();
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public String getComputerLanguage() {
        return computerLanguage;
    }

    public void setComputerLanguage(String computerLanguage) {
        this.computerLanguage = computerLanguage;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getFinishedWorksCounter() {
        return finishedWorksCounter;
    }

    public void setFinishedWorksCounter(int finishedWorksCounter) {
        this.finishedWorksCounter = finishedWorksCounter;
    }

    public int getTotalWorksNumber() {
        return totalWorksNumber;
    }

    public void setTotalWorksNumber(int totalWorksNumber) {
        this.totalWorksNumber = totalWorksNumber;
    }
}
