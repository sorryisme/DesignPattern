package oopDesignPattern.ch07.state.after;

public class NoCoinState implements State {

    @Override
    public void increateCoin(int coin, VendingMachine vm) {
        vm.increaseCoin(coin);
        vm.changeState(new SelectableState());
    }

    @Override
    public void select(int productId, VendingMachine vendingMachine) {
        SoundUtil.beep();
    }
    
    
}
