package inventorySystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
//    private static final Inventory inventory = new Inventory();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

//    public static Inventory getInstance(){
//        return inventory;
//    }
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        return allParts.filtered(part -> part.getId() == partId).stream().findFirst().orElse(null);
    }

    public static Product lookupProduct(int productId) {
        return allProducts.filtered(product -> product.getId() == productId).stream().findFirst().orElse(null);
    }

    public static ObservableList<Part> lookupPart(String partName) {

        return FXCollections.observableArrayList(allParts.filtered(part -> part.getName().equals(partName)));
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        return FXCollections.observableArrayList(allProducts.filtered(
                product -> product.getName().equals(productName)));
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}