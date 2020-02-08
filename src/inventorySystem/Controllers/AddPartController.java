package inventorySystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    @FXML
    private RadioButton in_house;

    @FXML
    private RadioButton outsourced;

    @FXML
    private TextField part_id;

    @FXML
    private TextField part_name;

    @FXML
    private TextField inv;

    @FXML
    private TextField price_cost;

    @FXML
    private TextField max;

    @FXML
    private TextField min;

    @FXML
    private TextField company_name;

    @FXML
    private Button cancel_button;

    @FXML
    void savePart(ActionEvent event) {
        System.out.println("printing this from added parts!");


        TextField part = part_name;
        TextField inv_name = inv;

        System.out.println(part.getText() + inv_name.getText());



    }

    @FXML
    void cancelButton(ActionEvent event) throws IOException {
        System.out.println("Add button clicked");
        cancel_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
