package simulator.checkout;

import simulator.shopper.Shopper;

/**
 * An Express Line only allows shoppers that have 15 items or less
 * @author acelko
 *
 */
public class ExpressLine extends NormalLine {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEnterLine(Shopper shopper) {
        if (shopper == null)
            throw new NullPointerException("Shopper cannot be null.");
        
        if (shopper.getShoppingList().size() > 15)
            return false;
        else 
            return true;
    }
    
}
