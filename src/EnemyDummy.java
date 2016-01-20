import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyDummy extends GameObject{

	public EnemyDummy(int x, int y, GameObjectID id) {
		super(x, y, id);
		veloY = 1;
		dmg = -20;
		health = 10;
		
		width = 50;
		height = 50;
	}

	@Override
	public void updateLogic() {
		if(y < 0 || y > Main.HEIGHT - height) veloY *= -1;
		y += veloY;
	}

	@Override
	public void updateGraphic(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
