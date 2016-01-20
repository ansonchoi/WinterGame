import java.util.Random;

public class Spawner {
	private GameObjectHandler handler;
	private int timer;
	
	public Spawner(GameObjectHandler handler) {
		this.handler = handler;
	}
	
	//Initialize the Player object and set timer = 0;
	public void init() {
		timer = 0;
		handler.addObject(new Player(100, 100, GameObjectID.Player, handler));
	}
	

	public void spawn() {
		int spawn = ++timer;
		int normalCreep = 1;
		
		if(spawn % 200 == 0){
			Random r = new Random();
			handler.addObject(new Enemy(r.nextInt(Main.WIDTH - 30), 0, GameObjectID.Enemy));
		}
		
		
	}
	

}
