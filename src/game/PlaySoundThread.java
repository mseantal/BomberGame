package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Class PlaySoundThread. Der sound von Strum, eigene Klasse
 */
public class PlaySoundThread extends Thread {

	/** The path. */
	private String path;

	/**
	 * Instantiates a new play sound thread.
	 *
	 * @param path
	 *            the path
	 */
	public PlaySoundThread(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	// soundclip.setFrame(0);
	public void run() {
		boolean playing = false;
		Clip clip = null;
		while (true) {
			if (GameSettings.running) {
				try {
					if (!playing || (clip != null && !clip.isRunning())) {
						clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
						clip.open(inputStream);
						clip.start();
						playing = true;
					}
					Thread.sleep(GameSettings.repaintIntervalInMS);
				} catch (InterruptedException e) {
					clip.stop();
					break;
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			} else {
				if (clip != null)
					clip.stop();
				playing = false;
			}
		}
	}
}
