import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Earth extends GameObject {
	private GameObjectHandler handler;
	private int colorValue = 255;
	public static boolean dead;

	/* constructor of Earth */
	public Earth(int x, int y, GameObjectID id, GameObjectHandler handler) {
		super(x, y, id);
		health = 500;
		this.handler = handler;
	}

	/* update Earth health and color value */
	@Override
	public void updateLogic() {
		health = Main.constrain(health, 0, 500);
		if (health <= 0) {
			dead = true;
		}
		this.colorValue = (int) (health / 500f * 255);
		this.colorValue = Main.constrain(this.colorValue, 0, 255);
		collisionAnalyse();
	}

	/* check the collision of the Earth with opponent */
	private void collisionAnalyse() {
		GameObject temp;
		for (int i = 0; i < handler.getAllObjects().size(); i++) {
			temp = handler.getAllObjects().get(i);
			if (!(temp instanceof Earth) && !(temp instanceof Player)) {
				if (this.getBounds().intersects(temp.getBounds())) {
					this.changeHealth(temp);
					handler.removeObject(temp);
				}
			}
		}
	}

	/* change color of the earth health bar */
	@Override
	public void updateGraphic(Graphics g) {
		// This is an example to make a representation to the object Earth
		int currentBWidth = (int) (health / 500f * Main.WIDTH);
		g.setColor(Color.gray);
		g.fillRect(0, Main.HEIGHT - HUD.height, Main.WIDTH, HUD.height);
		g.setColor(new Color(75, this.colorValue, 0));
		g.fillRect(0, Main.HEIGHT - HUD.height, currentBWidth, HUD.height);
		g.setColor(Color.white);
		g.drawRect(0, Main.HEIGHT - HUD.height, Main.WIDTH, HUD.height);
	}

	/* used to check if 2 object will be intersect */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, Main.WIDTH, HUD.height);
	}

}