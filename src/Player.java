import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Player extends GameObject {
	public static final int width = 64, height = 64;
	
	private GameObjectHandler handler;
	private boolean fireBullet = false;
	private int fireTimeGap;

	
	public Player(int x, int y, GameObjectID id, GameObjectHandler handler) {
		super(x, y, id);
		this.handler = handler;
		health = 100;
		BufferedImageLoader loader  = new BufferedImageLoader();
		try{
		this.objectImg = loader.loadImage("spriteSheet.png");
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		
	}

	@Override
	public void updateLogic() {
		x += veloX;
		y += veloY;
		
		//WIDTH, HEIGTH - object size - number (which is covered by the window board)
		x = Main.constrain(x, 0, Main.WIDTH - width);
		y = Main.constrain(y, 0 + HUD.height, Main.HEIGHT - HUD.height- height);
		
		//Here set the frequent of firing bullet by change the value of below
		//The higher number less frequent, verse vice
		fireTimeGap %= 10;
		
		//if space button is holding, create bullet at the mid of the player constantly
		if(fireBullet && fireTimeGap++ == 0){
			handler.addObject(new Bullet(x + width / 2 - Bullet.width / 2, y, GameObjectID.Bullet, handler));
		}
		
		collisionAnalyse();
	}

	//checking the collision of the Player with opponent
	private void collisionAnalyse() {
		GameObject temp;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			temp = handler.getAllObjects().get(i);
			if(!(temp instanceof Player) && !(temp instanceof Earth)){
				if(this.getBounds().intersects(temp.getBounds())){
					this.changeHealth(temp);
					handler.removeObject(temp);
				}
			}
		}
	}

	@Override
	public void updateGraphic(Graphics g) {
		//This is an example to make a representation to the object player
		if(this.objectImg!=null){
		g.drawImage(this.objectImg.getSubimage(1,1,width,height),x,y,null);

		}else{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		System.out.println("null player image");
		}
		

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void setFire(boolean fire){
		this.fireBullet = fire;
	}
	
}