package inventorySystem.Models;

import inventorySystem.exceptions.ValidationException;

public abstract class Part {
    private static int count = 0;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Part(){
//        id = ++count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void validate() throws ValidationException {

        if(getName().equals("")) {
            throw new ValidationException("Name can not be empty");
        }
        if(getStock() <= 0) {
            throw new ValidationException("Inventory must greater than 0");
        }
        if(getPrice() <= 0) {
            throw new ValidationException("The price must be more the 0");
        }
        if(getMin() <= 0 || getMin() >= getMax()) {
            throw new ValidationException("Min must be greater than 0 and Min amount has to be less than Max amount");
        }
        if(getStock() < getMin() || getStock() > getMax()) {
            throw new ValidationException("Inventory must be between the min and max values");
        }
        id = ++count;
    }
}