
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	
	private GameObjectHandler handler;
	private int movingSpeed;
	private int bulletCounter;
	
	private boolean upIsHolding;
	private boolean downIsHolding;
	private boolean leftIsHolding;
	private boolean rightIsHolding;
	
	public Controller(GameObjectHandler handler){
		this.handler = handler;
		movingSpeed = 5;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(GameObject object : this.handler.getAllObjects()){
			if(object instanceof Player){
				switch(key){
				case KeyEvent.VK_UP: upIsHolding = true; break;
				case KeyEvent.VK_DOWN: downIsHolding = true; break;
				case KeyEvent.VK_LEFT: leftIsHolding = true; break;
				case KeyEvent.VK_RIGHT: rightIsHolding = true; break;
				case KeyEvent.VK_ESCAPE: System.exit(1);
				}
				setMotion(object);
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		for(GameObject object : this.handler.getAllObjects()){
			
			if(object instanceof Player){
				switch(key){
				case KeyEvent.VK_UP: upIsHolding = false; break;
				case KeyEvent.VK_DOWN: downIsHolding = false; break;
				case KeyEvent.VK_LEFT: leftIsHolding = false; break;
				case KeyEvent.VK_RIGHT: rightIsHolding = false; break;
				case KeyEvent.VK_SPACE:
					handler.addObject(new Bullet(object.getX()+8, object.getY()-10, GameObjectID.Bullet, handler));
				}
				setMotion(object);
			}
		}
	}
	
	//Depends on the button pressing state, decide the motion of player
	private void setMotion(GameObject player){
		if(!(upIsHolding ^ downIsHolding)){
			player.setVeloY(0);
		}else if(upIsHolding){
			player.setVeloY(-movingSpeed);
		}else if(downIsHolding){
			player.setVeloY(movingSpeed);
		}

		if(!(leftIsHolding ^ rightIsHolding)){
			player.setVeloX(0);
		}else if(leftIsHolding){
			player.setVeloX(-movingSpeed);
		}else if(rightIsHolding){
			player.setVeloX(movingSpeed);
		}
	}
}
