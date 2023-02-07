import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GamePanel extends JPanel implements ActionListener{
	
	
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static final int SCREEN_WIDTH = 66*((int)screenSize.getWidth())/100;
	static final int SCREEN_HEIGHT = 80*((int)screenSize.getHeight())/100;
	static final int UNITS_SIZE = 22;
	static final int GAME_UNITS = SCREEN_WIDTH*SCREEN_HEIGHT/UNITS_SIZE;
	int X[] = new int[GAME_UNITS];
	int Y[] = new int[GAME_UNITS];
	static  int DELAY = 100;
	int BodyParts = 6;
	int AppleEaten;
	int AppleX;
	int AppleY;
	char direction = 'R';
	boolean running  = false;
	Timer timer;
	Random random;
	JButton Startbutton = new JButton(); 
	JButton Restartbutton = new JButton();
	JButton Settingsbutton = new JButton(); 
	JButton Homebutton = new JButton(); 
	JButton Exitbutton = new JButton();
	JButton Backbutton = new JButton();
	boolean start = false;
	boolean settings = false;
	boolean Ver = false;
	
	JSlider slider = new JSlider(20,200,90);
	File file = new File("eat apple.wav");
	File file1 = new File("Song.wav");
	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
	Clip clip = AudioSystem.getClip();
	Clip clip1 = AudioSystem.getClip();
	
	Timer time = new Timer(2000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			playsong();
		}
	});
	
	JCheckBox check = new JCheckBox();
	JLabel SnakeColor = new JLabel();
	JLabel RB = new JLabel();
	JRadioButton Green =new JRadioButton();
	JRadioButton Purple =new JRadioButton();
	JRadioButton Cocktail =new JRadioButton();
	JRadioButton Blue =new JRadioButton();
	JRadioButton Red =new JRadioButton();
	JRadioButton Yellow =new JRadioButton();
	JRadioButton Orange =new JRadioButton();
	ButtonGroup GB = new ButtonGroup();
	int ChooseColor = 1;
	String snakecolor = "Purple";
	GamePanel() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		
		clip.open(audioStream);
		clip1.open(audioStream1);
		Purple.setSelected(true);
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.setLayout(null);

		Startbutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-200, 200, 40);
		Startbutton.setBackground(new Color(0x0009FF));
		Startbutton.setText("Start");
		Startbutton.setForeground(Color.WHITE);
		Startbutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Startbutton.setFocusable(false);
		Startbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Startbutton.setVisible(false);
				Settingsbutton.setVisible(false);
				Exitbutton.setVisible(false);
				Settingsbutton.setVisible(false);
				Backbutton.setVisible(false);
				Restartbutton.setVisible(false);
				Homebutton.setVisible(false);
				slider.setVisible(false);
				check.setVisible(false);
				RB.setVisible(false);
				SnakeColor.setVisible(false);
				Green.setVisible(false);
				Purple.setVisible(false);
				Cocktail.setVisible(false);
				StartGame();
				start=true;
				Ver=true;
				repaint();
			}
		});
		this.add(Startbutton);
		Settingsbutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-140, 200, 40);
		Settingsbutton.setBackground(new Color(0x0009FF));
		Settingsbutton.setText("Settings");
		Settingsbutton.setForeground(Color.WHITE);
		Settingsbutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Settingsbutton.setFocusable(false);
		Settingsbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Startbutton.setVisible(false);
				Settingsbutton.setVisible(false);
				Exitbutton.setVisible(false);
				Settingsbutton.setVisible(false);
				Restartbutton.setVisible(false);
				Homebutton.setVisible(false);
				
				Backbutton.setVisible(true);
				
				slider.setVisible(true);
				check.setVisible(true);
				RB.setVisible(true);
				SnakeColor.setVisible(true);
				Green.setVisible(true);
				Blue.setVisible(true);
				Cocktail.setVisible(true);
				Red.setVisible(false);
				settings = true;
				running  = false;
				Ver=false;
				repaint();
			}
		});
		this.add(Settingsbutton);
		Restartbutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-140, 200, 40);
		Restartbutton.setBackground(new Color(0x0009FF));
		Restartbutton.setText("Restart");
		Restartbutton.setForeground(Color.WHITE);
		Restartbutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Restartbutton.setFocusable(false);
		Restartbutton.setVisible(false);
		Restartbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Startbutton.setVisible(false);
				Settingsbutton.setVisible(false);
				
			
				Backbutton.setVisible(false);
			
				
				slider.setVisible(false);
				check.setVisible(false);
				RB.setVisible(false);
				SnakeColor.setVisible(false);
				Green.setVisible(false);
				Purple.setVisible(false);
				Cocktail.setVisible(false);
				
				Restartbutton.setVisible(false);
				Homebutton.setVisible(false);
				Exitbutton.setVisible(false);
				
				start=true;
				Ver=true;
				repaint();
				RestartGame();
				
			}
		});
		
		this.add(Restartbutton);
		Homebutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-200, 200, 40);
		Homebutton.setBackground(new Color(0x0009FF));
		Homebutton.setText("Home");
		Homebutton.setForeground(Color.WHITE);
		Homebutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Homebutton.setFocusable(false);
		Homebutton.setVisible(false);
		Homebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				Settingsbutton.setVisible(false);
				Backbutton.setVisible(false);
				
				slider.setVisible(false);
				check.setVisible(false);
				RB.setVisible(false);
				SnakeColor.setVisible(false);
				
				
				Homebutton.setVisible(false);
				Restartbutton.setVisible(false);
				Exitbutton.setVisible(true);
				Settingsbutton.setVisible(true);
				Startbutton.setVisible(true);
				Green.setVisible(false);
				Purple.setVisible(false);
				Cocktail.setVisible(false);
				settings =false;
				running = false;
				Ver =false;
				BodyParts=6;
				AppleEaten=0;
				repaint();
				
			}
		});
		
		this.add(Homebutton);
		Exitbutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-80, 200, 40);
		Exitbutton.setBackground(new Color(0x0009FF));
		Exitbutton.setText("Exit");
		Exitbutton.setForeground(Color.WHITE);
		Exitbutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Exitbutton.setFocusable(false);
		Exitbutton.setVisible(true);
		Exitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			 GameFrame.frame.dispose();

			}
		});
		
		this.add(Exitbutton);
		Backbutton.setBounds((SCREEN_WIDTH-195)/2, SCREEN_HEIGHT-80, 200, 40);
		Backbutton.setBackground(new Color(0x0009FF));
		Backbutton.setText("Back");
		Backbutton.setForeground(Color.WHITE);
		Backbutton.setFont( new Font("Ink Free",Font.BOLD, 25));
		Backbutton.setFocusable(false);
		Backbutton.setVisible(false);
		Backbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Restartbutton.setVisible(false);
				Homebutton.setVisible(false);
			
				Green.setVisible(false);
				Purple.setVisible(false);
				Cocktail.setVisible(false);
				
				
				Backbutton.setVisible(false);
				Settingsbutton.setVisible(true);
				Startbutton.setVisible(true);
				Exitbutton.setVisible(true);
				settings =false;
				running = false;
				Ver =false;
				BodyParts=6;
				AppleEaten=0;
				slider.setVisible(false);
				check.setVisible(false);
				RB.setVisible(false);
				SnakeColor.setVisible(false);
				repaint();
				
			}
		});
		
		this.add(Backbutton);
		time.start();
		Blue.setVisible(false);
		
		Green.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Green.setVisible(false);
				Purple.setVisible(true);
				Cocktail.setVisible(true);
				Blue.setVisible(true);
				Red.setVisible(false);
				Yellow.setVisible(false);
				Orange.setVisible(false);
				snakecolor = "Green";
				ChooseColor = 0;
			}
		});
		
		Purple.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Green.setVisible(true);
				Purple.setVisible(false);
				Cocktail.setVisible(true);
				Blue.setVisible(true);
				Red.setVisible(false);
				Yellow.setVisible(false);
				Orange.setVisible(false);
				snakecolor = "Purple";
				ChooseColor = 1;
			}
		});
		
		Cocktail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Green.setVisible(true);
				Purple.setVisible(true);
				Cocktail.setVisible(false);
				Blue.setVisible(false);
				Red.setVisible(true);
				Yellow.setVisible(false);
				Orange.setVisible(false);
				snakecolor = "Cocktail";
				ChooseColor = 2;
			}
		});
		Blue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Green.setVisible(false);
				Purple.setVisible(true);
				Cocktail.setVisible(false);
				Blue.setVisible(false);
				Red.setVisible(true);
				Yellow.setVisible(true);
				Orange.setVisible(false);
				snakecolor = "Blue";
				ChooseColor = 3;
			}
		});
		Red.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Green.setVisible(false);
				Purple.setVisible(true);
				Cocktail.setVisible(false);
				Blue.setVisible(false);
				Red.setVisible(false);
				Yellow.setVisible(true);
				Orange.setVisible(true);
				snakecolor = "Red";
				ChooseColor = 4;
			}
		});
		Yellow.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Green.setVisible(true);
		Purple.setVisible(false);
		Cocktail.setVisible(true);
		Blue.setVisible(false);
		Red.setVisible(false);
		Yellow.setVisible(false);
		Orange.setVisible(true);
		snakecolor = "Yellow";
		ChooseColor = 5;
	}
});
		Orange.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Green.setVisible(false);
		Purple.setVisible(true);
		Cocktail.setVisible(true);
		Blue.setVisible(true);
		Red.setVisible(false);
		Yellow.setVisible(false);
		Orange.setVisible(false);
		snakecolor = "Orange";
		ChooseColor = 6;
	}
});
		
		
		this.add(RB);
		
	} 
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		FirstPane(g);
		
	}
	public void playsong() {
		
		clip1.start();
		clip1.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void FirstPane(Graphics g) {
		

		if(!settings &&!running && !Ver) {
			
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Snake Game", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, (SCREEN_HEIGHT-150)/2);
		}
		if(settings && !running && !Ver) {
			
		
			settings(g);
		}
		if(start){
			
				draw(g);
				
		}

	}
	public void settings(Graphics g) {
		
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 25));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Game Acceleration (sec per step)", (SCREEN_WIDTH - metrics3.stringWidth("Game Acceleration (sec per step)"))/2, 100);
		slider.setBounds((SCREEN_WIDTH-400)/2, 150, 400,50);
		slider.setBackground(Color.black);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(30);
		 slider.setPaintLabels(true);
		 slider.setForeground(Color.white);
		 slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				DELAY=slider.getValue();
			}
		});
		
		this.add(slider);
		check.setBounds((SCREEN_WIDTH-250)/3, 225, 250, 50);
		check.setText("   Show Lines");
		check.setFont( new Font("Ink Free",Font.BOLD, 25));
		check.setForeground(Color.red);
		check.setBackground(Color.black);
		check.setFocusable(false);
		
		this.add(check);
		
		SnakeColor.setBounds((SCREEN_WIDTH-250)/3, 300, 250, 50);
		SnakeColor.setText("Snake Color : "+snakecolor);
		SnakeColor.setFont( new Font("Ink Free",Font.BOLD, 25));
		SnakeColor.setForeground(Color.red);
		
		this.add(SnakeColor);
		
		RB.setBounds((SCREEN_WIDTH-250)/3, 375, 500, 50);
		RB.setBackground(Color.black);
		RB.setLayout(new FlowLayout());

		Green.setBackground(Color.black);
		Green.setOpaque(true);
		Green.setFont( new Font("Ink Free",Font.BOLD, 25));
		Green.setForeground(Color.green);
		Green.setFocusable(false);
		
		
		Purple.setBackground(Color.black);
		Purple.setOpaque(true);
		Purple.setFont( new Font("Ink Free",Font.BOLD, 25));
		Purple.setForeground(new Color(0x9E30FF));
		Purple.setFocusable(false);
		
		
		
		Cocktail.setBackground(Color.black);
		Cocktail.setOpaque(true);
		Cocktail.setFont( new Font("Ink Free",Font.BOLD, 25));
		Cocktail.setForeground(Color.white);
		Cocktail.setFocusable(false);
		
		Blue.setBackground(Color.black);
		Blue.setOpaque(true);
		Blue.setFont( new Font("Ink Free",Font.BOLD, 25));
		Blue.setForeground(Color.blue);
		Blue.setFocusable(false);
		
		Red.setBackground(Color.black);
		Red.setOpaque(true);
		Red.setFont( new Font("Ink Free",Font.BOLD, 25));
		Red.setForeground(Color.red);
		Red.setFocusable(false);
		
		Yellow.setBackground(Color.black);
		Yellow.setOpaque(true);
		Yellow.setFont( new Font("Ink Free",Font.BOLD, 25));
		Yellow.setForeground(Color.yellow);
		Yellow.setFocusable(false);
		
		Orange.setBackground(Color.black);
		Orange.setOpaque(true);
		Orange.setFont( new Font("Ink Free",Font.BOLD, 25));
		Orange.setForeground(Color.orange);
		Orange.setFocusable(false);
		
		
		
		
		
		Green.setText("   Green");
		Purple.setText("   Purple");
		Cocktail.setText("   Cocktail");
		Blue.setText("   Blue");
		Red.setText("   Red");
		Yellow.setText("   Yellow");
		Orange.setText("   Orange");
		
		GB.add(Red);
		GB.add(Yellow);
		GB.add(Orange);
		GB.add(Green);
		GB.add(Purple);
		GB.add(Cocktail);
		GB.add(Blue);
		
		RB.add(Red);
		RB.add(Yellow);
		RB.add(Orange);
		RB.add(Green);
		RB.add(Purple);
		RB.add(Cocktail);
		RB.add(Blue);
		
	}
	
	public void draw(Graphics g) {
		
		if(running) {
			if(check.isSelected()) {
			for(int i=0;i<SCREEN_WIDTH/UNITS_SIZE+1;i++) {
				g.drawLine(i*UNITS_SIZE, 0, i*UNITS_SIZE, SCREEN_HEIGHT);
			}
			for(int i=0;i<SCREEN_HEIGHT/UNITS_SIZE+1;i++) {
				g.drawLine(0, i*UNITS_SIZE, SCREEN_WIDTH, i*UNITS_SIZE);
			}
			}
			g.setColor(Color.red);
			g.fillOval(AppleX, AppleY, UNITS_SIZE, UNITS_SIZE);
		
			
			if(ChooseColor == 0 ) {
			for(int i = 0; i< BodyParts;i++) {
				if(i == 0) {
					g.setColor(new Color(0x26FF0E));
					g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
				}
				else {
					g.setColor(new Color(0x28891E));
					g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
				}			
			}
		}
			if(ChooseColor == 1 ) {
				for(int i = 0; i< BodyParts;i++) {
					if(i == 0) {
						g.setColor(new Color(0x9131DB));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}
					else {
						g.setColor(new Color(0x48007F));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}			
				}
			}
			if(ChooseColor == 2 ) {
				for(int i = 0; i< BodyParts;i++) {
					
						
						g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
							
				}
			}
			if(ChooseColor == 3 ) {
				for(int i = 0; i< BodyParts;i++) {
					if(i == 0) {
						g.setColor(new Color(0x5B9EEB));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}
					else {
						g.setColor(new Color(0x184D89));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}			
				}
			}
			if(ChooseColor == 4 ) {
				for(int i = 0; i< BodyParts;i++) {
					if(i == 0) {
						g.setColor(new Color(0xB66976));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}
					else {
						g.setColor(new Color(0xFD002A));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}			
				}
			}
			if(ChooseColor == 5 ) {
				for(int i = 0; i< BodyParts;i++) {
					if(i == 0) {
						g.setColor(new Color(0xEFEF61));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}
					else {
						g.setColor(new Color(0xFFFF00));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}			
				}
			}
			if(ChooseColor == 6 ) {
				for(int i = 0; i< BodyParts;i++) {
					if(i == 0) {
						g.setColor(new Color(0xEB893F));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}
					else {
						g.setColor(new Color(0xFF6F00));
						g.fillRect(X[i], Y[i], UNITS_SIZE, UNITS_SIZE);
					}			
				}
			}
		
		
		
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+AppleEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+AppleEaten))/2, g.getFont().getSize());
		}
		else{
			GameOver(g);	
		}
		
	}
	public void StartGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		
		
	}
	public void RestartGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		BodyParts=6;
		AppleEaten=0;
		timer.restart();
		
		
	}
	public void newApple() {
		AppleX = random.nextInt((int)(SCREEN_WIDTH/UNITS_SIZE))*UNITS_SIZE;
		AppleY = random.nextInt((int)(SCREEN_HEIGHT/UNITS_SIZE))*UNITS_SIZE;
	}
	public void checkApple() {
		if((X[0] == AppleX) && (Y[0] == AppleY)) {
			BodyParts++;
			AppleEaten++;
			newApple();
			clip.start();
			clip.setMicrosecondPosition(0);
			}
	}
	public void checkCollisions() {
		//checks if head collides with body
				for(int i = BodyParts;i>0;i--) {
					if((X[0] == X[i])&& (Y[0] == Y[i])) {
						running = false;
					}
				}
				//check if head touches left border
				if(X[0] < 0) {
					running = false;
				}
				//check if head touches right border
				if(X[0] > SCREEN_WIDTH) {
					running = false;
				}
				//check if head touches top border
				if(Y[0] < 0) {
					running = false;
				}
				//check if head touches bottom border
				if(Y[0] > SCREEN_HEIGHT) {
					running = false;
				}
				
				if(!running) {
					timer.stop();
				}
	}
	public void Move() {
		for(int i = BodyParts;i>0;i--) {
			X[i] = X[i-1];
			Y[i] = Y[i-1];
		}
		
		switch(direction) {
		case 'U':
			Y[0] = Y[0] - UNITS_SIZE;
			break;
		case 'D':
			Y[0] = Y[0] + UNITS_SIZE;
			break;
		case 'L':
			X[0] = X[0] - UNITS_SIZE;
			break;
		case 'R':
			X[0] = X[0] + UNITS_SIZE;
			break;
		}
		
	}
	public void GameOver(Graphics g) {
		//Score
		Restartbutton.setVisible(true);
		Homebutton.setVisible(true);
		Exitbutton.setVisible(true);
		start=false;
		Ver=false;
				
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+AppleEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+AppleEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	
				for(int i = 0;i<BodyParts;i++) {
					if(i==0) {
						X[i] = i;
						Y[i] = i;
					}
					
					
					X[i] =  UNITS_SIZE*i;
					Y[i] =  UNITS_SIZE*i;
				}
				
				direction = 'R';
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
				
				if(running) {
					Move();
					checkApple();
					checkCollisions();
				}
				repaint();
				
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
			
				public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						if(direction != 'R') {
							direction = 'L';
						}
						break;
					case KeyEvent.VK_RIGHT:
						if(direction != 'L') {
							direction = 'R';
						}
						break;
					case KeyEvent.VK_UP:
						if(direction != 'D') {
							direction = 'U';
						}
						break;
					case KeyEvent.VK_DOWN:
						if(direction != 'U') {
							direction = 'D';
						}
						break;
					}
				
				}
				
	}		

}
