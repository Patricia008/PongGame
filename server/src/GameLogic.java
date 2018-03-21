
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Timer;

public class GameLogic implements ActionListener {
	private GraphicsPanel g;
	private Ball ball;
	private Player playerOne, playerTwo;

	public GameLogic(GraphicsPanel g, Ball b, Player p1, Player p2) {
		this.g = g;
		ball = b;
		playerOne = p1;
		playerTwo = p2;

		Timer timer = new Timer(1000 / 70, this);
		timer.start();
	}
	
	/**
	 * All the movement, collision check, current game status, score, and ended
	 * by a call to GraphicsPanel's repaint() method.
	 */
	public void step() {

		if (playerOne.isUpPressed()) {
			if (playerOne.getY() - playerOne.getSpeed() > 0) {
				playerOne.setY(playerOne.getY() - playerOne.getSpeed());
			}
			playerOne.setUpPressed(false);
		}

		if (playerOne.isDownPressed()) {
			if (playerOne.getY() + playerOne.getSpeed() + playerOne.getHeight() < g
					.getHeight()) {
				playerOne.setY(playerOne.getY() + playerOne.getSpeed());
			}
			playerOne.setDownPressed(false);
		}

		if (playerTwo.isUpPressed()) {
			if (playerTwo.getY() - playerTwo.getSpeed() > 0) {
				playerTwo.setY(playerTwo.getY() - playerTwo.getSpeed());
			}
			playerTwo.setUpPressed(false);
		}

		if (playerTwo.isDownPressed()) {
			if (playerTwo.getY() + playerTwo.getSpeed() + playerTwo.getHeight() < g
					.getHeight()) {
				playerTwo.setY(playerTwo.getY() + playerTwo.getSpeed());
			}
			playerTwo.setDownPressed(false);
		}

		// Ball position after this step:
		int nextBallLeft = ball.getX() + ball.getDeltaX();
		int nextBallRight = ball.getX() + ball.getDiameter() + ball.getDeltaX();
		int nextBallTop = ball.getY() + ball.getDeltaY();
		int nextBallBottom = ball.getY() + ball.getDiameter()
				+ ball.getDeltaY();

		int playerOneRight = playerOne.getX() + playerOne.getWidth();
		int playerOneTop = playerOne.getY();
		int playerOneBottom = playerOne.getY() + playerOne.getHeight();

		int playerTwoLeft = playerTwo.getX();
		int playerTwoTop = playerTwo.getY();
		int playerTwoBottom = playerTwo.getY() + playerTwo.getHeight();

		// The ball should bounce off the top and bottom of the screen.
		if (nextBallTop < 0
				|| nextBallTop > g.getHeight() - (ball.getDiameter())) {
			ball.setDeltaY(-(ball.getDeltaY()));
		}

		// If the ball goes to the left side, a point should be awarded to
		// player 2.
		if (nextBallLeft < 0) {
			if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
				playerTwo.incrementScore();
				ball.setDeltaX(-(ball.getDeltaX()));
				ball.setX(500);
				ball.setY(250);
			} 
		}
		if (nextBallTop < playerOneBottom+20 && nextBallTop > playerOneTop-40
				&& nextBallLeft == playerOneRight) {
			ball.setDeltaX(-(ball.getDeltaX()));
		}

		// If the ball goes to the right side, a point should be awarded to
		// player 1.
		if (nextBallRight > g.getWidth()) {
			if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
				playerOne.incrementScore();
				ball.setDeltaX(-(ball.getDeltaX()));
				ball.setX(500);
				ball.setY(250);
			}
			
		}
		if (nextBallTop < playerTwoBottom+20 && nextBallTop > playerTwoTop-40
				&& nextBallRight == playerTwoLeft) {
			ball.setDeltaX(-(ball.getDeltaX()));
		}

		ball.setX(ball.getX() + ball.getDeltaX());
		ball.setY(ball.getY() + ball.getDeltaY());

		g.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}

}
