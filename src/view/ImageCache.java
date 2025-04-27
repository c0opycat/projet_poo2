package view;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public class ImageCache {

  private static final Map<String, Image> cache = new HashMap<>();

  public static Image getImage(String name, double width, double height) {
    String key = name;

    if (!cache.containsKey(key)) {
      Image image = new Image(
        "file:./resources/image/" + name,
        width,
        height,
        true,
        true
      );
      cache.put(key, image);
    }

    return cache.get(key);
  }
}
