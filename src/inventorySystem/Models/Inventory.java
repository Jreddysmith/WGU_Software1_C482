package inventorySystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        this.allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        this.allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        return allParts.filtered(part -> part.getId() == partId).stream().findFirst().orElse(null);
    }

    public Product lookupProduct(int productId) {
        return allProducts.filtered(product -> product.getId() == productId).stream().findFirst().orElse(null);
    }

    public ObservableList<Part> lookupPart(String partName) {

        return FXCollections.observableArrayList(allParts.filtered(part -> part.getName().equals(partName)));
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return FXCollections.observableArrayList(allProducts.filtered(
                product -> product.getName().equals(productName)));
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}