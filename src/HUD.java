
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HUD {
	
	public static int HEALTH = 100;
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
	}
	
	public void updateHUDGraphic(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}
}
