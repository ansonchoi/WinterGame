import java.util.Random;

//Enemy generator
public class Spawner {
	private GameObjectHandler handler;
	private int timer;
	private int level;

	public Spawner(GameObjectHandler handler) {
		this.handler = handler;
		level = 1;
	}

	// Initialize the Player object and set timer = 0;
	public void init() {
		timer = 0;
		handler.addObject(new Player((Main.WIDTH - Player.width) / 2, Main.HEIGHT - Player.height - 50,
				GameObjectID.Player, handler));
		handler.addObject(new Earth(0, Main.HEIGHT - HUD.height, GameObjectID.Earth, handler));
	}

	//reset the timer and level of them game
	public void reset() {
		timer = 0;
		level = 1;
	}
	
	//spawn enemy randomly, longer you play, more enemy spawned
	public void spawn() {
		Random r = new Random();
		int spawn = ++timer;

		if (timer == 1000) {
			level++;
			timer = 0;
		}

		if (spawn % 200 == 0) {
			for (int i = 0; i < r.nextInt(level + 1); i++) {
				handler.addObject(new EnemyDummy(r.nextInt(Main.WIDTH - EnemyDummy.width), 0, GameObjectID.Enemy));
			}

			for (int i = 0; i < r.nextInt(level + 1); i++) {
				handler.addObject(new EnemyTricky(r.nextInt(Main.WIDTH - EnemyTricky.width), 0, GameObjectID.Enemy));
			}

			for (int i = 0; i < r.nextInt(level + 1); i++) {
				handler.addObject(new EnemyNormal(r.nextInt(Main.WIDTH - EnemyNormal.width), 0, GameObjectID.Enemy));
			}

		}

	}

}
