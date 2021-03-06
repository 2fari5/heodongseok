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
    
<<<<<<< HEAD
	private Image player = new ImageIcon("src/images/pig.png").getImage();
    private Image background = new ImageIcon("src/images/h2.png").getImage();
    private Image fish = new ImageIcon("src/images/fish.png").getImage();
=======
	private Image player = new ImageIcon("src/images/kitty1.png").getImage();
    private Image background = new ImageIcon("src/images/h2.png").getImage();
    private Image fish = new ImageIcon("src/images/fish1.png").getImage();
>>>>>>> 9eb50a71d4cec327cc600d0ebea229a1434a7c63
    private Image meat = new ImageIcon("src/images/meat.png").getImage();
    

	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int fishWidth = fish.getWidth(null);
	private int fishHeight = fish.getHeight(null);
<<<<<<< HEAD
=======
	private int meatWidth = meat.getWidth(null);
	private int meatHeight = meat.getHeight(null);
>>>>>>> 9eb50a71d4cec327cc600d0ebea229a1434a7c63
	private boolean up, down, left, right;

	public Main() {
		setTitle("Kitty Game");
		playSound("src/sound/BackgroundMusic.wav", true); 
		setVisible(true);
		setSize(1000,900);
		fishX = 250;
		fishY = 250;
		meatX = 300;
		meatY = 300;
		score = 0;
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
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keyProcess();
			Eatfish();
<<<<<<< HEAD

		}
	}
	public void Eatfish()
	{
		if (playerX + playerWidth > fishX && fishX + fishWidth > playerX && playerY + playerHeight > fishY && fishY + fishHeight > playerY)
		{
			playSound("src/sound/Eat.wav", false);
			fishX = (int)(Math.random()*(501-playerWidth));
			fishY = (int)(Math.random()*(501-playerHeight-30))+30;
			
		}
=======
			Eatmeat();

		}
	}
	public void Eatfish(){
	
		
		if (playerX + playerWidth > fishX && fishX + fishWidth > playerX && playerY + playerHeight > fishY && fishY + fishHeight > playerY) {
		    score+=100;
			fishX = (int)(Math.random()*(501-playerWidth));
			fishY = (int)(Math.random()*(501-playerHeight-30))+30;
		
	}
	}
	
	public void Eatmeat() {
	
		if (playerX + playerWidth > meatX && meatX + meatWidth > playerX && playerY + playerHeight > meatY && meatY + meatHeight > playerY) {
			score+=200;
			meatX = (int)(Math.random()*(501-playerWidth));
			meatY = (int)(Math.random()*(501-playerHeight-30))+30;
	
	}
>>>>>>> 9eb50a71d4cec327cc600d0ebea229a1434a7c63
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