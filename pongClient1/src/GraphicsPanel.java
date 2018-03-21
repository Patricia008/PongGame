
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {
	private Ball ball;
	private Player playerOne, playerTwo;
	
	public GraphicsPanel(Ball b, Player p1, Player p2) {
		setBackground(Color.BLACK);
		ball = b;
		playerOne = p1;
		playerTwo = p2;
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRoundRect(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter(), ball.getDiameter(), ball.getDiameter());

		//g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
		g.fillRect(playerOne.getX(), playerOne.getY(), playerOne.getWidth(), playerOne.getHeight());
		g.fillRect(playerTwo.getX(), playerTwo.getY(), playerTwo.getWidth(), playerTwo.getHeight());
		Font font = new Font("Dialog", Font.PLAIN, 26);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(playerOne.getScore()+" : "+playerTwo.getScore(), 470, 25);
//		Rectangle r = new Rectangle();
	}
}
