package il.ac.haifa.ClinicSystem;

import java.io.IOException;

public class MainApp {
	public static void main(String[] args) throws IOException {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		SimpleClient chatClient = new SimpleClient(host, port, "contentAdmin");
		chatClient.openConnection();
    }
}
