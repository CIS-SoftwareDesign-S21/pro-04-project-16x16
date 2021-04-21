package project_16x16.scene;

import processing.core.PConstants;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import project_16x16.SideScroller;
import project_16x16.SideScroller.GameScenes;
import project_16x16.ui.Button;
import project_16x16.ui.Notifications;
import project_16x16.ui.NumberInputField;
import project_16x16.ui.TextInputField;

/**
 * WIP - the settings menu
 *
 * @author micycle1
 *
 */
public class ConfigureControls extends PScene {
    // Window
    private final int WINDOW_X_POS = (int) applet.gameResolution.x / 2;
    private final int WINDOW_Y_POS = (int) applet.gameResolution.y / 2;
    // Buttons
    private final int GRAPHICS_Y_OFFSET =  10;
    private final int SOUND_Y_OFFSET =  50;
    private final int CONTROL_Y_OFFSET =  90;
    private final int WINDOWSIZE_Y_OFFSET = 130;
    private final int QUIT_Y_OFFSET = 320;
    private final int APPLY_Y_OFFSET = 280;
    // Text Display

    private Button quit;
    private Button apply;
    private Button pressGraphicsOptions;
    private Button pressSoundOptions;
    private Button pressControlsOptions;
    private Button pressMiscOptions;

    private TextInputField windowSizeX;
    private TextInputField windowSizeX1;
    private TextInputField windowSizeX2;
    private TextInputField windowSizeX3;
    private TextInputField windowSizeX4;
    private TextInputField windowSizeX5;
    private TextInputField windowSizeX6;
    private TextInputField windowSizeX7;
    private TextInputField windowSizeX8;



    private SideScroller game;

    public ConfigureControls(SideScroller a) {
        super(a);
        game = a;

        quit = new Button(a);
        quit.setText("Quit");
        quit.setPosition(WINDOW_X_POS, WINDOW_Y_POS + QUIT_Y_OFFSET);

        apply = new Button(applet);
        apply.setText("Apply");
        apply.setPosition(WINDOW_X_POS, WINDOW_Y_POS + APPLY_Y_OFFSET);

        pressGraphicsOptions = new Button(applet);
        pressGraphicsOptions.setText("Configure Graphics");
        pressGraphicsOptions.setPosition(WINDOW_X_POS , WINDOW_Y_POS + GRAPHICS_Y_OFFSET);

        pressSoundOptions = new Button(applet);
        pressSoundOptions.setText("Configure Audio and Volume");
        pressSoundOptions.setPosition(WINDOW_X_POS , WINDOW_Y_POS + SOUND_Y_OFFSET);

        pressControlsOptions = new Button(applet);
        pressControlsOptions.setText("Configure Controls");
        pressControlsOptions.setPosition(WINDOW_X_POS , WINDOW_Y_POS + CONTROL_Y_OFFSET);

        windowSizeX = new TextInputField(a);
        windowSizeX.setPosition(WINDOW_X_POS , WINDOW_Y_POS -230 );

        windowSizeX1 = new TextInputField(a);
        windowSizeX1.setPosition(WINDOW_X_POS , WINDOW_Y_POS -180 );

        windowSizeX2 = new TextInputField(a);
        windowSizeX2.setPosition(WINDOW_X_POS , WINDOW_Y_POS -130 );

        windowSizeX3 = new TextInputField(a);
        windowSizeX3.setPosition(WINDOW_X_POS , WINDOW_Y_POS -80 );

        windowSizeX4 = new TextInputField(a);
        windowSizeX4.setPosition(WINDOW_X_POS , WINDOW_Y_POS -30 );

        windowSizeX5 = new TextInputField(a);
        windowSizeX5.setPosition(WINDOW_X_POS , WINDOW_Y_POS +20 );

        windowSizeX6 = new TextInputField(a);
        windowSizeX6.setPosition(WINDOW_X_POS , WINDOW_Y_POS +70 );

        windowSizeX7 = new TextInputField(a);
        windowSizeX7.setPosition(WINDOW_X_POS , WINDOW_Y_POS +120 );


    }

    @Override
    public void drawUI() {
        displayWindow();
//		// Display Window Title
        applet.fill(255);
        applet.textSize(60);
        applet.textAlign(CENTER, TOP);
        applet.text("Configure Controls", applet.gameResolution.x / 2, 20);

        quit.display();
        apply.display();


        OptionText(dynamicPadding("Move Upward -", 22), -WINDOW_X_POS / 2 + 56, -WINDOW_Y_POS / 2 -50 );
        OptionText(dynamicPadding("Move Downward -", 20), -WINDOW_X_POS / 2 + 51, -WINDOW_Y_POS / 2);
        OptionText(dynamicPadding("Move Left -", 20), -WINDOW_X_POS / 2 + 75, -WINDOW_Y_POS / 2 +50 );
        OptionText(dynamicPadding("Move Right -", 20), -WINDOW_X_POS / 2 + 75, -WINDOW_Y_POS / 2 +100 );
        OptionText(dynamicPadding("Attack -", 20), -WINDOW_X_POS / 2 + 86, -WINDOW_Y_POS / 2 +150 );
        OptionText(dynamicPadding("Jump -", 20), -WINDOW_X_POS / 2 + 100, -WINDOW_Y_POS / 2 +200 );
        OptionText(dynamicPadding("Camera To Mouse -", 20), -WINDOW_X_POS / 2 + 46, -WINDOW_Y_POS / 2 +250 );
        OptionText(dynamicPadding("Camera To Player -", 20), -WINDOW_X_POS / 2 + 40, -WINDOW_Y_POS / 2 +300 );



        windowSizeX.update();
        windowSizeX.display();

        windowSizeX1.update();
        windowSizeX1.display();

        windowSizeX2.update();
        windowSizeX2.display();

        windowSizeX3.update();
        windowSizeX3.display();

        windowSizeX4.update();
        windowSizeX4.display();

        windowSizeX5.update();
        windowSizeX5.display();

        windowSizeX6.update();
        windowSizeX6.display();

        windowSizeX7.update();
        windowSizeX7.display();

    }

    @Override
    void mouseReleased(MouseEvent e) {
        update();
    }

    private void update() {
        quit.update();
        apply.update();
        pressSoundOptions.update();
        if (quit.hover()) {
            game.returnScene();
            return;
        }
        if (apply.hover()) {
            //game.resizeWindow(windowSizeX.getValue(), 720); // TODO change
            Notifications.addNotification("Options Applied", "Your configuration has been successfully applied.");
            game.returnScene();
            return;
        }
        if (pressSoundOptions.hover()) {
            game.swapToScene(GameScenes.AUDIO_SETTINGS);

        }
        if (pressGraphicsOptions.hover()) {
//            game.swapToScene(GameScenes.GRAPHIC_SETTINGS);
        }
        if (pressControlsOptions.hover()) {
//            game.swapToScene(GameScenes.CONFIGURE_CONTROLS);
        }
    }

    private void displayWindow() {
        background(19, 23, 35);
        // Display Window
        applet.fill(29, 33, 45);
        applet.stroke(47, 54, 73);
        applet.strokeWeight(8);
        applet.rect(WINDOW_X_POS, WINDOW_Y_POS, applet.gameResolution.x * 0.66f - 8, applet.gameResolution.y - 8);
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
