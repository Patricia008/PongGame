
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A Simple Socket client that connects to our socket server
 *
 */
public class SocketClient {

    private String hostname;
    private int port;
    private static GameLogic gl;
    Socket socketClient;
    BufferedWriter writer;
    private Player p1, p2;

    public SocketClient(String hostname, int port, Player p1, Player p2){
        this.hostname = hostname;
        this.port = port;
        this.p1=p1;
        this.p2=p2;
    }

    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }

//    public void readResponse() throws IOException{
//        String userInput;
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
//
//        System.out.print("RESPONSE FROM SERVER:");
//       if((userInput = stdIn.readLine())!=null) {
//            System.out.println(userInput);
//            p1.setUpPressed(false);
//			p1.setDownPressed(false);
//			p2.setUpPressed(false);
//			p2.setDownPressed(false);
//			if(userInput.equals("w"))
//					p1.setUpPressed(true);
//			if(userInput.equals("s"))
//					p1.setDownPressed(true);
//			if(userInput.equals("u"))
//				p2.setUpPressed(true);
//			if(userInput.equals("d"))
//				p2.setDownPressed(true);
//        }
//    }
    
    public void send() throws IOException{
    	writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
    	String output=gl.getOutput();
    	if(!output.equals("n")) 
    	{
				writer.write(output);
				writer.newLine();
			}
		
		writer.flush();
		
    }

    public static void main(String arg[]){
    	
    	JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		Player p1 = new Player(25, 250, 10, 90, 7);
		Player p2 = new Player(950, 250, 10, 90, 7);

		frame.setSize(250, 250);
		JLabel label1 = new JLabel("Use keybord arrows to go up and down");
		frame.add(label1);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setFocusable(true);
	    
//		frame.add(pongPanel, BorderLayout.CENTER);
//		pongPanel.setFocusable(true);
    	
		String serverAddress = (arg.length == 0) ? "localhost" : arg[0];
        SocketClient client = new SocketClient (serverAddress,9991, p1, p2);
      //trying to establish connection to the server
        try {
			client.connect();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        gl = new GameLogic();
		frame.addKeyListener(gl);        
        while(true)
        {
        try {
            
            client.send();
/******/   //client.readResponse();
            
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
        }
    }
}