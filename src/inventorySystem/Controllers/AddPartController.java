package inventorySystem.Controllers;

import inventorySystem.Models.InHouse;
import inventorySystem.Models.Inventory;
import inventorySystem.Models.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import inventorySystem.exceptions.ValidationException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class AddPartController {

    @FXML
    private Inventory inventory;

    private final AtomicInteger currentId = new AtomicInteger(0);
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
    private Button save_button;

    @FXML
    private Label company_name_field;


    @FXML
    public void inHouseClick() {
        company_name_field.setText("Machine ID");
    }

    @FXML
    public  void outsourced() {
        company_name_field.setPrefWidth(150);
        company_name_field.setText("Company Name");
    }

    @FXML
    public void savePart(ActionEvent event) throws IOException, ValidationException {
        String partName = part_name.getText();
        int partInv = Integer.parseInt(inv.getText());
        double partPrice = Double.parseDouble(price_cost.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());

        if (in_house.isSelected()) {
            int machineId = Integer.parseInt(company_name.getText());

            InHouse newPart = new InHouse();
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setStock(partInv);
            newPart.setMax(partMax);
            newPart.setMin(partMin);
            newPart.setMachineId(machineId);
//            Inventory.addPart(newPart);

            try {
                newPart.isValid();
                Inventory.addPart(newPart);

                System.out.println("Add button clicked");
                Stage stage = new Stage();
                stage = (Stage) save_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();


            } catch (ValidationException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Not valid Part!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        } else if (outsourced.isSelected()) {

            String companyName = company_name.getText();
            Outsourced newPart = new Outsourced();
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setStock(partInv);
            newPart.setMax(partMax);
            newPart.setMin(partMin);
            newPart.setCompanyName(companyName);
//            Inventory.addPart(newPart);

            try{
                newPart.isValid();
                Inventory.addPart((newPart));

                System.out.println("Add button clicked");
                Stage stage = new Stage();
                stage = (Stage) save_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (ValidationException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Not valid Part!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        }
    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        System.out.println("Cancel Button was clicked");
        Stage stage = new Stage();
        cancel_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
