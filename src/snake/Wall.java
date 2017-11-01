package snake;

import java.awt.*;
import java.io.Serializable;

public class Wall implements Serializable {

    private Point location;

    public Point getLocation() {
        return location;
    }

    public Wall(int width, int height) {
        location = new Point(width, height);

        //new Wall().equals(new Wall())



    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wall wall = (Wall) o;

        return location != null ? location.equals(wall.location) : wall.location == null;
    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }
}
