package Tests;

import org.junit.Test;
import org.junit.Assert;
import snake.Config;
import snake.GameField;
import snake.Snake;

import java.awt.*;

import static org.junit.Assert.*;

public class GameFieldTest {

    @Test
    public void isDead(){
        Config config = new Config(25, 25, 25, 1000);
        GameField gf = new GameField(config);
        //gf.setTimerStop();
        Point[] arr = new Point[9];
        arr[0] = new Point(0, 0);
        arr[1] = new Point(1, 0);
        arr[2] = new Point(2, 0);
        arr[3] = new Point(2, 1);
        arr[4] = new Point(2, 2);
        arr[5] = new Point(1, 2);
        arr[6] = new Point(0, 2);
        arr[7] = new Point(0, 1);
        arr[8] = new Point(0, 0);
        gf.setSnakeLocations(arr);
        assertTrue(gf.isDead());
    }

    @Test
    public void isNotDead() {
        Config config = new Config(25, 25, 25, 1000);
        GameField field = new GameField(config);
        Point[] arr = new Point[9];
        arr[0] = new Point(0, 0);
        arr[1] = new Point(1, 0);
        arr[2] = new Point(2, 0);
        arr[3] = new Point(2, 1);
        arr[4] = new Point(2, 2);
        arr[5] = new Point(1, 2);
        arr[6] = new Point(0, 2);
        arr[7] = new Point(0, 1);
        arr[8] = new Point(1, 1);
        field.setSnakeLocations(arr);
        assertFalse(field.isDead());
    }

    @Test
    public void outOfTheBounds() {
        Config config = new Config(25, 25, 25, 1000);
        GameField field = new GameField(config);
        Point[] arr = new Point[3];
        arr[0] = new Point(-1, 0);
        arr[1] = new Point(0, 0);
        arr[2] = new Point(1, 0);
        field.setSnakeLocations(arr);
        assertTrue(field.isDead());
    }
}