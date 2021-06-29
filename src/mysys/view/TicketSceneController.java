package mysys.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mysys.model.OrderItem;

public class TicketSceneController {
    private OrderItem orderItem;

    @FXML
    private TextField leaveAirportField;
    @FXML
    private TextField arriveAirportField;

    @FXML
    private Label leaveTimeLabel;
    @FXML
    private Label arriveTimeLabel;
    @FXML
    private Label passengerNameLabel;
    @FXML
    private Label passengerIdLabel;
    @FXML
    private Label seatIdLabel;

    void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void getController() {

        leaveAirportField.setText(orderItem.leaveAirportProperty().get());
        arriveAirportField.setText(orderItem.arriveAirportProperty().get());

        leaveTimeLabel.setText(orderItem.leaveTimeProperty().get());
        arriveTimeLabel.setText(orderItem.arriveTimeProperty().get());
        passengerNameLabel.setText(orderItem.passengerNameProperty().get());
        passengerIdLabel.setText(orderItem.passengerIdProperty().get());
        seatIdLabel.setText(String.valueOf(orderItem.seatNumProperty().get()));
    }

    @FXML
    private void initialize() {

    }
}
