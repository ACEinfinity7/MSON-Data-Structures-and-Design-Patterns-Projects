package simulator.checkout;

import simulator.shopper.Shopper;

public class SimpleRegister extends AbstractRegister {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Transaction createTransaction(Shopper s) {
        int numItems = s.getShoppingList().size();
        
        return new Transaction(new Receipt(s.getShoppingList(), 0), s, numItems == 0 ? 1 : 4 * numItems);
    }

}
