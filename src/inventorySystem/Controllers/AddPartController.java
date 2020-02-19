package inventorySystem.Controllers;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import inventorySystem.Models.InHouse;
import inventorySystem.Models.Inventory;
import inventorySystem.Models.Outsourced;
import inventorySystem.Models.Part;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import inventorySystem.exceptions.ValidationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import static inventorySystem.Controllers.MainWindowController.getModifiedPart;

public class AddPartController implements Initializable {

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
    private Button update_button;

    @FXML
    private Label company_name_field;

    @FXML
    private Text main_label;

    private final Part modPart;

    public AddPartController() {
    this.modPart = getModifiedPart();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("seeing if the initialize works");
        if(modPart != null) {
            main_label.setText("Modify");
            if(modPart instanceof InHouse) {
                inHouseClick();
                in_house.selectedProperty().setValue(true);
                outsourced.selectedProperty().setValue(false);
                System.out.println("this is an inhouse object");
//                company_name.setText(Integer.toString(((InHouse) modPart).getMachineId()));
            } else{
                outsourced();
                outsourced.selectedProperty().setValue(true);
                in_house.selectedProperty().setValue(false);
//                company_name.setText(((Outsourced) modPart).getCompanyName());
                System.out.println("this is an outsorced");
            }
        } else {
            in_house.selectedProperty().setValue(true);
            outsourced.selectedProperty().setValue(false);
        }
    }


    @FXML
    public void inHouseClick() {
        company_name_field.setText("Machine ID");
        company_name.setPromptText("Mach ID");
    }

    @FXML
    public  void outsourced() {
        outsourced.setPrefWidth(150);
        company_name_field.setPrefWidth(150);
        company_name_field.setText("Company Name");
        company_name.setPromptText("Company Nm");
    }

    @FXML
    public void savePart(ActionEvent event) throws IOException, ValidationException {
        save_button.setVisible(true);
        update_button.setVisible(false);

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

            try {
                newPart.validate();
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

            try{
                newPart.validate();
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

    public void getPart(Part part){
        update_button.setVisible(true);
        save_button.setVisible(false);

        System.out.println("seeing what the object is " + part.getClass());

        part_id.setText(Integer.toString(part.getId()));
        part_name.setText(part.getName());
        inv.setText(Integer.toString(part.getStock()));
        price_cost.setText(Double.toString(part.getPrice()));
        max.setText(Integer.toString(part.getMax()));
        min.setText(Integer.toString(part.getMin()));
        if(part instanceof InHouse) {
            company_name.setText(Integer.toString(((InHouse) part).getMachineId()));

        } else {
            company_name.setText(((Outsourced) part).getCompanyName());
        }

//        this.updatedPartReturn = Integer.parseInt(part_id.getText());
    }

    @FXML
    public void updateButton(ActionEvent event){
//        int partId = updatedPartReturn;

        int partId = Integer.parseInt(part_id.getText());
        String partName = part_name.getText();
        int partInv = Integer.parseInt(inv.getText());
        double partPrice = Double.parseDouble(price_cost.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());


        if (in_house.isSelected()) {
            int machineId = Integer.parseInt(company_name.getText());

            InHouse updatePart = new InHouse();
            updatePart.setId(partId);
            updatePart.setName(partName);
            updatePart.setStock(partInv);
            updatePart.setPrice(partPrice);
            updatePart.setMax(partMax);
            updatePart.setMin(partMin);
            updatePart.setMachineId(machineId);


            try {
                updatePart.validate();
                Inventory.updatePart(partId, updatePart);

                System.out.println("Add button clicked");
                Stage stage = new Stage();
                stage = (Stage) update_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();


            } catch (ValidationException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Not valid Part!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        } else if (outsourced.isSelected()) {
            String companyName = company_name.getText();

            Outsourced updatePart = new Outsourced();
            updatePart.setId(partId);
            updatePart.setName(partName);
            updatePart.setStock(partInv);
            updatePart.setPrice(partPrice);
            updatePart.setMax(partMax);
            updatePart.setMin(partMin);
            updatePart.setCompanyName(companyName);

            try{
                updatePart.validate();
                Inventory.updatePart(updatePart.getId(), updatePart);

                System.out.println("Add button clicked");
                Stage stage = new Stage();
                stage = (Stage) update_button.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (ValidationException | IOException e) {
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
        stage = (Stage) cancel_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
