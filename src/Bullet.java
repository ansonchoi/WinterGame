import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	public static final int width = 10, height = 10; // bullet size

	private GameObjectHandler handler;

	/* constructor of Bullet */
	public Bullet(int x, int y, GameObjectID id, GameObjectHandler handler) {
		super(x, y, id);
		veloY = 10;
		dmg = -5;
		this.handler = handler;
	}

	/* bullet move upward and check collision */
	@Override
	public void updateLogic() {
		y -= veloY;
		collisionAnalyse();
	}

	/* update bullet position in the graph */
	@Override
	public void updateGraphic(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}

	/* used to check collision */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/* check collision between bullet and enemy */
	private void collisionAnalyse() {

		GameObject object;
		for (int i = 0; i < handler.getAllObjects().size(); i++) {
			object = handler.getAllObjects().get(i);
			if (!(object instanceof Player)) {
				if (this.getBounds().intersects(object.getBounds())) {
					object.changeHealth(this);
					if (object.getHealth() <= 0)
						handler.removeObject(object);
					handler.removeObject(this);
				}
			}
		}

		// This function is used to delete the bullet if it goes out of the
		// boundary
		if (this.getY() < 0 + HUD.height)
			handler.removeObject(this);
	}

}