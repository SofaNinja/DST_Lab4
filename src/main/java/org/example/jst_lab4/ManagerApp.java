package org.example.jst_lab4;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ManagerApp extends Application {
    private TableView<Manager> table;
    private TextField fullNameField, emailField, mobileNumberField, addressField, passportNumberField;
    private final ManagerController controller = new ManagerController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Manager app");

        table = new TableView<>();
        table.setItems(getManagerData());
        table.getColumns().addAll(createColumns());

        VBox inputForm = createInputForm();
        HBox actions = createActions();

        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(actions);
        layout.setRight(inputForm);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }

    private VBox createInputForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        fullNameField = new TextField();
        emailField = new TextField();
        mobileNumberField = new TextField();
        addressField = new TextField();
        passportNumberField = new TextField();

        fullNameField.setPromptText("Full Name");
        emailField.setPromptText("Email");
        mobileNumberField.setPromptText("Mobile Number");
        addressField.setPromptText("Address");
        passportNumberField.setPromptText("Passport Number");

        form.getChildren().addAll(
                new Label("Full Name"), fullNameField,
                new Label("Email"), emailField,
                new Label("Mobile Number"), mobileNumberField,
                new Label("Address"), addressField,
                new Label("Passport Number"), passportNumberField
        );

        return form;
    }

    private HBox createActions() {
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> handleAdd());
        updateButton.setOnAction(e -> handleUpdate());
        deleteButton.setOnAction(e -> handleDelete());

        return new HBox(10, addButton, updateButton, deleteButton);
    }

    private ObservableList<TableColumn<Manager, ?>> createColumns() {
        TableColumn<Manager, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getManagerId()));

        TableColumn<Manager, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFullName()));

        TableColumn<Manager, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));

        TableColumn<Manager, String> mobileColumn = new TableColumn<>("Mobile Number");
        mobileColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMobileNumber()));

        TableColumn<Manager, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAddress()));

        TableColumn<Manager, String> passportColumn = new TableColumn<>("Passport Number");
        passportColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPassportNumber()));

        return FXCollections.observableArrayList(idColumn, nameColumn, emailColumn, mobileColumn, addressColumn, passportColumn);
    }

    private ObservableList<Manager> getManagerData() {
        try {
            return FXCollections.observableArrayList(controller.getAllManagers());
        } catch (SQLException e) {
            showError(e.getMessage());
            return FXCollections.observableArrayList();
        }
    }

    private void handleAdd() {
        try {
            controller.addManager(new Manager(0, fullNameField.getText(), emailField.getText(), mobileNumberField.getText(),
                    addressField.getText(), passportNumberField.getText()));
            refreshTable();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void handleUpdate() {
        try {
            Manager selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setFullName(fullNameField.getText());
                selected.setEmail(emailField.getText());
                selected.setMobileNumber(mobileNumberField.getText());
                selected.setAddress(addressField.getText());
                selected.setPassportNumber(passportNumberField.getText());
                controller.updateManager(selected);
                refreshTable();
            }
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void handleDelete() {
        try {
            Manager selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.deleteManager(selected.getManagerId());
                refreshTable();
            }
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void refreshTable() {
        table.setItems(getManagerData());
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

