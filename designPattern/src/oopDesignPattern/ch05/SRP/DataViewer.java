package oopDesignPattern.ch05.SRP;

public class DataViewer {

	public void display() {
		String data = loadHtml();
		updateGui(data);
	}
	
	public String loadHtml() {
		String url = "";
		HttpClient client = new HttpClient();
		client.connect(url);
		return client.getResponse();
	}
	
	private void updateGui(String data){
		GuiData guiModel = parseDataToGuiData(data);
		//tableUI.changeData(guiModel);
	}
	
	private GuiData parseDataToGuiData(String data) {
		return new GuiData();
	}
}


class HttpClient {
	
	public void connect(String url) {
		
	}
	
	public String getResponse() {
		return "";
	}
}

class GuiData{
}
