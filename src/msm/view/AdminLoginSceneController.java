package msm.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import msm.Main;
import msm.MySqlConnect;

public class AdminLoginSceneController {
    private Main mainapp;
    private MySqlConnect sqlConnect;


    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField adminField;

    public void getController(Main amainapp) {
        this.mainapp = amainapp;
        sqlConnect = this.mainapp.getSqlConnect();
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void clickLoginButton(ActionEvent event) {
        String adminString = adminField.getText();
        String pwdString = passwordField.getText();
        String commandString = "select * from admininfo where admin = ? and pwd = ?";

        try {
            PreparedStatement preparedStatement = sqlConnect.connect.prepareStatement(commandString);
            preparedStatement.setString(1, adminString);
            preparedStatement.setString(2, pwdString);
            ResultSet rSet = preparedStatement.executeQuery();

            if (rSet.next()) {
                mainapp.showAdminScene();
                ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            } else {
                Main.popup("��ʾ��Ϣ", "�������", AlertType.INFORMATION);
            }

            rSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
