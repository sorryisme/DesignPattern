package objectAndDesignPattern;

public class CustomerRefactoring {

    private Wallet wallet;
    
    public int getPayment(int payment) throws NotEnoughMoneyException{
        if(wallet == null) throw new NotEnoughMoneyException();
        if(wallet.getTotalMoney() > payment) {
            wallet.substractMoney(payment);
            return payment;
        }
        
        throw new NotEnoughMoneyException();
    }
    
}
