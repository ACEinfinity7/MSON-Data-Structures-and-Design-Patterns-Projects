package simulator.checkout;

import simulator.shopper.Shopper;
import structures.LinkedQueue;
import structures.QueueInterface;

public class NormalLine extends LinkedQueue<Shopper> implements CheckoutLineInterface {

    @Override
    public boolean canEnterLine(Shopper shopper) {
        if (shopper == null)
            throw new NullPointerException("Shopper cannot be null.");
        
        return true;
    }

    @Override
    public QueueInterface<Shopper> enqueue(Shopper shopper) {
        if (shopper == null)
            throw new NullPointerException("Shopper cannot be null.");
        
        if (!canEnterLine(shopper))
            throw new IllegalArgumentException("Shopper cannot enter line.");
        else
            return super.enqueue(shopper);
    }

}
