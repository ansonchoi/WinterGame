import java.applet.Applet;
import java.applet.AudioClip;

public class SoundMaker {

	private AudioClip menuBgMusic = Applet.newAudioClip(Main.class.getResource("rec/menuBg.wav"));
	private AudioClip gameBgMusic = Applet.newAudioClip(Main.class.getResource("rec/gameBg.wav"));

	private boolean isFirstTimeLooping;

	public SoundMaker() {
		isFirstTimeLooping = true;
	}

	// Play the background music according to current gamestate
	public void playBGM(GameState state) {
		if (state == GameState.Menu && isFirstTimeLooping) {
			menuBgMusic.loop();
			isFirstTimeLooping = false;
		} else if (state == GameState.Game && isFirstTimeLooping) {
			gameBgMusic.loop();
			isFirstTimeLooping = false;
		}
	}

	// Stop the background music
	public void resetBGM(GameState state) {
		if (!isFirstTimeLooping) {
			isFirstTimeLooping = true;
			if (state == GameState.Game)
				menuBgMusic.stop();
			else
				gameBgMusic.stop();
		}
	}

}
