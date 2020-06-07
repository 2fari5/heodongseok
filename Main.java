import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame {
	private Image bufferImage;
	private Graphics screenGraphic;
	private Clip clip;
	private int fishX, fishY;
	private int score, Time;
	private int meatX, meatY;
    
	private Image player = new ImageIcon("src/images/pig.png").getImage();
    private Image background = new ImageIcon("src/images/h2.png").getImage();
    private Image fish = new ImageIcon("src/images/fish.png").getImage();
    private Image meat = new ImageIcon("src/images/meat.png").getImage();
    

	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int fishWidth = fish.getWidth(null);
	private int fishHeight = fish.getHeight(null);
	private int meatWidth = meat.getWidth(null);
	private int meatHeight = meat.getHeight(null);
	private boolean up, down, left, right;

	public Main() {
		setTitle("Kitty Game");
		
		setVisible(true);
		setSize(1000,900);
		score = 0;
		fishX = 250;
		fishY = 400;
		meatX = 600;
		meatY = 100;
		playerX=10;
		playerY=35;
		
		GUI gui = new GUI();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE)
					gui.setVisible(true);	
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					up = true;
					break;
				case KeyEvent.VK_DOWN:
					down = true;
					break;
				case KeyEvent.VK_LEFT:
					left = true;
					break;
				case KeyEvent.VK_RIGHT:
					right = true;
					break;
				}
			}

			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					up = false;
					break;
				case KeyEvent.VK_DOWN:
					down = false;
					break;
				case KeyEvent.VK_LEFT:
					left = false;
					break;
				case KeyEvent.VK_RIGHT:
					right = false;
					break;
				}
			}
		});
		
		while (true) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keyProcess();
			Eatfish();
			Eatmeat();

		}
	}
	public void Eatfish(){
	
		
		if (playerX + playerWidth > fishX && fishX + fishWidth > playerX && playerY + playerHeight > fishY && fishY + fishHeight > playerY) {
		    playSound("src/sound/Eat.wav", false);
			score+=100;
			fishX = (int)(Math.random()*(900-playerWidth));
			fishY = (int)(Math.random()*(900-playerHeight-30))+30;
		
	}
	}
	
	public void Eatmeat() {
	
		if (playerX + playerWidth > meatX && meatX + meatWidth > playerX && playerY + playerHeight > meatY && meatY + meatHeight > playerY) {
			playSound("src/sound/Eat.wav", false);
			score+=200;
			meatX = (int)(Math.random()*(900-playerWidth));
			meatY = (int)(Math.random()*(900-playerHeight-30))+30;
			
		
	
	}
	}
	public void playSound(String pathName, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
			clip.start();
			if (isLoop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	// 오디오 재생

	public void keyProcess() {
		if (up && playerY - 3 > 30)
			playerY -= 3;
		if (down && playerY + playerHeight + 3 < 900)
			playerY += 3;
		if (left && playerX - 3 > 0)
			playerX -= 3;
		if (right && playerX + playerWidth + 3 < 1000)
			playerX += 3;
	}

	public void paint(Graphics g) {
		bufferImage = createImage(1000,900);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {

		g.drawImage(background, 0, 0, null);
		g.drawImage(player,  playerX, playerY, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE : " + score, 330, 60);
		g.drawImage(fish, fishX, fishY, null);
		g.drawImage(meat, meatX, meatY, null);

		this.repaint();
	}

	public static void main(String[] args) {
		new StartScreen();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		new Main();
	}

}