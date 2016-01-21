import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	
	private Main main;
	private Spawner spawner;
	private HUD hud;
	private SoundMaker sound;
	
	public Menu(Main main, Spawner spawner, HUD hud, SoundMaker sound){
		this.main = main;
		this.spawner = spawner;
		this.hud = hud;
		this.sound = sound;
	}
	
	//do action when clicked certain field on the board
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(main.state == GameState.Menu && Main.isMouseOver(mx, my, 125, 150, 200, 70)){ // Start
			main.state = GameState.Game;
			sound.resetBGM(main.state);
			spawner.init();
		}else if(main.state == GameState.Menu && Main.isMouseOver(mx, my, 125, 280, 200, 70)){ // Score
			main.state = GameState.Score;
		}else if(main.state == GameState.Menu && Main.isMouseOver(mx, my, 125, 410, 200, 70)){ // About
			main.state = GameState.About;
		}else if((main.state == GameState.Score 
				|| main.state == GameState.About) && Main.isMouseOver(mx, my, 125, 410, 200, 70)){ //Back button
			main.state = GameState.Menu;
		}else if(main.state == GameState.Gameover && Main.isMouseOver(mx, my, 90, 400, 300, 70)){
			main.state = GameState.Menu;
			sound.resetBGM(main.state);
			hud.resetScore();
		}
		
	}
	
	public void updateMenuGraphic(Graphics g) {
		g.setFont(new Font("arial", 1, 50));
		g.setColor(Color.white);
		
		if(main.state == GameState.Menu){
			g.drawRect(125, 150, 200, 70);
			g.drawString("Play", 175, 200);
			
			//Second Button, Score
			g.drawRect(125, 280, 200, 70);
			g.drawString("Score", 160, 335);
			
			//Third Button, About
			g.drawRect(125, 410, 200, 70);
			g.drawString("About", 155, 460);
			
		}else if(main.state == GameState.Score){	//Display Menu-Score content
			g.drawString("Top 3 Scores", 70, 80);
			
			//Show Score
			g.drawString("1: " + hud.getTopScores().get(0), 120, 150);
			g.drawString("2: " + hud.getTopScores().get(1), 120, 250);
			g.drawString("3: " + hud.getTopScores().get(2), 120, 350);
			
			//Back button
			g.drawRect(125, 410, 200, 70);
			g.drawString("Back", 165, 460);
		}else if(main.state == GameState.About){	//Display Menu-About content
			//Team info
			g.drawString("Our team", 115, 80);
			g.setFont(new Font("arial", 1, 20));
			g.drawString("CAI, Wenhao            CHENG, Ho Yin", 50, 150);
			g.drawString("CHOI, Wai Hong       TAI, Chun Ho", 50, 200);
			
			//UserGuide
			g.setFont(new Font("arial", 1, 50));
			g.drawString("User Guide", 100, 300);
			g.setFont(new Font("arial", 1, 20));
			g.drawString("Press \"W / S / A / D\" to control", 90, 360);
			g.drawString("Hold \"J\" to fire bullet", 130, 390);
			
			//Back button
			g.setFont(new Font("arial", 1, 50));
			g.drawRect(125, 410, 200, 70);
			g.drawString("Back", 165, 460);
		}else if(main.state == GameState.Gameover){
			g.drawString("Game Over", 95, 80);
			g.drawString("Your Score: ", 20, 250);
			g.drawString(Long.toString(hud.getEndingScore()), 20, 350);
			// Play again button
			g.drawRect(90, 400, 300, 70);
			g.drawString("Play again", 120, 455);
		}
	}
	
	
}
