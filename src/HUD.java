
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class HUD {

	public static int HEALTH = 100;
	private int colorValue = 255;
	public final static int height = 40;
	private int HPBarWidth = 200;
	private long score;
	private GameObjectHandler handler;
	private LinkedList<Long> topScores;

	public HUD(GameObjectHandler handler){
		this.handler = handler;
		//top 3 scores initialize
		topScores = new LinkedList<Long>();
		topScores.add((long) 0);
		topScores.add((long) 0);
		topScores.add((long) 0);
	}

	public void updateHUDLogic(){
		GameObject player;
		for(int i = 0; i < handler.getAllObjects().size(); i++){
			if(handler.getAllObjects().get(i) instanceof Player){
				player = handler.getAllObjects().get(i);
				HEALTH = player.getHealth();
			}
		}
		HEALTH = Main.constrain(HEALTH, 0, 100);

		colorValue = Main.constrain(colorValue, 0, 255);

		colorValue = HEALTH * 2;
		if(HEALTH!=0 && !Earth.dead){
			score++;
		}
	}

	public void updateHUDGraphic(Graphics g){
		// Health
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.HPBarWidth, HUD.height);
		g.setColor(new Color(75, colorValue,0));
		g.fillRect(0, 0, HUD.HEALTH * 2, HUD.height);
		g.setColor(Color.white);
		g.drawRect(0, 0, this.HPBarWidth, HUD.height);

		// Score
		int scoreBarWidth = this.HPBarWidth*3/4;
		g.setColor(Color.gray);
		g.fillRect(this.HPBarWidth, 0, scoreBarWidth , HUD.height);
		g.setColor(Color.white);
		g.drawRect(this.HPBarWidth, 0, scoreBarWidth, HUD.height);
		g.drawString("Score: " + score, this.HPBarWidth+5, HUD.height/2+5);

		// Button
		int button1_xpos = this.HPBarWidth + scoreBarWidth;
		int button_width = (Main.WIDTH-button1_xpos)/2;
		g.setColor(Color.gray);
		g.fillRect(button1_xpos, 0, button_width, HUD.height);
		g.setColor(Color.red);
		g.drawString("Pause", button1_xpos+8, 25);
		g.setColor(Color.white);
		g.drawRect(button1_xpos, 0, button_width, HUD.height);
		// Button 2
		int button2_xpos = this.HPBarWidth + scoreBarWidth + button_width;
		g.setColor(Color.gray);
		g.fillRect(button2_xpos, 0, button_width, HUD.height);
		g.setColor(Color.red);
		g.drawString("Exit", button2_xpos+12, 25);
		g.setColor(Color.white);
		g.drawRect(button2_xpos, 0, button_width, HUD.height);
	}

	public long getEndingScore(){
		return this.score;
	}
	
	//update the top 3 scores and reset game score to zero
	public void resetScore(){
		topScores.push(score);
		
		//Sort scores from larger to smaller
		Collections.sort(topScores, new Comparator<Long>(){
			   @Override
			   public int compare(Long o1, Long o2){
			        if(o1 < o2){
			           return 1; 
			        }
			        if(o1 > o2){
			           return -1; 
			        }
			        return 0;
			   }
		});
		
		//keeping the topScores only have 3 elements, save memory space
		topScores.removeLast();
		
		for(int i = 0; i < topScores.size(); i++){
			System.out.print(topScores.get(i) + " ");
		}
		
		this.score = 0;
	}
	
	public LinkedList<Long> getTopScores(){
		return topScores;
	}
}
