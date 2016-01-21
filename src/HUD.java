
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HUD extends MouseAdapter{

	public static int HEALTH = 100;
	private int colorValue = 255;
	public final static int height = 40;
	private int HPBarWidth = 200;
	private int score = 0;
	private GameObjectHandler handler;
	private Main main;

	public HUD(GameObjectHandler handler, Main main){
		this.handler = handler;
		this.main = main;
	}

	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		int scoreBarWidth = this.HPBarWidth*3/4;
		int button1_xpos = this.HPBarWidth + scoreBarWidth;
		int button_width = (Main.WIDTH-button1_xpos)/2;
		int button2_xpos = this.HPBarWidth + scoreBarWidth + button_width;
		int buttonYes_xpos = 50;
		int buttonYes_ypos = 300;
		int buttonNo_xpos = Main.WIDTH-150;
		int buttonNo_ypos = 300;
		int buttonConfirm_width = 100;
		//		System.out.println(mx);
		if(main.state==GameState.Game && Main.isMouseOver(mx, my, button1_xpos, 0, button_width, HUD.height)){
			if(Main.isPaused)
				Main.isPaused = false;
			else if (!Main.checkExit)
				Main.isPaused = true;


		}else if(main.state==GameState.Game && Main.isMouseOver(mx, my, button2_xpos, 0, button_width, HUD.height)){
			Main.checkExit = true;
			if(Main.isPaused){
				Main.isPaused = false;
			}
		}else if(Main.checkExit){
			if( Main.isMouseOver(mx, my, buttonYes_xpos, buttonYes_ypos, buttonConfirm_width, HUD.height) ){
				Main.exit = true;
				Main.checkExit = false;
			}else if( Main.isMouseOver(mx, my, buttonNo_xpos, buttonNo_ypos, buttonConfirm_width, HUD.height) ){
				Main.exit = false;
				Main.checkExit = false;
			}
		}
		else if(Main.isPaused){
			if( Main.isMouseOver(mx, my, 0, 0, button2_xpos, HUD.height) ||  Main.isMouseOver(mx, my, 0, HUD.height, Main.WIDTH, Main.HEIGHT-HUD.height) )
				Main.isPaused = false;
		}
		//		else if(main.state==GameState.Game && Main.isMouseOver(mx, my, button2_xpos, 0, button_width, HUD.height)){
		//			System.out.println("button2");
		//		}else if(main.state==GameState.Pause && Main.isMouseOver(mx, my, button1_xpos, 0, button_width, HUD.height)){
		//			main.state = GameState.Game;
		//		}
	}

	public void updateHUDLogic(){
		GameObject player;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			if(handler.getAllObjects().get(i) instanceof Player){
				player = handler.getAllObjects().get(i);
				HEALTH = player.getHealth();
			}
		}
		HEALTH = Main.constrain(HEALTH, 0, 100);

		colorValue = Main.constrain(colorValue, 0, 255);

		colorValue = HEALTH * 2;
		if(HEALTH!=0 && !Earth.dead){
			score++;
		}
	}

	public void updateHUDGraphic(Graphics g){
		// Health
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.HPBarWidth, HUD.height);
		g.setColor(new Color(75, colorValue,0));
		g.fillRect(0, 0, HUD.HEALTH * 2, HUD.height);
		g.setColor(Color.white);
		g.drawRect(0, 0, this.HPBarWidth, HUD.height);

		// Score
		int scoreBarWidth = this.HPBarWidth*3/4;
		g.setColor(Color.gray);
		g.fillRect(this.HPBarWidth, 0, scoreBarWidth , HUD.height);
		g.setColor(Color.white);
		g.drawRect(this.HPBarWidth, 0, scoreBarWidth, HUD.height);
		g.drawString("Score: " + score, this.HPBarWidth+5, HUD.height/2+5);

		// Button
		int button1_xpos = this.HPBarWidth + scoreBarWidth;
		int button_width = (Main.WIDTH-button1_xpos)/2;
		g.setColor(Color.gray);
		g.fillRect(button1_xpos, 0, button_width, HUD.height);
		g.setColor(Color.red);
		g.drawString("Pause", button1_xpos+8, 25);
		g.setColor(Color.white);
		g.drawRect(button1_xpos, 0, button_width, HUD.height);
		// Button 2
		int button2_xpos = this.HPBarWidth + scoreBarWidth + button_width;
		g.setColor(Color.gray);
		g.fillRect(button2_xpos, 0, button_width, HUD.height);
		g.setColor(Color.red);
		g.drawString("Exit", button2_xpos+12, 25);
		g.setColor(Color.white);
		g.drawRect(button2_xpos, 0, button_width, HUD.height);
	}

	public int score(){
		return this.score;
	}
	public void resetScore(){
		this.score = 0;
	}
}
