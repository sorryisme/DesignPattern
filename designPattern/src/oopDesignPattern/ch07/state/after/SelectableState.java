package oopDesignPattern.ch07.state.after;

public class SelectableState implements State {

    @Override
    public void increateCoin(int coin, VendingMachine vm) {
        vm.increaseCoin(coin);
    }

    @Override
    public void select(int productId, VendingMachine vm) {
        vm.provideProduct(productId);
        vm.decreaseCoin();
        
        if(vm.hasNoCoin())
            vm.changeState(new NoCoinState());
    }

    
}
