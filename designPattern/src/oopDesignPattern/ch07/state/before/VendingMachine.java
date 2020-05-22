package oopDesignPattern.ch07.state.before;

public class VendingMachine {
	public static enum State { NOCOIN, SELECTABLE, SOLDOUT}
	private State state = State.NOCOIN;
	
	public void insertCoin (int coin) {
		switch(state) {
			case NOCOIN:
				increaseCoin(coin);
				state = State.SELECTABLE;
				break;
				
			case SELECTABLE:
				increaseCoin(coin);
			case SOLDOUT:
			    returnCoin();
		}
	}
	
	public void select(int productId) {
		switch(state) {
			case NOCOIN :
				//아무것도하지않음
				break;
			case SELECTABLE :
				provideProduct(productId);
				decreaseCoin();
				if(hasNoCoin())
					state = State.NOCOIN;
			case SOLDOUT:
			    //아무것도 하지 않음
		}
	}
	
	private void increaseCoin (int coin) {
		
	}
	private void provideProduct (int productId) {
		
	}
	private void decreaseCoin() {
		
	}
	private boolean hasNoCoin() {
		return true;
	}
	
	private void returnCoin() {
	    
	}
}
