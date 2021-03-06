package logic;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Level implements Serializable {

  @Getter
  private int width;
  @Getter
  private int height;
  private Random rnd = new Random();
  @Getter
  private String levelName;
  @Getter
  @Setter
  private Set<Wall> mazeLocations;
  @Getter
  @Setter
  private Set<Entrance> entrances;
  @Getter
  @Setter
  Food food;

  public Level(Config config, String level) {
    levelName = level;
    mazeLocations = new HashSet<>();
    entrances = new HashSet<>();
    width = config.getFieldWidth();
    height = config.getFieldHeight();
    generateFood();
  }

  public void generateFood() {
    food = new Food(findFreeSpot());
  }

  public Set<Wall> createRandomField() {
    Set<Wall> maze = new HashSet();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (new Random().nextInt(50) == 0) {
          maze.add(new Wall(x, y));
        }
      }
    }
    return maze;
  }

  public Point findFreeSpot() {
    while (true) {
      int x = rnd.nextInt(width - 1);
      int y = rnd.nextInt(height - 1);
      boolean repeat = false;
      for (Wall wall : mazeLocations) {
        if (wall.getLocation().x == x && wall.getLocation().y == y) {
          repeat = true;
          break;
        }
      }
      if (repeat) {
        continue;
      }
      return new Point(x, y);
    }
  }

  public Set<Entrance> findOpenEntrances() {
    Set<Entrance> openedEntrances = new HashSet<>();
    for (Entrance entry : getEntrances()) {
      if (entry.isOpen()) {
        openedEntrances.add(entry);
      }
    }
    return openedEntrances;
  }

  /**
   * Ищет в данном уровне вход с названием inputEntry
   *
   * @return местоположение входа
   */
  public Point findEntry(char inputEntry) {
    Set<Entrance> openedEntrances = findOpenEntrances();
    for (Entrance openedEntry : openedEntrances) {
      if (openedEntry.getName() == inputEntry) {
        Point location = openedEntry.getLocation();
        return new Point(location.x, location.y);
      }
    }
    return null;
  }

  /**
   * Ищет в данном уровне вход с местоположением location
   * @param location
   * @return имя входа
   */
  public char getEntranceName(Point location) {
    Set<Entrance> openedEntrances = findOpenEntrances();
    for (Entrance openedEntry : openedEntrances) {
      Point entryLocation = openedEntry.getLocation();
      if (entryLocation.x == location.x && entryLocation.y == location.y) {
        return openedEntry.getName();
      }
    }
    //дефолтное значение для char == ошибка
    return '\u0000';
  }
}
