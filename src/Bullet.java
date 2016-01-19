import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	private GameObjectHandler handler;
	public Bullet(int x, int y, GameObjectID id, GameObjectHandler handler) {
		super(x, y, id);
		veloY = 10;
		this.handler = handler;
	}
	
	
	

	@Override
	public void updateLogic() {

		if(y < 0 || y > Main.HEIGHT - 15 - 28){
			this.x = -100;
			this.y = 100;
		}
		y -= veloY;
		collisionAnalyse();
	}

	@Override
	public void updateGraphic(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 10, 10);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	
	private void collisionAnalyse() {

		for(GameObject object : handler.getAllObjects()){
			if(object instanceof Enemy){
				if(this.getBounds().intersects(object.getBounds())){
					handler.removeObject(object);
				}
			}
		}
	}
	
}