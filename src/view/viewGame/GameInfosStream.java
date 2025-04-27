package view.viewGame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class GameInfosStream extends OutputStream {

  private final TextArea infosArea;
  private final ByteArrayOutputStream byteBuffer;

  public GameInfosStream(TextArea ta) {
    this.infosArea = ta;
    this.byteBuffer = new ByteArrayOutputStream();
  }

  @Override
  public void write(int b) throws IOException {
    byteBuffer.write(b);
    // Convertir les octets en chaîne UTF-8 et ajouter au TextArea
    String text = byteBuffer.toString(StandardCharsets.UTF_8.name());
    appendTextToArea(text);
    byteBuffer.reset(); // Réinitialiser le buffer
  }

  private void appendTextToArea(String text) {
    Platform.runLater(() -> infosArea.appendText(text));
  }
}
