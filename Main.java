import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Main extends JFrame {
	private Image bufferImage;
	private Graphics screenGraphic;

	private Clip clip;

	private Image player = new ImageIcon("src/images/kitty1.png").getImage();
	private Image fish = new ImageIcon("src/images/fish1.png").getImage();
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int fishX, fishY;	
	private boolean up, down, left, right;

	public Main() {
		setTitle("Kitty Game");
		setVisible(true);
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
		fishX = 250;
		fishY = 250;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					up = true;
					break;
				case KeyEvent.VK_S:
					down = true;
					break;
				case KeyEvent.VK_A:
					left = true;
					break;
				case KeyEvent.VK_D:
					right = true;
					break;
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					up = false;
					break;
				case KeyEvent.VK_S:
					down = false;
					break;
				case KeyEvent.VK_A:
					left = false;
					break;
				case KeyEvent.VK_D:
					right = false;
					break;
				}
			}
		});

		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keyProcess();

		}
	}

	public void keyProcess() {
		if (up && playerY - 3 > 30)
			playerY -= 3;
		if (down && playerY + playerHeight + 3 < 500)
			playerY += 3;
		if (left && playerX - 3 > 0)
			playerX -= 3;
		if (right && playerX + playerWidth + 3 < 500)
			playerX += 3;
	}

	public void paint(Graphics g) {
		bufferImage = createImage(500,500);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {

		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawImage(fish, fishX, fishY, null);

		this.repaint();
	}

	public static void main(String[] args) {
		new Main();
	}

}