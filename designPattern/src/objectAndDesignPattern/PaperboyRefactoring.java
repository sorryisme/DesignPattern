package objectAndDesignPattern;

public class PaperboyRefactoring {
    
    public void case2() {
        CustomerRefactoring customer = new CustomerRefactoring();
        int payment = 1000;
        try {
            int paidAmount = customer.getPayment(payment);
        } catch( NotEnoughMoneyException e) {
            e.toString();
        }
    }
    
}
