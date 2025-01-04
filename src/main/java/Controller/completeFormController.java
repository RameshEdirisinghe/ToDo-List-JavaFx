package Controller;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.task;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class completeFormController implements Initializable {

    @FXML
    private JFXListView completedLisView;

    @FXML
    void btnCloseOnClickAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutClickAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<task> comTaskList = completeController.getInstance().getAllComTasks();

        for(task tsk:comTaskList){
            addToVBox(tsk.getTaskName(),tsk.getDate());
        }
    }

    public void addToVBox(String taskName, String date) {
        System.out.println("Task: " + taskName + ", Date: " + date.toString());

        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.getStyleClass().add("hbox-task");

        Label tsName = new Label("Task: "+taskName);
        tsName.getStyleClass().add("label-task");

        CheckBox Pending = new CheckBox("Pending ?");
        Pending.getStyleClass().add("checkbox-complete");

        Label lbldate = new Label("Completed Date:  "+date.toString());
        lbldate.getStyleClass().add("label-date");

        Pending.setOnAction(actionEvent -> {
            if (Pending.isSelected()){
                completedLisView.getItems().remove(hbox);
            }
        });

        hbox.getChildren().addAll(tsName,lbldate,Pending);
        completedLisView.getItems().add(hbox);

    }

    public void btnReloadOnClickAction(ActionEvent actionEvent) {
    }
}
