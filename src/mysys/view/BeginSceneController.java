package mysys.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mysys.Main;

public class BeginSceneController {
    private Main mainapp;

    public void getController(Main amainapp) {
        this.mainapp = amainapp;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void clickGetTicket() {
        mainapp.showGetTicketScene();
    }

    @FXML
    private void clickUserLogin() {
        mainapp.showLoginScene();
    }

    @FXML
    private void clickAdminLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AdminLoginScene.fxml"));
            AnchorPane pane = loader.load();

            AdminLoginSceneController controller = loader.getController();
            controller.getController(this.mainapp);

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
