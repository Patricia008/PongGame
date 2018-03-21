
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Timer;

public class GameLogic implements ActionListener, KeyListener {

	private String output;

	public GameLogic() {

		output=new String();
		Timer timer = new Timer(1000 / 70, this);
		timer.start();
	}
	
	/**
	 * All the movement, collision check, current game status, score, and ended
	 * by a call to GraphicsPanel's repaint() method.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public String getOutput()
	{
		return output;
		//return (output.equals(new String("n"))) ? null : output;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		switch (k.getKeyCode()) {

		case KeyEvent.VK_W:
//			if (!(playerOne.isUpPressed()))
//				playerOne.setUpPressed(true);
			output="w";
			break;
		case KeyEvent.VK_S:
//			if (!(playerOne.isDownPressed()))
//				playerOne.setDownPressed(true);
			output="s";
			break;
		case KeyEvent.VK_ENTER:
			output="e";
			break;
		default:
			
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		output="n";


	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}
}
