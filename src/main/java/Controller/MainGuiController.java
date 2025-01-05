package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class MainGuiController {
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void btnOnActionLogin(ActionEvent actionEvent) {
        System.out.println("awa");
            int usrId = MainController.getInstance().login(txtUserName.getText(),txtPassword.getText());
            if(usrId!=-1){
                System.out.println("awa");
                newForm(usrId);
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Invalid user Name or Password").show();
            }
    }

    public void newForm(int usrId){
        Stage st = new Stage();
        try {
            tskFormController.setUserId(usrId);
            st.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/tskForm.fxml"))));
            st.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
