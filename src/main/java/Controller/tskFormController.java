package Controller;


import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.task;
import javafx.scene.image.Image;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class tskFormController implements Initializable {

    public JFXListView completeListView;
    public JFXListView todoListView;
    public static int userId;
    public Label greetinLbl;
    public ImageView userImg;
    public Label taskCountLbl;
    @FXML
    private DatePicker dateField;

    @FXML
    private TextField txtTaskName;

    @FXML
    void btnAddOnClickAction(ActionEvent event) {
        String taskName = txtTaskName.getText();
        LocalDate selectedDate = dateField.getValue();
        if (selectedDate != null && taskName != null && !taskName.trim().isEmpty()) {
            if(taskController.getInstance().saveToDb(userId,taskName,selectedDate.toString())){
                setTaskCount();
                new Alert(Alert.AlertType.INFORMATION,"ADDED").show();
            }else{
                System.out.println("watun naa");
            }

            addToVBox(taskName, selectedDate.toString());
        } else {
            System.out.println("Please fill out both fields.");
        }
    }

    public void setGreeting(){
        String username = taskController.getInstance().getUserName(userId);
        greetinLbl.setText("Hello "+username+" !");
        if (username.equals("shalani")){
            Image newImage = new Image("img/fUser.png"); // Provide valid image path
            userImg.setImage(newImage);
        }
    }

    public void setTaskCount(){
        int count =taskController.getInstance().getTaskCount(userId);
        taskCountLbl.setText("You have "+count+" tasks remaining");
    }

    @FXML
    void btnCompleteOnClickAction(ActionEvent event) {
        Stage st = new Stage();
        try {
            completeFormController.setUserId(userId);
            st.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/completeForm.fxml"))));
            st.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUserId(int usrId){
        userId = usrId;
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
                setTaskCount();
            }
        });

        hbox.getChildren().addAll(tsName,lbldate,done);
        todoListView.getItems().add(hbox);

    }

    public void remove(String tskName){
        taskController.getInstance().addCompleteTable(tskName);
        taskController.getInstance().remove(tskName);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGreeting();
        setTaskCount();
        loadTable();

    }

    public void loadTable(){
        List<task> taskList = taskController.getInstance().getAllTasks(userId);

        for(task tsk:taskList){
            addToVBox(tsk.getTaskName(),tsk.getDate());
        }
    }


    public void btnLogOutOnClickAction(ActionEvent actionEvent) {

    }

    public void btnCloseOnClickAction(ActionEvent actionEvent) {
    }
}

