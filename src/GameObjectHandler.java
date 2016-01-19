
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Iterator;

public class GameObjectHandler {
	//an linked list to store all game objects
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	private LinkedList<GameObject> bulletObjects = new LinkedList<GameObject>();
	
	//update all game objects'logic
	public void updateGameObjectsLogic(){
		try{
			for(GameObject object : gameObjects){
				object.updateLogic();
			}
			Iterator<GameObject> iterator = bulletObjects.iterator();
			while (iterator.hasNext()){
				GameObject object = iterator.next();
				object.updateLogic();
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//update all game objects'graphic
	public void updateGameObjectsGraphic(Graphics g){
		try{
			for(GameObject object : gameObjects){
				object.updateGraphic(g);
			}
			for(GameObject object : bulletObjects){
				object.updateGraphic(g);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//add object to the game objects handler
	public void addObject(GameObject object){
		try{
		if(!(object instanceof Bullet)){
			gameObjects.add(object);

		}else{
			bulletObjects.add(object);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//remove object from the game object handler
	public void removeObject(GameObject object){
		System.out.println("Trying to remove an object");
		if(object instanceof Bullet){
			bulletObjects.remove(object);
		}else{
			gameObjects.remove(object);
		}
		System.out.println("Finish removing an object");
	}
	
	public LinkedList<GameObject> getAllObjects(){
		return this.gameObjects;
	}
	public LinkedList<GameObject> getBulletObjects(){
		return this.bulletObjects;
	}
}
