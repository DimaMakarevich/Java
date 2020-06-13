package graphicalInterface.appForm;

import graphicalInterface.controller.Controller;
import graphicalInterface.model.Student;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.*;

public class View {
    private final String REGEX_DIGITS_ONLY = "\\d+$";
    private Scene scene;
    private TableElement tableElement;
    private Controller controller;
    private Stage stage;
    private VBox root;

    public enum WindowType {
        DELETE, SEARCH
    }

    public View(Controller controller) {
        final int STAGE_WIDTH = 1460, STAGE_HEIGHT = 781;
        final String STAGE_TITLE_TEXT = "LABA_2";

        this.controller = controller;
        initWindow();
        stage = new Stage();
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setTitle(STAGE_TITLE_TEXT);
        stage.getIcons().add(new Image("graphicalInterface/icon.jpg"));
        stage.setScene(scene);
    }

    private void initWindow() {
        MenuItem newDocMenuItem = new MenuItem(NameConstants.NEW_DOC_MENU_ITEM_LABEL_TEXT),
                openDocMenuItem = new MenuItem(NameConstants.OPEN_DOC_MENU_ITEM_LABEL_TEXT),
                saveMenuItem = new MenuItem(NameConstants.SAVE_DOC_MENU_ITEM_LABEL_TEXT),
                addItemsMenuItem = new MenuItem(NameConstants.ADD_ITEM_MENU_ITEM_LABEL_TEXT),
                searchItemsMenuItem = new MenuItem(NameConstants.SEARCH_ITEMS_MENU_ITEM_LABEL_TEXT),
                deleteItemsMenuItem = new MenuItem(NameConstants.DELETE_ITEMS_MENU_ITEM_LABEL_TEXT),
                closeAppMenuItem = new MenuItem(NameConstants.CLOSE_APP_MENU_ITEM_LABEL_TEXT);

        Menu fileMenu = new Menu(NameConstants.FILE_MENU_LABEL_TEXT),
                editMenu = new Menu(NameConstants.EDIT_MENU_LABEL_TEXT);

        MenuBar menuBar = new MenuBar();

        Button newDocButton = new Button(NameConstants.NEW_DOC_BUTTON_LABEL_TEXT),
                openDocButton = new Button(NameConstants.OPEN_DOC_BUTTON_LABEL_TEXT),
                saveDocButton = new Button(NameConstants.SAVE_DOC_BUTTON_LABEL_TEXT),
                addItemsButton = new Button(NameConstants.ADD_ITEMS_BUTTON_LABEL_TEXT),
                searchItemsButton = new Button(NameConstants.SEARCH_ITEMS_BUTTON_LABEL_TEXT),
                deleteItemsButton = new Button(NameConstants.DELETE_ITEMS_BUTTON_LABEL_TEXT);

        ToolBar instruments;

        fileMenu.getItems().addAll(newDocMenuItem, openDocMenuItem, saveMenuItem, new SeparatorMenuItem(), closeAppMenuItem);
        editMenu.getItems().addAll(addItemsMenuItem, new SeparatorMenuItem(), searchItemsMenuItem, deleteItemsMenuItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);
        instruments = new ToolBar(newDocButton, openDocButton, saveDocButton, new Separator(), addItemsButton, searchItemsButton, deleteItemsButton);

        tableElement = new TableElement(controller.getStudentList()); /// опредлить нужный конструктор
        root = new VBox();

        root.getChildren().addAll(menuBar, instruments, tableElement.get());

        scene = new Scene(root);

        newDocButton.setOnAction(e -> newDoc());
        newDocMenuItem.setOnAction(e -> newDoc());
        openDocButton.setOnAction(e -> openDoc());
        openDocMenuItem.setOnAction(e -> openDoc());
        saveDocButton.setOnAction(e -> saveDoc());
        saveMenuItem.setOnAction(e -> saveDoc());
        addItemsButton.setOnAction(e -> addItems());
        addItemsMenuItem.setOnAction(e -> addItems());
        searchItemsButton.setOnAction(e -> searchItems());
        searchItemsMenuItem.setOnAction(e -> searchItems());
        deleteItemsButton.setOnAction(e -> deleteItems());
        deleteItemsMenuItem.setOnAction(e -> deleteItems());
        closeAppMenuItem.setOnAction(e -> Platform.exit());
    }

    public Stage getStage() {
        return stage;
    }

    private void newDoc() {
        final String ENTRY_NUM_LABEL_TEXT = "Сгенерировать записей",

                NEW_DOC_WINDOW_TITLE_TEXT = "Сгенерировать новый документ";
        TextField entNumField = new TextField("0");
        GridPane grid = new GridPane();
        Pane root = new VBox();
        Alert newDocWindow;

        grid.addRow(0, new Label(ENTRY_NUM_LABEL_TEXT), entNumField);
        root.getChildren().addAll(grid);

        newDocWindow = createEmptyCloseableDialog();
        newDocWindow.setTitle(NEW_DOC_WINDOW_TITLE_TEXT);
        newDocWindow.getDialogPane().setContent(root);
        newDocWindow.show();


        ((Button) newDocWindow.getDialogPane().lookupButton(newDocWindow.getButtonTypes().get(0))).setOnAction(ae -> {
            int entitiesNumber = 0;

            if (!entNumField.getText().isEmpty() & entNumField.getText().matches(REGEX_DIGITS_ONLY)) {
                entitiesNumber = Integer.valueOf(entNumField.getText());
            }
            controller.newDoc(entitiesNumber);

            this.root.getChildren().remove(tableElement.get());
            tableElement = new TableElement(controller.getStudentList());
            this.root.getChildren().addAll(
                    tableElement.get()
            );

            newDocWindow.close();
        });
    }

