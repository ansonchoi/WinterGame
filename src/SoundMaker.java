import java.applet.Applet;
import java.applet.AudioClip;

public class SoundMaker{

	private AudioClip menuBgMusic = Applet.newAudioClip(Main.class.getResource("menuBg.wav"));
	private AudioClip gameBgMusic = Applet.newAudioClip(Main.class.getResource("gameBg.wav"));

	private boolean isFirstTimeLooping;

	public SoundMaker(){
		isFirstTimeLooping = true;
	}

	public void playBGM(GameState state) {
		if(state == GameState.Menu && isFirstTimeLooping){
			menuBgMusic.loop();
			isFirstTimeLooping = false;
		}else if(state == GameState.Game && isFirstTimeLooping){
			gameBgMusic.loop();
			isFirstTimeLooping = false;
		}
	}

	public void resetBGM(GameState state){
		if(!isFirstTimeLooping){
			isFirstTimeLooping = true;
			if(state == GameState.Game)
				menuBgMusic.stop();
			else
				gameBgMusic.stop();
		}
	}


}
