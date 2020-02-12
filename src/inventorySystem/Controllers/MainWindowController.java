package inventorySystem.Controllers;

import inventorySystem.Models.*;
import inventorySystem.Models.Inventory;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;


import static inventorySystem.Models.Inventory.getAllParts;

public class MainWindowController implements Initializable {

    @FXML
    private Label parts_label;

    @FXML
    private Button parts_search;

    @FXML
    private TextField parts_search_field;

    @FXML
    private TableView<Part> parts_table;

    @FXML
    private TableColumn<Part, Integer> parts_part_id;

    @FXML
    private TableColumn<Part, String> parts_part_name;

    @FXML
    private TableColumn<Part, Integer> parts_inventory_level;

    @FXML
    private TableColumn<Part, Double> parts_price_per_unit;

    @FXML
    private Button parts_add_button;

    @FXML
    private Button parts_modify_button;

    @FXML
    private Button parts_delete_button;

    @FXML
    private Label products_label;

    @FXML
    private Button products_search;

    @FXML
    private TextField products_search_field;

    @FXML
    private TableView<Product> product_table;

    @FXML
    private TableColumn<Product, Integer> products_part_id;

    @FXML
    private TableColumn<Product, String> products_part_name;

    @FXML
    private TableColumn<Product, Integer> products_inventory_level;

    @FXML
    private TableColumn<Product, Double> products_price_per_unit;

    @FXML
    private Button products_add_button;

    @FXML
    private Button products_modify_button;

    @FXML
    private Button products_delete_button;

    @FXML
    private Button exit_button;

    @FXML
    private Inventory inventory;





    @FXML
    void partAddButton() throws IOException {
        System.out.println("Add button clicked");
        parts_add_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/addPart.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    @FXML
    void partModifyButton() {
        System.out.println("clicked Modify button");
    }

    @FXML
    void partDeleteButton() {
        Part part = (Part) parts_table.getSelectionModel().getSelectedItem();

        if(part == null) return;

        inventory.deletePart(part);


    }

    @FXML
    public void updatePart() {
        parts_table.setItems(getAllParts());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        System.out.println("back to home screen");




            parts_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            parts_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            parts_inventory_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
            parts_price_per_unit.setCellValueFactory(new PropertyValueFactory<>("price"));


            updatePart();


//
//            products_part_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
//            products_part_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
//            products_inventory_level.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
//            products_price_per_unit.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//
//            parts_table.setItems(inventory.getAllParts());
//
//            System.out.println("seeing if im getting the parts back: " + inventory.getAllParts());


//            parts_table.setItems(Inventory.getInstance().getAllParts());
//            product_table.setItems(Inventory.getInstance().getAllProducts());
//
//            System.out.println("Lets see if out inventory comes up: " + Inventory.getInstance().getAllParts());

    }

    @FXML
    public void exitMainWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to Exit the app");
        Optional<ButtonType> result = alert.showAndWait();

        if(!result.isPresent()) {
            System.out.println("Exit Screen");
        } else if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }



}
