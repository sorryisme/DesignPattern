package oopDesignPattern.ch07.state.after;

public interface State {
    
    public void increateCoin(int coin, VendingMachine vendingMachine);
    public void select(int productId, VendingMachine vendingMachine);
}