    private void openDoc() {
        FileChooser openDocChooser = new FileChooser();

        openDocChooser.setTitle("Открыть документ");
        openDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-документ", "*.xml")
        );

        try {
            controller.openDoc(openDocChooser.showOpenDialog(stage));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        tableElement.rewriteDefaultList(controller.getStudentList());
        tableElement.resetToDefaultItems();
    }

    private void saveDoc() {
        FileChooser saveDocChooser = new FileChooser();

        saveDocChooser.setTitle("Сохранить документ");
        saveDocChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.*"),
                new FileChooser.ExtensionFilter("XML-документ", "*.xml")
        );
        controller.saveDoc(saveDocChooser.showSaveDialog(stage));
    }

    private void addItems() {
        TextField surnameField = new TextField(),
                nameField = new TextField(),
                patronymicField = new TextField(),
                groupNumberField = new TextField(),
                courseNumberField = new TextField(),
                totalWorksCounter = new TextField(),
                finishedWorksNumber = new TextField(),
                computerLanguage = new TextField();

        GridPane root = new GridPane();
        Alert addItemWindow;

        root.addRow(0, new Label(NameConstants.SURNAME_LABEL_TEXT), surnameField);
        root.addRow(1, new Label(NameConstants.NAME_LABEL_TEXT), nameField);
        root.addRow(2, new Label(NameConstants.PATRONYMIC_LABEL_TEXT), patronymicField);
        root.addRow(3, new Label(NameConstants.COURSE_NUMBER_LABEL_TEXT), courseNumberField);
        root.addRow(4, new Label(NameConstants.GROUP_LABEL_TEXT), groupNumberField);
        root.addRow(5, new Label(NameConstants.TOTAL_WORKS_LABEL_TEXT), totalWorksCounter);
        root.addRow(6, new Label(NameConstants.FINISHED_WORKS_LABEL_TEXT), finishedWorksNumber);
        root.addRow(7, new Label(NameConstants.COMPUTER_LANGUAGE_LABEL_TEXT), computerLanguage);

        addItemWindow = createEmptyCloseableDialog();
        addItemWindow.setTitle(NameConstants.WINDOW_TITLE_TEXT);
        addItemWindow.getDialogPane().setContent(root);
        addItemWindow.show();

        ((Button) addItemWindow.getDialogPane().lookupButton(addItemWindow.getButtonTypes().get(0))).setOnAction(ae -> {
            controller.addStudent(
                    surnameField.getText(),
                    nameField.getText(),
                    patronymicField.getText(),
                    groupNumberField.getText(),
                    Integer.parseInt(courseNumberField.getText()),
                    Integer.parseInt(totalWorksCounter.getText()),
                    Integer.parseInt(finishedWorksNumber.getText()),
                    computerLanguage.getText()
            );

            tableElement.resetToDefaultItems();
            addItemWindow.close();
        });
    }

    private void searchItems() {
        final String WINDOW_TITLE_TEXT = "Искать";
        Alert searchItemsWindow;
        RequestElement requestElement = new RequestElement(WindowType.SEARCH, controller);

        searchItemsWindow = createEmptyCloseableDialog();
        searchItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        searchItemsWindow.getDialogPane().setContent(requestElement.get());
        searchItemsWindow.show();

        ((Button) searchItemsWindow.getDialogPane().lookupButton(searchItemsWindow.getButtonTypes().get(0))).setOnAction(
                ae -> searchItemsWindow.close()
        );
    }

    private void deleteItems() {
        final String WINDOW_TITLE_TEXT = "Удалить";
        Alert deleteItemsWindow;
        RequestElement requestElement = new RequestElement(WindowType.DELETE, controller);

        deleteItemsWindow = createEmptyCloseableDialog();
        deleteItemsWindow.setTitle(WINDOW_TITLE_TEXT);
        deleteItemsWindow.getDialogPane().setContent(requestElement.get());
        deleteItemsWindow.show();

        ((Button) deleteItemsWindow.getDialogPane().lookupButton(deleteItemsWindow.getButtonTypes().get(0))).setOnAction(ae -> {
            createDeleteInfoWindow(String.valueOf(requestElement.search().size()));
            controller.delete(requestElement.search());
            tableElement.resetToDefaultItems();
            deleteItemsWindow.close();
        });
    }

    private void createDeleteInfoWindow(String deleteInfo) {
        final String CLOSE_BUTTON_LABEL_TEXT = "ОК";
        ButtonType closeButton = new ButtonType(CLOSE_BUTTON_LABEL_TEXT);
        Alert window = new Alert(Alert.AlertType.NONE);
        VBox vertical = new VBox();

        vertical.getChildren().add(new Label("Удалено " + deleteInfo + " объектов."));
        window.getDialogPane().setContent(vertical);
        window.getButtonTypes().addAll(closeButton);
        window.show();
    }

    private Alert createEmptyCloseableDialog() {
        final String CLOSE_BUTTON_LABEL_TEXT = "Далее";
        ButtonType closeButton = new ButtonType(CLOSE_BUTTON_LABEL_TEXT);
        Alert window = new Alert(Alert.AlertType.NONE);
        window.setHeight(40);
        window.getButtonTypes().addAll(closeButton);
        return window;
    }
}
