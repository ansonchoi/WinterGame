
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y; //position of the object in the game
	protected GameObjectID id;
	protected int veloX, veloY; //velocity of the object to x and y direction
	
	public GameObject(int x, int y, GameObjectID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void updateLogic();
	
	public abstract void updateGraphic(Graphics g);
	
	//used to check if 2 object will be intersect
	public abstract Rectangle getBounds();
	
/**	Current wont use them, actually it is not a good practice to set all private/protected variables
 * 	to have both public getter and setter (if do so, why dont just set those variable to be public)
 * **/
//	public int getX() {
//		return this.x;
//	}
//	
//	public int getY() {
//		return this.y;
//	}
//	
//	public void setX(int x) {
//		this.x = x;
//	}
//	
//	public void setY(int y) {
//		this.y = y;
//	}
//
//	public int getVeloX() {
//		return this.veloX;
//	}
//	
//	public int getVeloY() {
//		return this.veloY;
//	}
	
	public void setVeloX(int veloX) {
		this.veloX = veloX;
	}

	public void setVeloY(int veloY) {
		this.veloY = veloY;
	}

	public GameObjectID getGameObjectID(){
		return this.id;
	}
// test
//	public void setGameObjectID(GameObjectID id){
//		this.id = id;
//	}
	
}
