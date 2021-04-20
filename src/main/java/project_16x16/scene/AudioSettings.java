package project_16x16.scene;

import ddf.minim.AudioPlayer;
import javafx.beans.binding.MapExpression;
import javafx.scene.control.LabeledBuilder;
import processing.core.PConstants;
import processing.event.KeyEvent;
import project_16x16.Options;
import project_16x16.SideScroller;
import project_16x16.ui.Button;
import project_16x16.ui.Notifications;
import project_16x16.Audio.BGM;
import project_16x16.Audio;
import processing.core.PConstants;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import project_16x16.SideScroller;
import project_16x16.SideScroller.GameScenes;
import project_16x16.ui.Button;
import project_16x16.ui.Notifications;
import project_16x16.ui.NumberInputField;

import java.util.HashMap;

/**
 *
 * @author micycle1
 *
 */


public final class AudioSettings extends PScene {
	private final int WINDOW_X_POS = (int) applet.gameResolution.x / 2;
	private final int WINDOW_Y_POS = (int) applet.gameResolution.y / 2;




	private SideScroller game;


	private Button quit;
	private Button apply;
	private Button Increase;
	private Button Decrease;

	private Button Increase1;
	private Button Decrease1;

	public AudioSettings(SideScroller a) {

		super(a);

		game = a;

		apply = new Button(applet);
		apply.setText("Apply");
		apply.setPosition(a.width / 2, 550);

		quit = new Button(a);
		quit.setText("Quit");
		quit.setPosition(a.width / 2, 600);

		Increase = new Button(a);
		Increase.setText("Increase");
		Increase.setPosition(a.width /3, 250);

		Decrease = new Button(a);
		Decrease.setText("Decrease");
		Decrease.setPosition(a.width /3 +225, 250);

		Increase1 = new Button(a);
		Increase1.setText("Increase");
		Increase1.setPosition(a.width /3, 375);

		Decrease1 = new Button(a);
		Decrease1.setText("Decrease");
		Decrease1.setPosition(a.width /3 +225, 375);

	}

	@Override
	public void switchTo() {
		super.switchTo();
		// TODO
	}

	@Override
	public void drawUI() {
		game.background(23, 26, 36);
		apply.display();
		quit.display();
		Increase.display();
		Decrease.display();

		Increase1.display();
		Decrease1.display();

		applet.fill(255);
		applet.textSize(60);
		applet.textAlign(CENTER, TOP);
		applet.text("Volume", applet.gameResolution.x / 2, 20);

		OptionText(dynamicPadding("Game Volume :", 20), -WINDOW_X_POS / 2 -110, -WINDOW_Y_POS / 2 +72 );
		OptionText(dynamicPadding("5", 20), -WINDOW_X_POS /2 +158, -WINDOW_Y_POS / 2 +72 );

		OptionText(dynamicPadding("Special Effect :", 20), -WINDOW_X_POS / 2 -120, -WINDOW_Y_POS / 2 +197 );
		OptionText(dynamicPadding("5", 20), -WINDOW_X_POS / 2 +158 , -WINDOW_Y_POS / 2 +197 );


	}
	private void OptionText(String toDisplay, int x, int y) { // Display the text at the bottom
		applet.fill(255);
		applet.textSize(30);
		applet.textAlign(CENTER, CENTER);
		applet.text(toDisplay, WINDOW_X_POS + x, WINDOW_Y_POS + y);
	}

	private String dynamicPadding(String textToPad, int charLimit) {
		int paddingAmount = Math.abs(textToPad.length() - charLimit);
		String textPad = textToPad;
		for (int i = 0; i < paddingAmount; i++) {
			textPad = " " + textPad;
		}
		return textPad;
	}

	@Override
	void mouseReleased(processing.event.MouseEvent e) {
		apply.update();
		quit.update();
		Increase.update();
		Decrease.update();

		if (quit.hover()) {
			game.returnScene();
			return;
		}
		if (apply.hover()) {
			// TODO apply settings
			Notifications.addNotification("Sound Settings Applied", "Your configuration has been successfully applied.");
			game.returnScene();
			return;
		}

		if(Increase.hover()){
			Audio.setGainBGM(+10);
			OptionText(("5"), -WINDOW_X_POS/2 +200, -WINDOW_Y_POS /2+ 75);
			return;
		}
		if(Decrease.hover()){
			Audio.setGainBGM(-10);
			//OptionText(("-1"), -WINDOW_X_POS/2 +325, -WINDOW_Y_POS /2+ 75);
			return;
		}

		if(Increase1.hover()){
			Audio.setGainSFX(+10);
			OptionText(("5"), -WINDOW_X_POS/2 +325, -WINDOW_Y_POS /2+ 75);
			return;
		}
		if(Decrease1.hover()){
			Audio.setGainSFX(-10);
			//OptionText(("-1"), -WINDOW_X_POS/2 +325, -WINDOW_Y_POS /2+ 75);
			return;
		}
	}


	@Override
	void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case PConstants.ESC : // Pause
				game.returnScene();
				break;
			default :
				break;
		}
	}

}
