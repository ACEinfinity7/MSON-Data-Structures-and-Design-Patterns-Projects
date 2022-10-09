package simulator.store;

import java.util.ArrayList;
import java.util.List;

import simulator.checkout.AbstractRegister;
import simulator.checkout.CheckoutLineInterface;
import simulator.checkout.ExpressLine;
import simulator.checkout.NormalLine;
import simulator.checkout.SimpleRegister;
import simulator.checkout.Transaction;
import simulator.grocery.GroceryInterface;

public class SimpleStore extends AbstractGroceryStore {

    /**
     * List to hold registers for the store
     */
    private List<AbstractRegister> registers;

    /**
     * List to hold lines for the store
     */
    private List<CheckoutLineInterface> lines;

    /**
     * Constructor, initializes both registers and turns them on as well adding a simple and express
     * line to the line list
     */
    public SimpleStore() {
        registers = new ArrayList<AbstractRegister>();
        registers.add(new SimpleRegister());
        registers.add(new SimpleRegister());
        for (AbstractRegister register : registers) {
            register.turnOn();
        }

        lines = new ArrayList<CheckoutLineInterface>();
        // index 0 is the express line
        lines.add(new ExpressLine());
        // index 1 is the normal line
        lines.add(new NormalLine());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
        for (int i = 0; i < registers.size(); i++) {
            AbstractRegister register = registers.get(i);
            CheckoutLineInterface line = lines.get(i);

            if (!register.isBusy() && !line.isEmpty()) {
                register.processShopper(line.dequeue());
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CheckoutLineInterface> getLines() {
        return lines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (AbstractRegister register : registers) {
            transactions.addAll(register.getTransactions());
        }

        return transactions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAverageWaitingTime() {
        List<Transaction> transactions = getTransactions();
        double totalWaitTime = 0;
        for (Transaction transaction : transactions) {
            totalWaitTime += transaction.getShopper().getWaitingTime();
        }
        return totalWaitTime / transactions.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalSales() {
        List<Transaction> transactions = getTransactions();
        double totalSales = 0;
        for (Transaction transaction : transactions) {
            totalSales += transaction.getReceipt().getSaleValue();
        }
        return totalSales;
    }

    @Override
    public double getTotalCost() {
        List<Transaction> transactions = getTransactions();
        double totalCost = 0;
        for (Transaction transaction : transactions) {
            List<GroceryInterface> groceries = transaction.getReceipt().getGroceries();
            for (GroceryInterface grocery : groceries) {
                totalCost += grocery.getCost();
            }
        }

        for (AbstractRegister register : registers) {
            totalCost += register.getRunningCost();
        }

        return totalCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalProfit() {
        return getTotalSales() - getTotalCost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfShoppers() {
        return getTransactions().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfIrateShoppers() {
        List<Transaction> transactions = getTransactions();
        int irate = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getShopper().isIrate()) {
                irate++;
            }
        }
        
        return irate;
    }

}
