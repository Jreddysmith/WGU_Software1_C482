package inventorySystem.Controllers;

import inventorySystem.Models.Inventory;
import inventorySystem.Models.Part;
import inventorySystem.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static inventorySystem.Models.Inventory.getAllParts;
import inventorySystem.Models.Product;
import javafx.stage.Stage;

public class ProductController implements Initializable {

    @FXML
    private Button cancel_button;

    @FXML
    private TableView<Part> all_product_table;

    @FXML
    private TableColumn<Part, Integer> all_products_part_id;

    @FXML
    private TableColumn<Part, String> all_products_part_name;

    @FXML
    private TableColumn<Part, Integer> all_products_inventory_level;

    @FXML
    private TableColumn<Part, Double> all_products_price_per_unit;

    @FXML
    private TableView<Part> current_product_table;

    @FXML
    private TableColumn<Part, Integer> current_products_part_id;

    @FXML
    private TableColumn<Part, String> current_products_part_name;

    @FXML
    private TableColumn<Part, Integer> current_products_inventory_level;

    @FXML
    private TableColumn<Part, Double> current_products_price_per_unit;

    @FXML
    private TextField product_id;

    @FXML
    private TextField product_name;

    @FXML
    private TextField product_inv;

    @FXML
    private TextField product_price;

    @FXML
    private TextField product_max;

    @FXML
    private TextField product_min;

    @FXML
    private Button products_search_button;

    @FXML
    private TextField products_search_field;

    @FXML
    private Button productsAddButton;

    @FXML
    private Button products_delete_button;

    @FXML
    private Button cancel_id_button;

    @FXML
    private Button product_save_button;


    @FXML
    private ObservableList productParts = FXCollections.observableArrayList();

    public ProductController() {
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

    @FXML
    public void updateAllProducts() {
        all_product_table.setItems(getAllParts());
    }

    @FXML
    public void updateCurrentProducts() {
        current_product_table.setItems(productParts);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        all_products_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        all_products_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        all_products_inventory_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
        all_products_price_per_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateAllProducts();

        current_products_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        current_products_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        current_products_inventory_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
        current_products_price_per_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateAllProducts();

    }


    public void productsSearchButton(ActionEvent event) {
        String partsSearch = products_search_field.getText();
        Part searchPart = Inventory.lookupPart(Integer.parseInt(partsSearch));


        if(searchPart != null || !partsSearch.isEmpty()) {
            ObservableList<Part> filteredPart = FXCollections.observableArrayList();
            filteredPart.add(searchPart);
            all_product_table.setItems(filteredPart);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Not Found");
            alert.setContentText("Search");
            alert.showAndWait();
        }
    }

    public void productsAdd(ActionEvent event) {
    }

    public void productsDeleteButton(ActionEvent event) {

        if (productParts.size() > 2) {
            Part part = current_product_table.getSelectionModel().getSelectedItem();
            productParts.remove(part);
            updateCurrentProducts();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Error!");
            alert.setHeaderText("This should have at least one part!");
            alert.showAndWait();
        }
    }

    public void productSaveButton(ActionEvent event) {
    }
}
