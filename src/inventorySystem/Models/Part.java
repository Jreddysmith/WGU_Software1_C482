package inventorySystem.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {
    protected static int count = 0;
    protected IntegerProperty id;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty stock;
    protected IntegerProperty min;
    protected IntegerProperty max;

//    public Part(int id, String name, double price, int stock, int min, int max) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.stock = stock;
//        this.min = min;
//        this.max = max;
//    }
//
//    public Part(){
//
//    }

    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return this.price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getStock() {
        return this.stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getMin() {
        return this.min.get();
    }

    public void setMin(int min) {
        this.min .set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return this.max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public IntegerProperty maxProperty() {
        return max;
    }
}