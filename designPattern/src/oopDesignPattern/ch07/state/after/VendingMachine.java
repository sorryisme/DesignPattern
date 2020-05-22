package oopDesignPattern.ch07.state.after;

public class VendingMachine {
    private State state;
    
    public VendingMachine() {
        state = new NoCoinState();
    }
    
    public void insertCoin(int coin) {
        state.increateCoin(coin, this);
    }
    
    public void select(int productId) {
        state.select(productId, this);
    }
    
    public void changeState(State newState) {
        this.state = newState;
    }
    
    public void provideProduct(int productId) {
        
    }
    
    public void decreaseCoin() {
        
    }
    
    public void increaseCoin(int coin) {
        
    }
    
    public boolean hasNoCoin() {
        return true;
    }
    
}
