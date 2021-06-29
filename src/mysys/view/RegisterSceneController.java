package mysys.view;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mysys.Main;
import mysys.MySqlConnect;

public class RegisterSceneController {
    private MySqlConnect sqlConnect;

    @FXML
    private TextField userNameField;
    @FXML
    private TextField peopleIdField;
    @FXML
    private TextField realNameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordAgainField;


    public void getController(Main amainapp) {
        sqlConnect = amainapp.getSqlConnect();
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void clickRegister() {
        String userNameString = userNameField.getText();
        String pwdString = passwordField.getText();
        String pwdAgainString = passwordAgainField.getText();
        String peopleIdString = peopleIdField.getText();
        String realNameString = realNameField.getText();

        /*��Ϣ������*/
        if (userNameString.isEmpty() || pwdString.isEmpty() ||
                peopleIdString.isEmpty() || realNameString.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ע����Ϣ");
            alert.setHeaderText(null);
            alert.setContentText("��������Ϣ��");

            alert.showAndWait();
        } else {

            if (pwdString.equals(pwdAgainString)) {
                String commandString = "select * from userinfo where name = ? or user_id = ? ";
                try {
                    PreparedStatement preparedStatement =
                            sqlConnect.connect.prepareStatement(commandString);

                    preparedStatement.setString(1, userNameString);
                    preparedStatement.setString(2, peopleIdString);
                    ResultSet rSet = preparedStatement.executeQuery();

                    if (rSet.next()) {
                        rSet.close();
                        preparedStatement.close();

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ע����Ϣ");
                        alert.setHeaderText(null);
                        alert.setContentText("�û����Ѵ��ڻ�����֤��ע�ᣡ");

                        alert.showAndWait();
                    } else {
                        rSet.close();
                        preparedStatement.close();

                        commandString = "insert into userinfo(name, pwd, user_id, balance, real_name) "
                                + "values(?, ?, ?, 0, ?)";

                        preparedStatement = sqlConnect.connect.prepareStatement(commandString);
                        preparedStatement.setString(1, userNameString);
                        preparedStatement.setString(2, pwdString);

                        /*�������֤����*/
                        preparedStatement.setString(3, peopleIdString);
                        preparedStatement.setString(4, realNameString);

                        int rtn = preparedStatement.executeUpdate();
                        if (rtn > 0) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("ע����Ϣ");
                            alert.setHeaderText(null);
                            alert.setContentText("ע��ɹ���");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ע����Ϣ");
                            alert.setHeaderText(null);
                            alert.setContentText("ע��ʧ�ܣ�");

                            alert.showAndWait();
                        }

                        preparedStatement.close();

                    }

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else /*�������벻һ��*/ {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ע����Ϣ");
                alert.setHeaderText(null);
                alert.setContentText("�������벻һ�£�");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clickExit(ActionEvent event) {
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }
}
