package project_16x16.scene;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.*;
import project_16x16.SideScroller;
import project_16x16.scene.gameplaymodes.GameplayMode;

public class StartStoryScene extends PScene{
    SideScroller sidescroller;
    int numLoadingFrames = 145;  // The number of frames in the loading gif file
    int currentLoadingFrame = 0;
    PImage[] loadingImages = new PImage[numLoadingFrames];


    public StartStoryScene(SideScroller a) {
        super(a);
        this.sidescroller=a;
        for(int i = 0; i < numLoadingFrames; i++) {
            //This is used to load the images using regex instead of mannually writing
            //the file postfix from 001 to 145
            String imageName = "Art/loading/frame_" + this.sidescroller.nf(i, 3) + ".gif";
            loadingImages[i] = loadImage(imageName);
        }
    }

    @Override
    public void draw() {
        background(0);
        currentLoadingFrame = (currentLoadingFrame+1)%numLoadingFrames;
        image(loadingImages[(currentLoadingFrame) % numLoadingFrames], 20, 20);
    }

}











