package objectAndDesignPattern;

public class PaperBoy {
    
    public void case1() {
        Customer customer = new Customer();
        int payment = 1000;
        Wallet wallet = customer.getWallet();
        //고객 지갑 주세요
        if (wallet.getTotalMoney() >= payment) {
            // 지갑에 돈이 있는 지 확인
            wallet.substractMoney(payment);
            //지갑에서 돈을 빼가겠습니다.
        } else {

        }
    }
    
}
