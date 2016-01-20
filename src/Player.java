
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private GameObjectHandler handler;
	private boolean fireBullet = false;
	private int fireTimeGap;
	
	public Player(int x, int y, GameObjectID id, GameObjectHandler handler) {
		super(x, y, id);
		this.handler = handler;
		health = 100;
	}

	@Override
	public void updateLogic() {
		x += veloX;
		y += veloY;
		
		//WIDTH, HEIGTH - object size - number (which is covered by the window board)
		x = Main.constrain(x, 0, Main.WIDTH - 30 - 5);
		y = Main.constrain(y, 0, Main.HEIGHT - 30 - 28);
		
		//Here set the frequent of firing bullet by change the value of below
		//The higher number less frequent, verse vice
		fireTimeGap %= 10;
		
		//if space button is holding, create bullet at the mid of the player constantly
		if(fireBullet && fireTimeGap++ == 0){
			handler.addObject(new Bullet(x + 20, y, GameObjectID.Bullet, handler));
		}
		
		collisionAnalyse();
	}

	private void collisionAnalyse() {
		GameObject temp;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			temp = handler.getAllObjects().get(i);
			if(!(temp instanceof Player)){
				if(this.getBounds().intersects(temp.getBounds())){
					// remove object 
					// reduce health 
					this.changeHealth(temp);
					handler.removeObject(temp);
				}
			}
		}
	}

	@Override
	public void updateGraphic(Graphics g) {
		//This is an example to make a representation to the object player
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}
	
	public void setFire(boolean fire){
		this.fireBullet = fire;
	}
	
}
