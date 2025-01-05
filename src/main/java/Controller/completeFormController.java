package Controller;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.task;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class completeFormController implements Initializable {

    public Label greatingLbl;
    public ImageView userImg;
    @FXML
    private JFXListView completedLisView;
    public static int userId;

    @FXML
    void btnCloseOnClickAction(ActionEvent event) {

    }

    @FXML
    void btnLogOutClickAction(ActionEvent event) {

    }

    public void setGreeting(){
        String username = taskController.getInstance().getUserName(userId);
        greatingLbl.setText("Hello "+username+" !");
        if (username.equals("shalani")){
            Image newImage = new Image("img/fUser.png"); // Provide valid image path
            userImg.setImage(newImage);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGreeting();
        loadTable();

    }

    public void loadTable(){
        List<task> comTaskList = completeController.getInstance().getAllComTasks(userId);

        for(task tsk:comTaskList){
            addToVBox(tsk.getTaskName(),tsk.getDate());
        }
    }
    public static void setUserId(int usrId){
        userId = usrId;
    }

    public void addToVBox(String taskName, String date) {
        System.out.println("Task: " + taskName + ", Date: " + date.toString());

        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.getStyleClass().add("hbox-task");

        Label tsName = new Label("Task: "+taskName);
        tsName.getStyleClass().add("label-task");

        CheckBox Pending = new CheckBox("Delete ?");
        Pending.getStyleClass().add("checkbox-complete");

        Label lbldate = new Label("Completed Date:  "+date.toString());
        lbldate.getStyleClass().add("label-date");

        Pending.setOnAction(actionEvent -> {
            if (Pending.isSelected()){
                completedLisView.getItems().remove(hbox);
                remove(taskName);
            }
        });

        hbox.getChildren().addAll(tsName,lbldate,Pending);
        completedLisView.getItems().add(hbox);

    }

    public void remove(String tskName){
        completeController.getInstance().remove(tskName);


    }

    public void btnReloadOnClickAction(ActionEvent actionEvent) {
        loadTable();
    }
}
