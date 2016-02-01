import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	protected int x, y; // position of the object in the game
	protected GameObjectID id;
	protected int veloX, veloY; // velocity of the object to x and y direction
	protected int dmg; // For enemy, it take the health of player down; 
	protected int health;
	protected BufferedImage objectImg;

	/* constructor of GameObject */
	public GameObject(int x, int y, GameObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public GameObjectID getGameObjID() {
		return this.id;
	}

	public abstract void updateLogic();

	public abstract void updateGraphic(Graphics g);

	/* update the health of the object */
	public void changeHealth(GameObject p) {
		this.health += p.getDmg();
	}

	/* used to check if 2 object will be intersect */
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getobjectImg() {
		return objectImg;
	}
	
	public void setVeloX(int veloX) {
		this.veloX = veloX;
	}

	public void setVeloY(int veloY) {
		this.veloY = veloY;
	}

	public int getHealth() {
		return this.health;
	}

	public int getDmg() {
		return this.dmg;
	}
}
