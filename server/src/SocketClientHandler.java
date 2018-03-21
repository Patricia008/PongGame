
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClientHandler implements Runnable {

	private Socket client;
	private Player p;
	private String up, down;
	private String userInput;

	public SocketClientHandler(Socket client, Player p, String up, String down) {
		this.client = client;
		this.p=p;
		this.up=up;
		this.down=down;
	}

	@Override
	public void run() {
		try {
			System.out.println("Thread started with name:"+Thread.currentThread().getName());
			while(true){
				readResponse();
				//sendResponse();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void readResponse() throws IOException, InterruptedException {

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		if ((userInput=stdIn.readLine())!=null) {
			System.out.print(userInput);	
			p.setUpPressed(false);
			p.setDownPressed(false);
			if(userInput.equals(up))
					p.setUpPressed(true);
			if(userInput.equals(down))
					p.setDownPressed(true);
		}
	}
	
//	private void sendResponse() throws IOException, InterruptedException {
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
//		writer.write(userInput);
//		writer.newLine();
//		writer.flush();
//		//writer.close();
//	}

}
