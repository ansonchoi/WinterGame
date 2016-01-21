
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	
	private GameObjectHandler handler;
	private int movingSpeed;

	private boolean upIsHolding;
	private boolean downIsHolding;
	private boolean leftIsHolding;
	private boolean rightIsHolding;
	private boolean spaceIsHolding;
	
	
	public Controller(GameObjectHandler handler){
		this.handler = handler;
		movingSpeed = 5;
	}
	
	public void resetMotions(){
		upIsHolding = false;
		downIsHolding = false;
		leftIsHolding = false;
		rightIsHolding = false;
		spaceIsHolding = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE)	System.exit(1);
		
		GameObject object;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			object = handler.getAllObjects().get(i);
			if(object instanceof Player){
				switch(key){
				case KeyEvent.VK_W: upIsHolding = true; break;
				case KeyEvent.VK_S: downIsHolding = true; break;
				case KeyEvent.VK_A: leftIsHolding = true; break;
				case KeyEvent.VK_D: rightIsHolding = true; break;
				case KeyEvent.VK_J: spaceIsHolding = true; break;
				}
				setMotion((Player)object);
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		GameObject object;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			object = handler.getAllObjects().get(i);
			if(object instanceof Player){
				switch(key){
				case KeyEvent.VK_W: upIsHolding = false; break;
				case KeyEvent.VK_S: downIsHolding = false; break;
				case KeyEvent.VK_A: leftIsHolding = false; break;
				case KeyEvent.VK_D: rightIsHolding = false; break;
				case KeyEvent.VK_J: spaceIsHolding = false; break;
				}
				setMotion((Player)object);
			}
		}
	}
	
	//Depends on the button pressing state, decide the motion of player
	private void setMotion(Player player){

		if(spaceIsHolding){
			player.setFire(true);
		}else{
			player.setFire(false);
		}
		
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
