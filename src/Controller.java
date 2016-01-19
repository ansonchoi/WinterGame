
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	
	private GameObjectHandler handler;
	private int movingSpeed;
	private int bulletCounter = 0;
	
	public Controller(GameObjectHandler handler){
		this.handler = handler;
		movingSpeed = 5;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(GameObject object : this.handler.getAllObjects()){
			if(object instanceof Player){
				switch(key){
					case KeyEvent.VK_UP: object.setVeloY(-movingSpeed); break;
					case KeyEvent.VK_DOWN: object.setVeloY(movingSpeed); break;
					case KeyEvent.VK_LEFT: object.setVeloX(-movingSpeed); break;
					case KeyEvent.VK_RIGHT: object.setVeloX(movingSpeed); break;
					case KeyEvent.VK_ESCAPE: System.exit(1);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		for(GameObject object : this.handler.getAllObjects()){
			
			if(object instanceof Player){
				switch(key){
					case KeyEvent.VK_UP: object.setVeloY(0); break;
					case KeyEvent.VK_DOWN: object.setVeloY(0); break;
					case KeyEvent.VK_LEFT: object.setVeloX(0); break;
					case KeyEvent.VK_RIGHT: object.setVeloX(0); break;
					case KeyEvent.VK_SPACE:
					handler.getBulletObjects().get(bulletCounter).setX(object.getX()+8);
					handler.getBulletObjects().get(bulletCounter).setY(object.getY()-10);
					bulletCounter++;
					if(bulletCounter==14)bulletCounter=0;
					break;

				}
			}
		}
	}
}
