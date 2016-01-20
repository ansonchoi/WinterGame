
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y; //position of the object in the game
	protected GameObjectID id;
	protected int veloX, veloY; //velocity of the object to x and y direction
	protected int dmg; //For enemy, it take the health of player down; For medicine, it boosts the health of player;
	protected int health; // if health drop to 0, the object will destroy
	
	public GameObject(int x, int y, GameObjectID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public GameObjectID getGameObjID(){
		return this.id;
	}
	public abstract void updateLogic();
	
	public abstract void updateGraphic(Graphics g);
	
	public void changeHealth(GameObject p){
		this.health += p.getDmg();
	}
	
	//used to check if 2 object will be intersect
	public abstract Rectangle getBounds();
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
//	public int getVeloX() {
//		return veloX;
//	}
//	
//	public int getVeloY() {
//		return veloY;
//	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVeloX(int veloX) {
		this.veloX = veloX;
	}

	public void setVeloY(int veloY) {
		this.veloY = veloY;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public int getDmg(){
		return this.dmg;
	}
}
