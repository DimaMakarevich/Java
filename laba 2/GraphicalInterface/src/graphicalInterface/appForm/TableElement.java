package graphicalInterface.appForm;

import graphicalInterface.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class TableElement {
    private int rowsOnPage, currentPage = 1, numberOfPages;
    private Label paginationLabel, itemsCountLabel;
    private Button resetSearchButton;
    private TextField rowsOnPageField;
    private TableView<Student> table;
    private ToolBar navigator, pagination;
    private Pane tableElement;
    private List<Student> defaultStudentList;
    private ObservableList<Student> studentObsList, curStudentObsList;

    public TableElement(List<Student> studentList) {
        final int TABLE_HEIGHT = 500,
                TABLE_WIDTH = 1460,
                DEFAULT_ROWS_ON_PAGE_NUMBER = 15;

        final String SNP_COLUMN_LABEL_TEXT = "ФИО студента",
                GROUP_COLUMN_LABEL_TEXT = "Группа",
                COURSE_COLUMN_LABEL_TEXT = "Курс",
                TOTAl_WORKS_COLUMN_LABEL_TEXT = "Общее число работ",
                FINISHED_WORKS_COLUMN_LABEL_TEXT = "Количество выполненных работ",
                ROWS_ON_PAGE_LABEL_TEXT = "Студентов на странице: ",
                COMPUTER_LANGUAGE_COLUMN_LABEL_TEXT = "Язык программирования",
                TO_BEGIN_BUTTON_LABEL_TEXT = "<<",
                TO_LEFT_BUTTON_LABEL_TEXT = "<",
                TO_RIGHT_BUTTON_LABEL_TEXT = ">",
                TO_END_BUTTON_LABEL_TEXT = ">>";

       // Property sProperty = new SimpleStringProperty();

        Button toBeginButton = new Button(TO_BEGIN_BUTTON_LABEL_TEXT),
                toLeftButton = new Button(TO_LEFT_BUTTON_LABEL_TEXT),
                toRightButton = new Button(TO_RIGHT_BUTTON_LABEL_TEXT),
                toEndButton = new Button(TO_END_BUTTON_LABEL_TEXT);

        TableColumn<Student, String> snpCol = new TableColumn<>(SNP_COLUMN_LABEL_TEXT),
                courseCol = new TableColumn<>(COURSE_COLUMN_LABEL_TEXT),
                groupCol = new TableColumn<>(GROUP_COLUMN_LABEL_TEXT),
                totalWorksCol = new TableColumn<>(TOTAl_WORKS_COLUMN_LABEL_TEXT),
                finishedWorksCol = new TableColumn<>(FINISHED_WORKS_COLUMN_LABEL_TEXT),
                computerLanguageCol = new TableColumn<>(COMPUTER_LANGUAGE_COLUMN_LABEL_TEXT);

        defaultStudentList = studentList;
        studentObsList = FXCollections.observableArrayList(defaultStudentList);
        curStudentObsList = FXCollections.observableArrayList();

        snpCol.setMinWidth(300);
        snpCol.setCellValueFactory(new PropertyValueFactory<>("alignSnp"));
        groupCol.setCellValueFactory(new PropertyValueFactory<>("groupNumber"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
        totalWorksCol.setCellValueFactory(new PropertyValueFactory<>("totalWorksNumber"));
        finishedWorksCol.setCellValueFactory(new PropertyValueFactory<>("finishedWorksCounter"));
        computerLanguageCol.setCellValueFactory(new PropertyValueFactory<>("computerLanguage"));

        paginationLabel = new Label();
        navigator = new ToolBar(
                toBeginButton,
                toLeftButton,
                paginationLabel,
                toRightButton,
                toEndButton
        );

        itemsCountLabel = new Label("/" + studentObsList.size() + "/");
        rowsOnPageField = new TextField();
        rowsOnPageField.setText(String.valueOf(DEFAULT_ROWS_ON_PAGE_NUMBER));
        resetSearchButton = new Button("Сбосить результаты поиска");
        resetSearchButton.setVisible(false);

        pagination = new ToolBar(
                itemsCountLabel,
                new Separator(),
                new Label(ROWS_ON_PAGE_LABEL_TEXT),
                rowsOnPageField,
                new Separator(),
                navigator,
                resetSearchButton
        );

        table = new TableView<>();
        table.setMinHeight(TABLE_HEIGHT);
        table.setMaxWidth(TABLE_WIDTH);

        table.getColumns().addAll(
                snpCol,
                courseCol,
                groupCol,
                totalWorksCol,
                finishedWorksCol,
                computerLanguageCol
        );

        table.setItems(curStudentObsList);
        setRowsOnPage();
        tableElement = new VBox();

        tableElement.getChildren().addAll(table, pagination);
        rowsOnPageField.setOnAction(ae -> setRowsOnPage());
        toBeginButton.setOnAction(ae -> goBegin());
        toLeftButton.setOnAction(ae -> goLeft());
        toRightButton.setOnAction(ae -> goRight());
        toEndButton.setOnAction(ae -> goEnd());

        resetSearchButton.setOnAction(ae -> {
            resetToDefaultItems();
            resetSearchButton.setVisible(false);
        });
    }

    public Pane get() {
        return tableElement;
    }

    public void rewriteDefaultList(List<Student> list) {
        defaultStudentList = list;
    }

    public void resetToDefaultItems() {
        setObservableList(defaultStudentList);
    }

    public void setObservableList(List<Student> list) {
        studentObsList = FXCollections.observableArrayList(list);
        resetSearchButton.setVisible(true);

        setRowsOnPage();
    }

    private void setRowsOnPage() {
        rowsOnPage = Integer.valueOf(rowsOnPageField.getText());
        currentPage = 1;

        refreshPage();
    }

    private void goBegin() {
        currentPage = 1;
        refreshPage();
    }

    private void goLeft() {
        if (currentPage > 1) {
            currentPage--;
        }
        refreshPage();
    }

    private void goRight() {
        if (currentPage < numberOfPages) {
            currentPage++;
        }
        refreshPage();
    }

    private void goEnd() {
        currentPage = numberOfPages;
        refreshPage();
    }

    private void refreshPage() {
        int fromIndex = (currentPage - 1) * rowsOnPage,
                toIndex = currentPage * rowsOnPage;

        if (toIndex > studentObsList.size()) {
            toIndex = studentObsList.size();
        }

        curStudentObsList.clear();
        curStudentObsList.addAll(studentObsList.subList(fromIndex, toIndex));

        refreshPaginationLabel();
    }

    private void refreshPaginationLabel() {
        numberOfPages = (studentObsList.size() - 1) / rowsOnPage + 1;
        paginationLabel.setText(currentPage + "/" + numberOfPages);
        itemsCountLabel.setText("/" + studentObsList.size() + "/");
    }

}
