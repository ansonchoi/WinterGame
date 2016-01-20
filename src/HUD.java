
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HUD {

	public static int HEALTH = 100;
	private int colorValue = 255;
	public final static int height = 40;
	private int HPBarWidth = 200;
	private int score = 0;
	private GameObjectHandler handler;


	public HUD(GameObjectHandler handler){
		this.handler = handler;
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
		if(HEALTH!=0){
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
		g.setColor(Color.white);
		g.drawRect(button1_xpos, 0, button_width, HUD.height);
		// Button 2
		int button2_xpos = this.HPBarWidth + scoreBarWidth + button_width;
		g.setColor(Color.gray);
		g.fillRect(button2_xpos, 0, button_width, HUD.height);
		g.setColor(Color.white);
		g.drawRect(button2_xpos, 0, button_width, HUD.height);
	}
	
	public int score(){
		return this.score;
	}
}
