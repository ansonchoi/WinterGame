import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Font;


public class Main extends Canvas implements Runnable{
	
	public static final long serialVersionUID = 1L;
	//Size of the gaming space
	public static final int WIDTH = 450, HEIGHT = WIDTH * 12 / 9;
	
	//State checkers
	public GameState state = GameState.Menu;
	private boolean isRunning = false;
	public static boolean isPaused = false;
	public static boolean checkExit = false;
	public static boolean exit = false;
	
	private Thread thread;
	private GameObjectHandler handler;
	private Menu menu;
	private HUD hud;
	private Spawner spawner;
	private SoundMaker sound;
	private Controller controller;
	
	public Main(){
		handler = new GameObjectHandler();
		hud = new HUD(handler, this);
		spawner = new Spawner(handler);
		sound = new SoundMaker();
		menu = new Menu(this, spawner, hud, sound);
		controller = new Controller(handler);
		
		this.addKeyListener(controller);
		this.addMouseListener(menu);
		this.addMouseListener(hud);
		
		//Size of the window and the name of the game
		new Window(WIDTH + 6, HEIGHT + 28, "Skip the ball!", this);
	}
	
	//Starting the content of game inside the window
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	//Stop the content of the game inside the window
	public synchronized void stop(){
		try{
			thread.join();
			isRunning = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Game loop that a lot people use it to maintain the animation of the game
	@Override
	public void run() {
		this.requestFocus();
		long lastLoopTime = System.nanoTime();
		final double targetFPS = 60.0;
		final double optimalTime = 1000000000 / targetFPS;
		double delta = 0;
		
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastLoopTime) / optimalTime;
			lastLoopTime = now;
			
			//update the content/logic of the game
			while(delta >= 1){
				updateGameLogic();
				delta--;
			}
			
			//draw everything of the game at that time
			if(isRunning)	updateGameGraphic();

			
			
		}
		
		stop();
		
	}
	
	public void updateGameLogic(){
		sound.playBGM(state);
		if(state == GameState.Game){
			if(!isPaused && !checkExit){
				handler.updateGameObjectsLogic();
				hud.updateHUDLogic();
				spawner.spawn();
				
//				if(checkExit){
//					exit = true;
//				}
				
				if(HUD.HEALTH <= 0 || Earth.dead || this.exit){
					HUD.HEALTH = 100;
					Earth.dead = false;
					this.checkExit = false;
					this.exit = false;
					state = GameState.Gameover;
					controller.resetMotions();
					handler.removeAllObject();
					spawner.reset();
				}
			}
		}else if(state == GameState.Menu){
			
		}
		
	}
	
	//draw everything function
	public void updateGameGraphic(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//screen color
		g.setColor(Color.black); 
		g.fillRect(0, 0, WIDTH,HEIGHT);
		
		if(this.isPaused){
			g.setColor(Color.red);
//			g.drawString("Paused", 200, 200);  
			g.drawString("Click to continue", 175, 300);
		}
		
		if(this.checkExit){
			
			int button1_xpos = 50;
			int button1_ypos = 300;
			int button2_xpos = Main.WIDTH-150;
			int button2_ypos = 300;
			int button_width = 100;
			
			//Exit string
			g.setFont(new Font("Airal", 0, 14)); 
			g.setColor(Color.red);
			g.drawString("Confirm to exit?", Main.WIDTH/2-45, HUD.height + 150);
			
			//button for yes
			g.setColor(Color.yellow);
			g.fillRect(button1_xpos, button1_ypos, button_width, HUD.height);
			g.setColor(Color.red);
			g.drawString("Yes", button1_xpos+40, button1_ypos+25);
			g.setColor(Color.white);
			g.drawRect(button1_xpos, button1_ypos, button_width, HUD.height);
			
			//button for no
			g.setColor(Color.pink);
			g.fillRect(button2_xpos, button2_ypos, button_width, HUD.height);
			g.setColor(Color.red);
			g.drawString("No", button2_xpos+40, button2_ypos+25);
			g.setColor(Color.white);
			g.drawRect(button2_xpos, button2_ypos, button_width, HUD.height);
			g.setFont(new Font("default",0,13)); 
//			g.setColor(Color.red);
//			g.drawString("Yes", 200, 200);
//			g.drawString("No", 175, 300);
		}
		
		if(state == GameState.Game){
			handler.updateGameObjectsGraphic(g);
			hud.updateHUDGraphic(g);
		}else{
			menu.updateMenuGraphic(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	//Help functions:
	
	//constrain a variable within a boundaries
	public static int constrain(int var, int min, int max){
		if(var < min) return var = min;
		else if(var > max) return var = max;
		else return var;
	}
	
	//check if the mouse is clicked in desired region
	public static boolean isMouseOver(int mx, int my, int x, int y, int width, int height){
		if((mx > x && mx < x + width)
			&&(my > y && my < y + height)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}