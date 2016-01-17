
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HUD {
	
	public static int HEALTH = 100;
	
	public void updateGameObjectsLogic(){
		HEALTH--;

		HEALTH = Main.constrain(HEALTH, 0, 100);
	}
	
	public void updateGameObjectsGraphic(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}
}
