package project_16x16.objects;

import java.util.ArrayList;

import processing.core.PVector;
import project_16x16.components.AnimationComponent;
import processing.core.PImage;
import processing.data.JSONObject;
import project_16x16.scene.GameplayScene;
import project_16x16.SideScroller;
import project_16x16.Tileset;

/**
 * Extends {@link EditableObject}.
 */
public class GameObject extends EditableObject {

	// Animation Component
	public AnimationComponent animation;

	// Collision Component
	public CollidableObject collision;

	public PImage image;

	public GameObject(SideScroller a, GameplayScene g) {
		super(a, g);

		animation = new AnimationComponent();
	}

	public GameObject(SideScroller a, GameplayScene g, int w, int h, int x, int y) {
		this(a, g);

		// Get From Game Graphics Image
		pos = new PVector(x, y);
		width = w;
		height = h;
	}
	public GameObject(SideScroller a, GameplayScene g, String id, int x, int y) {
		this(a, g);

		pos = new PVector(x, y);
		setGraphic(id);
	}

	public void setGraphic(String name) {
		image = Tileset.getTile(name);
		id = name;
		width = image.width;
		height = image.height;
	}

	@Override
	public void display() {
		float pixelOffsetX = 0;
		float pixelOffsetY = 0;

		if (height / 4 % 2 != 0) {
			pixelOffsetY = 2;
		}
		if (width / 4 % 2 != 0) {
			pixelOffsetX = 2;
		}

		applet.image(image, pos.x + pixelOffsetX, pos.y + pixelOffsetY, width, height);

	}


	public void update() {
	}

	public void delete() {
	}
	
	/**
	 * 
	 * @param name the name of a tile
	 * @return the animation of a given tile
	 */
	protected ArrayList<PImage> getAnimation(String name) {
		return Tileset.getAnimation(name);
	}

	/**
	 * Returns a specific tile given its coordinates and dimensions
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @param w width
	 * @param h height
	 *
	 * @return a tile given its coordinates and dimensions
	 **/
	protected PImage g(int x, int y, int w, int h) {
		return Tileset.getTile(x, y, w, h);
	}

	protected PImage g(int x, int y, int w, int h, float s) {
		return Tileset.getTile(x, y, w, h);
	}

	@Override
	public void debug() {
		applet.stroke(255, 190, 200);
		applet.noFill();
		applet.rect(pos.x, pos.y, width, height);
	}

	@Override
	public JSONObject exportToJSON() {
		JSONObject item = new JSONObject();
		item.setString("id", id);
		item.setString("type", "OBJECT");
		item.setInt("x", (int) pos.x);
		item.setInt("y", (int) pos.y);
		return item;
	}
}
