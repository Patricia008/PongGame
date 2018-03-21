import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;


/**
* A simple socket server
* @author faheem
*
*/
public class SocketServer {
    
    private ServerSocket serverSocket;
    private int port;
    private static Player p1;
	private static Player p2;
    
    public SocketServer(int port) {
        this.port = port;
    }
    
    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);
        
        //Listen for clients. Block till one connects
        Socket client = null;
        int nr=0;
        Player p;
        String up, down;
        Thread thr1=null, thr2=null;
        while(nr<2){  
        	System.out.println("Waiting for clients...");
        	client = serverSocket.accept();
        	System.out.println("The following client has connected:"+client.getInetAddress().getCanonicalHostName());
        	//A client has connected to this server. Send welcome message
        	if(nr==0){ 
        		p=p1;
        		up=new String("w");
        		down=new String("s");
        		thr1 = new Thread(new SocketClientHandler(client, p, up, down));
                thr1.start();
        		} else {
        			p=p2;
        			up=new String("u");
        			down=new String("d");
        			thr2 = new Thread(new SocketClientHandler(client, p, up, down));
        	        thr2.start();
        		}          
            nr++;
        }  


    }
    
    
    
    /**
    * Creates a SocketServer object and starts the server.
    *
    * @param args
    */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9991;
        JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());


		Ball b = new Ball(250, 250, 30);
		p1 = new Player(25, 250, 10, 90, 7);
		p2 = new Player(950, 250, 10, 90, 7);
		GraphicsPanel pongPanel = new GraphicsPanel(b, p1, p2);

		frame.setSize(1000, 500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setFocusable(true);
		//frame.addKeyListener(gl);
		frame.add(pongPanel, BorderLayout.CENTER);
		pongPanel.setFocusable(true);
		GameLogic gl = new GameLogic(pongPanel, b, p1, p2);

		try {
            // initializing the Socket Server
            SocketServer socketServer = new SocketServer(portNumber);
            socketServer.start();
            
            } catch (IOException e) {
            e.printStackTrace();
        }
		
    }
    
}