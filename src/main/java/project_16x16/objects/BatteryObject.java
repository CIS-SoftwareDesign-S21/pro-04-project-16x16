package project_16x16.objects;

import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.projectiles.MagicProjectile;
import project_16x16.projectiles.ProjectileObject;
import project_16x16.scene.GameplayScene;

public class BatteryObject extends GameObject{
    ProjectileObject projectile;
    public BatteryObject(SideScroller a, GameplayScene g) {
        super(a, g);

        type = type.OBJECT;
        id = "Battery_Orb:4";
        image = Tileset.getTile("Battery_Orb:4");

        collision = new CollidableObject(applet, g, 114, 114, 0, 0, true);
        collision.flag = "TRANSPARENT_BULLET";
        g.objects.add(collision);

        //((MagicProjectile) projectile).hit(collision);
        System.out.println("**********Print this");
    }


    public void display(){
        float pixelOffsetX = 0;
        float pixelOffsetY = 0;

        if (height / 4 % 2 != 0) {
            pixelOffsetY = 3;
        }
        if (width / 4 % 2 != 0) {
            pixelOffsetX = 3;
        }

        applet.image(image, pos.x + pixelOffsetX, pos.y + pixelOffsetY, width, height);
    }

    public void update(){

    }
}
