package simulator.grocery;

public class Grocery implements GroceryInterface {

    // set up some attributes
    private String name;
    private double price, cost, handling;

    // constructor - initialize attributes
    public Grocery(String n) {
        name = n;
    }

    public Grocery(String n, double price, double cost, double handling) {
        name = n;
        this.price = price;
        this.cost = cost;
        this.handling = handling;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public double getHandlingRating() {
        return handling;
    }

}
