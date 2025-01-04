package Controller;


import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class tskFormController implements Initializable {

    public JFXListView completeListView;
    public JFXListView todoListView;
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField txtTaskName;

    @FXML
    void btnAddOnClickAction(ActionEvent event) {
        String taskName = txtTaskName.getText();
        LocalDate selectedDate = dateField.getValue();
        if (selectedDate != null && taskName != null && !taskName.trim().isEmpty()) {
            if(taskController.getInstance().saveToDb(1,taskName,selectedDate.toString())){
                new Alert(Alert.AlertType.INFORMATION,"ADDED").show();
            }else{
                System.out.println("watun naa");
            }

            addToVBox(taskName, selectedDate.toString());
        } else {
            System.out.println("Please fill out both fields.");
        }
    }

    @FXML
    void btnCompleteOnClickAction(ActionEvent event) {
        Stage st = new Stage();
        try {
            st.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/completeForm.fxml"))));
            st.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToVBox(String taskName, String date) {
        System.out.println("Task:   " + taskName + ", Date: " + date.toString());

        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.getStyleClass().add("hbox-task");

        Label tsName = new Label("Task: "+taskName);
        tsName.getStyleClass().add("label-task");

        CheckBox done = new CheckBox("Done ?");
        done.getStyleClass().add("checkbox-complete");

        Label lbldate = new Label("Planned Date:  "+date.toString());
        lbldate.getStyleClass().add("label-date");

        done.setOnAction(actionEvent -> {
            if (done.isSelected()){
                todoListView.getItems().remove(hbox);
                remove(taskName);
            }
        });

        hbox.getChildren().addAll(tsName,lbldate,done);
        todoListView.getItems().add(hbox);

    }

    public void remove(String tskName){
        taskController.getInstance().remove(tskName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<task> taskList = taskController.getInstance().getAllTasks();

        for(task tsk:taskList){
            addToVBox(tsk.getTaskName(),tsk.getDate());
        }
    }

    public void btnLogOutOnClickAction(ActionEvent actionEvent) {
    }

    public void btnCloseOnClickAction(ActionEvent actionEvent) {
    }
}

