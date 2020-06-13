package graphicalInterface.appForm;

import graphicalInterface.controller.Controller;
import graphicalInterface.model.Student;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.*;

public class RequestElement {
    final String CRITERIA_1 = "ФИО студента или группа",
            CRITERIA_2 = "Курсу или языку программирования",
            CRITERIA_3 = "Количество выполненных работ или общее число работ",
            CRITERIA_4 = "Количество не выполненных работ";
    private String selectedItem;
    private ComboBox criteriaComBox;
    private Button searchButton;
    private TableElement tableElement;
    private GridPane grid;
    private Pane criteriaChooser, root;
    private List<Label> criteria1LabelList, criteria2LabelList, criteria3LabelList, criteria4LabelList;
    private List<TextField> criteria1FieldList, criteria2FieldList, criteria3FieldList, criteria4FieldList;
    private List<ComboBox> criteria2Combobox, criteria3Combobox;
    private Controller controller;

    public RequestElement(View.WindowType windowType,Controller controller) {
        this.controller=controller;
        criteriaComBox = new ComboBox();
        criteriaComBox.getItems().addAll(CRITERIA_1, CRITERIA_2, CRITERIA_3, CRITERIA_4);
        criteriaComBox.setValue(CRITERIA_1);
        searchButton = new Button("Искать");
        criteriaChooser = new HBox();
        criteria1LabelList = new ArrayList<>();
        criteria1FieldList = new ArrayList<>();
        criteria2LabelList = new ArrayList<>();
        criteria2FieldList = new ArrayList<>();
        criteria3LabelList = new ArrayList<>();
        criteria3FieldList = new ArrayList<>();
        criteria4LabelList = new ArrayList<>();
        criteria4FieldList = new ArrayList<>();
        criteria2Combobox = new ArrayList<>();
        criteria3Combobox = new ArrayList<>();
        initCriteriaLists();
        grid = new GridPane();
        switchPreset();
        tableElement = new TableElement(new ArrayList<>());
        this.root = new VBox();

        if (windowType == View.WindowType.SEARCH) {
            criteriaChooser.getChildren().addAll(new Label("Критерии поиска: "), criteriaComBox, searchButton);
            this.root.getChildren().addAll(
                    new Separator(),
                    new Separator(),
                    criteriaChooser,
                    grid,
                    new Separator(),
                    new Separator(),
                    tableElement.get(),
                    new Separator()
            );
        }

        if (windowType == View.WindowType.DELETE) {
            criteriaChooser.getChildren().addAll(new Label("Критерии поиска: "), criteriaComBox);
            this.root.getChildren().addAll(new Separator(), new Separator(), criteriaChooser, grid);
        }

        criteriaComBox.setOnAction(ae -> switchPreset());
        searchButton.setOnAction(ae -> {
            List<Student> studentList = search();
            cleanSearch();
            tableElement.setObservableList(studentList);
        });
    }

    private void switchPreset() {
        final int CRITERIA_1_FIELD_NUMBER = 2,
                CRITERIA_2_FIELD_NUMBER = 1,
                CRITERIA_3_FIELD_NUMBER = 2,
                CRITERIA_4_FIELD_NUMBER = 1;

        grid.getChildren().clear();
        selectedItem = criteriaComBox.getSelectionModel().getSelectedItem().toString();
        switch (selectedItem) {
            case CRITERIA_1:
                for (int i = 0; i < CRITERIA_1_FIELD_NUMBER; i++) {
                    grid.addRow(i, criteria1LabelList.get(i), criteria1FieldList.get(i));
                }
                break;
            case CRITERIA_2:
                for (int i = 0; i < CRITERIA_2_FIELD_NUMBER; i++) {
                    grid.addRow(i, criteria2LabelList.get(i), criteria2FieldList.get(i));
                }
                grid.addRow(1, criteria2LabelList.get(1), criteria2Combobox.get(0));

                break;
            case CRITERIA_3:
                for (int i = 0; i < CRITERIA_3_FIELD_NUMBER; i++) {
                    grid.addRow(i, criteria3LabelList.get(i), criteria3Combobox.get(i));
                }
                break;
            case CRITERIA_4:
                for (int i = 0; i < CRITERIA_4_FIELD_NUMBER; i++) {
                    grid.addRow(i, criteria4LabelList.get(i), criteria4FieldList.get(i));
                }
                break;
        }
    }

