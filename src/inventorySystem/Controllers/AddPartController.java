package inventorySystem.Controllers;

import inventorySystem.Models.InHouse;
import inventorySystem.Models.Inventory;
import inventorySystem.Models.Outsourced;
import javafx.beans.property.DoubleProperty;
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
    void savePart(ActionEvent event) throws IOException {

//        TextField part = part_name;
//        TextField inv_name = inv;

        String partName = part_name.getText();
        int partInv = Integer.parseInt(inv.getText());
        double partPrice = Double.parseDouble(price_cost.getText());
        int partMax = Integer.parseInt( max.getText());
        int partMin = Integer.parseInt(min.getText());




        if(in_house.isSelected()) {

        } else if (outsourced.isSelected()) {

            String companyName = company_name.getText();

//            Outsourced newPart = new Outsourced(currentId.getAndIncrement(), partName, partPrice, partInv,
//                     partMax, partMin, companyName);

            Outsourced newPart = new Outsourced();

            newPart.setId(currentId.getAndIncrement());
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setStock(partInv);
            newPart.setMax(partMax);
            newPart.setMin(partMin);
            newPart.setCompanyName(companyName);



            Inventory.addPart(newPart);


//            Inventory.getInstance().addPart(new Outsourced(currentId.getAndIncrement(), partName, partPrice, partInv,
//                    partMax, partMin, companyName));

//            System.out.println(Inventory.getInstance().getAllParts());
        }

//        System.out.println("You have just saved an Item");
//        save_button.getScene().getWindow().hide();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));
//
//        loader.load();
//        Parent root = loader.getRoot();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.showAndWait();

        System.out.println("Add button clicked");
        Stage stage = new Stage();
        stage = (Stage)save_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));

//        loader.load();
        Parent root = loader.load();
//        Scene scene = new Scene(root);

        stage.setScene(new Scene(root));
        stage.show();



    }

    @FXML
    void cancelButton(ActionEvent event) throws IOException {
        System.out.println("Cancel Button was clicked");
        cancel_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/mainWindow.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    void changeWindows() {

    }

}
