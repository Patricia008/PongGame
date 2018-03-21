
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
	private int x, y, width, height, speed = 0;
	private boolean upPressed, downPressed;
	private int score;
	
	public Player(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		upPressed = false;
		downPressed = false;
		this.speed=speed;
		this.score=0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpeed() {
		return speed;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void incrementScore()
	{
		score++;
	}
}
