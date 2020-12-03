package main.java.entities.tile.destroyable;


import static main.java.Game.getThroughWall;
import static main.java.Game.setThroughWall;

import main.java.entities.Entity;
import main.java.entities.bomb.DirectionalExplosion;
import main.java.entities.mob.Player;
import main.java.entities.mob.enemy.Kondoria;
import main.java.graphics.Screen;
import main.java.graphics.Sprite;
import main.java.level.Coordinates;

public class BrickTile extends DestroyableTile {

    public BrickTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if(_destroyed) {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
        }
        else
            screen.renderEntity( x, y, this);
    }

    @Override
    public boolean collide(Entity e) {

        if(e instanceof DirectionalExplosion)
            destroy();

        if(e instanceof Kondoria)
            return true;

        if(e instanceof Player && getThroughWall() != 0  ) {
            setThroughWall(getThroughWall() - 1);
            return true;
        }

        return false;
    }


}