    private void initCriteriaLists() {
        final String SNP_LABEL_TEXT = "ФИО: ",
                GROUP_LABEL_TEXT = "Номер группы: ",
                COURSE_LABEL_TEXT = "Курс: ",
                COMPUTER_LANGUAGE_LABEL_TEXT = "Язык программирования: ",
                FINISHED_WORKS_LABEL_TEXT = "Количетсво выполненных работ: ",
                TOTAL_WORKS_LABEL_TEXT = "Общее количество работ: ",
                NOT_COMPLETED_WORKS_LABEL_TEXT = "Число не выполненных работ";

        criteria1LabelList.add(new Label(SNP_LABEL_TEXT));
        criteria1LabelList.add(new Label(GROUP_LABEL_TEXT));
        criteria1FieldList.add(new TextField());
        criteria1FieldList.add(new TextField());

        criteria2LabelList.add(new Label(COURSE_LABEL_TEXT));
        criteria2LabelList.add(new Label(COMPUTER_LANGUAGE_LABEL_TEXT));
        criteria2FieldList.add(new TextField());
        criteria2Combobox.add(getLanguageList());

        criteria3LabelList.add(new Label(FINISHED_WORKS_LABEL_TEXT));
        criteria3LabelList.add(new Label(TOTAL_WORKS_LABEL_TEXT));
        criteria3Combobox.add(getFinishedWorksNumber());
        criteria3Combobox.add(getTotalWorksNumber());

        criteria4LabelList.add(new Label(NOT_COMPLETED_WORKS_LABEL_TEXT));
        criteria4FieldList.add(new TextField());
    }

    public ComboBox<String> getTotalWorksNumber() {
        List<Student> studentList = controller.getStudentList();
        ComboBox<String> box = new ComboBox();
        TreeSet<Integer> set = new TreeSet();
        List<String> stringSet = new ArrayList<>();
        for (Student studentCounter : studentList) {
            set.add(studentCounter.getTotalWorksNumber());
        }

        Iterator<Integer> i = set.iterator();
        while (i.hasNext()) {
            stringSet.add(i.next().toString());
        }

        box.getItems().addAll(stringSet);
        return box;
    }

    public ComboBox<String> getFinishedWorksNumber() {
        List<Student> studentList = controller.getStudentList();
        ComboBox<String> box = new ComboBox();
        TreeSet<Integer> set = new TreeSet();
        List<String> stringSet = new ArrayList<>();
        for (Student studentCounter : studentList) {
            set.add(studentCounter.getFinishedWorksCounter());
        }

        Iterator<Integer> i = set.iterator();
        while (i.hasNext()) {
            stringSet.add(i.next().toString());
        }

        box.getItems().addAll(stringSet);
        return box;
    }


    public ComboBox<String> getLanguageList() {
        List<Student> studentList = controller.getStudentList();
        ComboBox<String> box = new ComboBox();
        Set<String> set = new HashSet<>();

        for (Student studentCounter : studentList) {
            set.add(studentCounter.getComputerLanguage());
        }

        box.getItems().addAll(set);
        return box;

    }

    public Pane get() {
        return this.root;
    }

    public void cleanSearch() {
        criteria3Combobox.get(0).getSelectionModel().clearSelection();
        criteria3Combobox.get(1).getSelectionModel().clearSelection();
        criteria2Combobox.get(0).getSelectionModel().clearSelection();
    }

    public List search() {
        List criteriaList;
        criteriaList = new ArrayList<String>();
        criteriaList.add(criteria1FieldList.get(0).getText());
        criteriaList.add(criteria1FieldList.get(1).getText());
        criteriaList.add(criteria2FieldList.get(0).getText());
        criteriaList.add(criteria2Combobox.get(0).getValue());
        criteriaList.add(criteria3Combobox.get(0).getValue());
        criteriaList.add(criteria3Combobox.get(1).getValue());
        criteriaList.add(criteria4FieldList.get(0).getText());

        return controller.search(selectedItem, criteriaList);
    }
}
