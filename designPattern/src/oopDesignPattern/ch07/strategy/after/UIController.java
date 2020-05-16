package oopDesignPattern.ch07.strategy.after;

public class UIController {

	private DiscountStrategy strategy;
	
	public void onFirstGuestButtonClick() {
		strategy = new FirstGuestDiscountStrategy();
	}
	
	public void onCalculatorButtonClick() {
		Calculator cal = new Calculator(strategy);
		int price = cal.
	}
	
}
