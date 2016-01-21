import java.awt.Graphics;
import java.util.LinkedList;

public class GameObjectHandler {
	//an linked list to store all game objects
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	private LinkedList<GameObject> bulletObjects = new LinkedList<GameObject>();
	
	//update all game objects'logic
	public void updateGameObjectsLogic(){
		GameObject temp;
		for(int i = 0; i < gameObjects.size(); i ++){
			temp = gameObjects.get(i);
			temp.updateLogic();
		}
		
		for(int i = 0; i < bulletObjects.size() ; i++){
			temp = bulletObjects.get(i);
			temp.updateLogic();
		}
	}
	
	//update all game objects'graphic
	public void updateGameObjectsGraphic(Graphics g){

		GameObject temp;
		for(int i = 0; i < gameObjects.size(); i ++){
			temp = gameObjects.get(i);
			temp.updateGraphic(g);
		}

		for(int i = 0; i < bulletObjects.size() ; i++){
			temp = bulletObjects.get(i);
			temp.updateGraphic(g);
		}
	}
	
	//add object to the game objects handler
	public void addObject(GameObject object){
		if(!(object instanceof Bullet)){
			gameObjects.add(object);
		}else{
			bulletObjects.add(object);
		}
	}
	
	//remove object from the game object handler
	public void removeObject(GameObject object){
		if(object instanceof Bullet){
			bulletObjects.remove(object);
		}else{
			gameObjects.remove(object);
		}
	}
	
	public void removeAllObject(){
//		GameObject temp;
//		for(int i = 0; i < gameObjects.size(); i ++){
//			temp = gameObjects.get(i);
//			
//			if(temp.getGameObjID() == GameObjectID.Player){
				gameObjects.clear();
////				if(Main.state != Main.GameState.Gameover){
////					
////				}
//			}
//		}
	}
	
	public LinkedList<GameObject> getAllObjects(){
		return this.gameObjects;
	}
	
	public LinkedList<GameObject> getBulletObjects(){
		return this.bulletObjects;
	}
}