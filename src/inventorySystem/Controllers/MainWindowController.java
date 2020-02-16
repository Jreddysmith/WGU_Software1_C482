package inventorySystem.Controllers;

import inventorySystem.Models.*;
import inventorySystem.Models.Inventory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import java.util.Optional;
import java.util.ResourceBundle;
import static inventorySystem.Models.Inventory.getAllParts;
import static inventorySystem.Models.Inventory.getAllProducts;

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

    private static Part modifiedPart;

    @FXML
    void partAddButton() throws IOException {
        System.out.println("Add button clicked");
        Stage stage = new Stage();
        stage = (Stage)parts_add_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/addPart.fxml"));

        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void addPartsScreen() throws IOException {
        Stage stage = new Stage();
        stage = (Stage)parts_add_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/addPart.fxml"));

        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void partModifyButton() throws IOException {

        Part part = parts_table.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/modifyPart.fxml"));
        loader.load();

        ModifyedPartController mpc = loader.getController();
        mpc.getPart(part);

    }

    @FXML
    public void partDeleteButton() {
        Part part = (Part) parts_table.getSelectionModel().getSelectedItem();

        if (part != null) {
            Inventory.deletePart(part);
            updatePart();
        }
        return;
    }

    @FXML
    public void partsSearchButton() {
        String partsSearch = parts_search_field.getText();


        if(partsSearch != null && !partsSearch.isEmpty()) {
            Part searchPart = Inventory.lookupPart(Integer.parseInt(partsSearch));
            ObservableList<Part> filteredPart = FXCollections.observableArrayList();
            filteredPart.add(searchPart);
            parts_table.setItems(filteredPart);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Error");
            alert.setHeaderText("Not Found");
            alert.setContentText("Search");
            alert.showAndWait();
        }
    }

    @FXML
    public void updatePart() {
        parts_table.setItems(getAllParts());
    }

    @FXML
    public void updateProducts() {
        product_table.setItems(getAllProducts());
    }

    @FXML
    public void productsAddButton() throws IOException {

        System.out.println("Add Product button clicked");
        Stage stage = new Stage();
        stage = (Stage)products_add_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorySystem/Views/product.fxml"));

        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
            parts_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            parts_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            parts_inventory_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
            parts_price_per_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
            updatePart();


            products_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            products_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            products_inventory_level.setCellValueFactory(new PropertyValueFactory<>("stock"));
            products_price_per_unit.setCellValueFactory(new PropertyValueFactory<>("price"));
            updateProducts();

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
