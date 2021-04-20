package project_16x16.scene;

import processing.core.PConstants;
import processing.event.KeyEvent;
import project_16x16.Options;
import project_16x16.SideScroller;
import project_16x16.ui.Button;
import project_16x16.ui.Notifications;
import project_16x16.SideScroller;

/**
 *
 * @author micycle1
 *
 */


public final class GraphicSettings extends PScene {
    private final int WINDOW_X_POS = (int) applet.gameResolution.x / 2;
    private final int WINDOW_Y_POS = (int) applet.gameResolution.y / 2;




    private SideScroller game;


    private Button quit;
    private Button apply;
    private Button Default;
    private Button FullScreen;

    public GraphicSettings(SideScroller a) {

        super(a);

        game = a;

        apply = new Button(applet);
        apply.setText("Apply");
        apply.setPosition(a.width / 2, 500);

        quit = new Button(a);
        quit.setText("Quit");
        quit.setPosition(a.width / 2, 600);

        Default = new Button(a);
        Default.setText("Default");
        Default.setPosition(a.width / 3, 250);

        FullScreen = new Button(a);
        FullScreen.setText("FullScreen");
        FullScreen.setPosition(a.width /3 +425, 250);

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
        Default.display();
        FullScreen.display();

        applet.fill(255);
        applet.textSize(60);
        applet.textAlign(CENTER, TOP);
        applet.text("Volume", applet.gameResolution.x / 2, 20);




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
        Default.update();
        FullScreen.update();

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

        if(Default.hover()){
            //Options.fullscreen = java.awt.event.KeyEvent.VK_F11;
            OptionText(("1"), -WINDOW_X_POS/2 +325, -WINDOW_Y_POS /2+ 75);
            return;
        }
        if(FullScreen.hover()){
            OptionText(("-1"), -WINDOW_X_POS/2 +325, -WINDOW_Y_POS /2+ 75);
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
