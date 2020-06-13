package graphicalInterface.model;

public class FullName {

    private String surname, name, patronymic;

    public FullName(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public FullName(String alignForm){
        String[] splitForm = alignForm.split("\\s+");
        if(splitForm[0] != null){
            surname = splitForm[0];
        } else {
            surname = "";
        }
        if(splitForm[1] != null){
            name = splitForm[1];
        } else {
            name = "";
        }
        if(splitForm[2] != null){
            patronymic = splitForm[2];
        } else {
            patronymic = "";
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
