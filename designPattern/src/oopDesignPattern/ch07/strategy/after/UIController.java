package oopDesignPattern.ch07.strategy.after;

import java.util.List;

public class UIController {

	private DiscountStrategy strategy;
	
	public void onFirstGuestButtonClick() {
		strategy = new FirstGuestDiscountStrategy();
	}
	
	public void onCalculatorButtonClick(List<Item> items) {
		Calculator cal = new Calculator(strategy);
		int price = cal.calculate(items);
	}
	
}
