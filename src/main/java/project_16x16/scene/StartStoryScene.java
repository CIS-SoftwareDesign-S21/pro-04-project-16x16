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
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.entities.Player;
import project_16x16.objects.BackgroundObject;
import project_16x16.objects.CollidableObject;
import project_16x16.objects.EditableObject;
import project_16x16.objects.GameObject;
import project_16x16.projectiles.ProjectileObject;
import project_16x16.scene.gameplaymodes.GameplayMode;

public class StartStoryScene extends PScene{
    SideScroller sidescroller;
    int numLoadingFrames = 145;  // The number of frames in the loading gif file
    int currentLoadingFrame = 0;
    PImage[] loadingImages = new PImage[numLoadingFrames];
    private final String level1String;
    private GameplayScene playscene;
    private boolean isSingleplayer = true; // true by default
    int counter = 0;

    public StartStoryScene(SideScroller a, String level) {
        super(a);
        this.sidescroller=a;
        for(int i = 0; i < numLoadingFrames; i++) {
            //This is used to load the images using regex instead of mannually writing
            //the file postfix from 001 to 145
            String imageName = "Art/loading/frame_" + this.sidescroller.nf(i, 3) + ".gif";
            loadingImages[i] = loadImage(imageName);
        }
        level1String=level;
        playscene = new GameplayScene(this.sidescroller, level1String);
        playscene.changeMode(GameplayScene.GameModes.PLAY);
    }

    @Override
    public void draw() {
        //This is a very plain setup to connect the loading animation to the start of the game
        //Change to setup later
        if(counter <120 ){
            background(0);
            currentLoadingFrame = (currentLoadingFrame+1)%numLoadingFrames;
            image(loadingImages[(currentLoadingFrame) % numLoadingFrames], 5, 15);
            counter++;
        }
        if(counter >= 120) {
            this.playscene.draw();
        }
    }



}











