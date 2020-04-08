package oopDesignPattern.ch05.OCP;

public class ResponseSender {

	private Data data;
	
	public ResponseSender(Data data) {
		this.data = data;
	}
	
	
	public Data getData() {
		return data;
	}
	
	public void Send() {
		sendHeader();
		sendBody();
	}
	
	protected void sendHeader() {
		
	}
	protected void sendBody(){
		
	}
}

class Data {
	
}
