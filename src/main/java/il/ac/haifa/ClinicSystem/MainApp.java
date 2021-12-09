package il.ac.haifa.ClinicSystem;

import java.io.IOException;

public class MainApp {
	public static void main(String[] args) throws IOException {
		String host = "127.0.0.1";
		int port = 50001;
		
		SimpleClient chatClient = new SimpleClient(host, port, "contentAdmin");
		chatClient.openConnection();
    }
}
