package project_16x16.scene;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import processing.core.*;
import processing.data.JSONArray;
import processing.data.JSONObject;
import project_16x16.*;
import project_16x16.entities.Player;
import project_16x16.objects.BackgroundObject;
import project_16x16.objects.CollidableObject;
import project_16x16.objects.EditableObject;
import project_16x16.objects.GameObject;
import project_16x16.projectiles.ProjectileObject;
import project_16x16.scene.gameplaymodes.GameplayMode;
import project_16x16.SideScroller.GameScenes;
import project_16x16.ui.CountdownClock;

public class StartStoryScene extends PScene{
    private int numLoadingFrames = 145;  // The number of frames in the loading gif file
    private int currentLoadingFrame = 0;
    private int numEndingFrames=3;
    private int numLosingFrames=37;
    private int currentEndingFrame=0;
    private int currentLosingFrame=0;
    private PImage[] loadingImages = new PImage[numLoadingFrames];
    private PImage[] endingImages = new PImage[numEndingFrames];
    private PImage[] losingImages = new PImage[numLosingFrames];
    private String currentlevel;
    private GameplayScene playscene;
    private boolean isSingleplayer = true; // true by default
    private int counter = 0;
    private int endCounter = 120;
    private int losingCounter = 37;

    public StartStoryScene(SideScroller a, String level) {
        super(a);
        for(int i = 0; i < numLoadingFrames; i++) {
            /**This is used to load the images using regex instead of mannually writing
             * the file postfix from 001 to 145
             */
            String imageName = "Art/loading/frame_" + a.nf(i, 3) + ".gif";
            loadingImages[i] = loadImage(imageName);
        }
        for(int j =0; j<numEndingFrames; j++) {
            String imageName = "Art/ending/frame_" + a.nf(j, 3) + ".jpg";
            endingImages[j] = loadImage(imageName);
        }
        for(int t = 0; t<numLosingFrames; t++) {
            String imageName = "Art/losing/frame_" + a.nf(t, 3) + ".jpg";
            losingImages[t] = loadImage(imageName);
        }
        a.clock = new CountdownClock(0);
        currentlevel=level;
        playscene = new GameplayScene(a, currentlevel);
        a.getGame().getPlayer().pos=this.playscene.getPlayer().pos;
        playscene.changeMode(GameplayScene.GameModes.PLAY);
    }
    @Override
    public void draw() {
        /**
         * This is a very plain setup to connect the loading animation to the start of the game.
         * The loading gif contains 145 static images so we set the counter variable to 145,
         * when it finishes with all the iteration of images, it starts the new level map.
         * Improvements can be done later
         */
        if(counter <145 ){
            background(0);
            currentLoadingFrame = (currentLoadingFrame+1)%numLoadingFrames;
            image(loadingImages[(currentLoadingFrame) % numLoadingFrames], applet.camera.getPosition().x, applet.camera.getPosition().y);
            counter++;
        }
        if(counter >= 145) {
            applet.camera.setFollowObject(this.playscene.getPlayer());
            this.playscene.draw();
            checkWinningPosition();
            if(this.playscene.getPlayer().life==0) {
                background(0);
                applet.frameRate(10);
                currentLosingFrame = (currentLosingFrame+1)%numLosingFrames;
                image(losingImages[(currentLosingFrame) % numLosingFrames], applet.camera.getPosition().x, applet.camera.getPosition().y);
                this.losingCounter--;
                if(losingCounter==0) {
                    applet.frameRate(60);
                    losingCounter=37;
                    this.playscene.getPlayer().life=3;
                    applet.swapToScene(GameScenes.MAIN_MENU);
                }
            }
            if(endCounter==0) {
                applet.swapToScene(GameScenes.MAIN_MENU);
                this.currentlevel=Constants.LEVEL1;
                playscene = new GameplayScene(applet, currentlevel);
                applet.getGame().getPlayer().pos=this.playscene.getPlayer().pos;
                playscene.changeMode(GameplayScene.GameModes.PLAY);
                endCounter=120;
                this.playscene.getPlayer().life=3;
            }
        }
    }
    @Override
    protected void keyReleased(processing.event.KeyEvent e) {
        switch (e.getKeyCode()) { // Global gameplay hotkeys
            case PConstants.ESC : // Pause
                applet.swapToScene(SideScroller.GameScenes.PAUSE_MENU);
                break;
            case 49 : // 1
                break;
            case 50 : // 2
                break;
            case 51 : // 3
                break;
            case 52 : // 4
                break;
            case 54 : // 6
                break;
            default :
                break;
        }
    }
    public GameplayScene getPlayscene() {
        return this.playscene;
    }
    public void setCounter(int x) {
        this.counter=x;
    }
    public void drawUI() {
        this.playscene.currentMode.updateGUI();
    }
    public void checkWinningPosition() {
        switch(this.currentlevel){
            case Constants.LEVEL1:
                if(this.playscene.getPlayer().pos.x==-612 && this.playscene.getPlayer().pos.y==160) {
                    this.playscene.loadLevel(Constants.LEVEL2);
                    this.playscene.getPlayer().pos.set(100, 20);
                    this.currentlevel=Constants.LEVEL2;
                }
                break;
            case Constants.LEVEL2:
                if(this.playscene.getPlayer().pos.x==3044 && this.playscene.getPlayer().pos.y==544) {
                    this.playscene.loadLevel(Constants.LEVEL3);
                    this.playscene.getPlayer().pos.set(-216, -96);
                    this.currentlevel=Constants.LEVEL3;
                }
                break;
            case Constants.LEVEL3:
                if(this.playscene.getPlayer().pos.x==1348 && this.playscene.getPlayer().pos.y==-288) {
                    background(0);
                    currentEndingFrame = (currentEndingFrame+1)%numEndingFrames;
                    image(endingImages[(currentEndingFrame) % numEndingFrames], applet.camera.getPosition().x, applet.camera.getPosition().y);
                    this.endCounter--;
                }
                break;
            default:
                break;
        }
    }
}

