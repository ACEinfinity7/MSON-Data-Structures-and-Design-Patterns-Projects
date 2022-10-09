package simulator.checkout;

import java.util.List;

import simulator.grocery.GroceryInterface;

public class Receipt extends AbstractReceipt {

    /**
     * {@inheritDoc}
     * 
     * @param groceries
     * @param discount
     */
    public Receipt(List<GroceryInterface> groceries, double discount) {
        super(groceries, discount);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSubtotal() {
        double subTotal = 0;

        for (GroceryInterface grocery : super.getGroceries()) {
            subTotal += grocery.getPrice();
        }

        return subTotal;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSaleValue() {
        return getSubtotal()- (super.getDiscount() * getSubtotal());

    }
}
