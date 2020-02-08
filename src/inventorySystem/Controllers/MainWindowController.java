package inventorySystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private Label parts_label;

    @FXML
    private Button parts_search;

    @FXML
    private TextField parts_search_field;

    @FXML
    private TableColumn<?, ?> parts_part_id;

    @FXML
    private TableColumn<?, ?> parts_part_name;

    @FXML
    private TableColumn<?, ?> parts_inventory_level;

    @FXML
    private TableColumn<?, ?> parts_price_per_unit;

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
    private TableColumn<?, ?> products_part_id;

    @FXML
    private TableColumn<?, ?> products_part_name;

    @FXML
    private TableColumn<?, ?> products_inventory_level;

    @FXML
    private Button products_add_button;

    @FXML
    private Button products_modify_button;

    @FXML
    private Button products_delete_button;

    @FXML
    private Button exit_button;

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
        System.out.println("clicked Delete button");
    }

    @FXML
    void initialize() {
//        parts_add_button.setOnAction(event -> {
//            System.out.println("seeing if this also works!");
//        });
    }

}
