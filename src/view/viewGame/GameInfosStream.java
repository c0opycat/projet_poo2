package view.viewGame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * A custom OutputStream that redirects output to a JavaFX TextArea.
 * Allows real-time display of game information in the UI.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class GameInfosStream extends OutputStream {

  private final TextArea infosArea;
  private final ByteArrayOutputStream byteBuffer;

  /**
   * Constructs a new GameInfosStream instance.
   *
   * @param ta the TextArea where the output will be displayed.
   */
  public GameInfosStream(TextArea ta) {
    this.infosArea = ta;
    this.byteBuffer = new ByteArrayOutputStream();
  }

  /**
   * Writes a single byte to the stream and appends the resulting text to the TextArea.
   *
   * @param b the byte to write.
   * @throws IOException if an I/O error occurs.
   */
  @Override
  public void write(int b) throws IOException {
    byteBuffer.write(b);
    String text = byteBuffer.toString(StandardCharsets.UTF_8.name());
    appendTextToArea(text);
    byteBuffer.reset();
  }

  /**
   * Appends the given text to the TextArea on the JavaFX Application Thread.
   *
   * @param text the text to append.
   */
  private void appendTextToArea(String text) {
    Platform.runLater(() -> infosArea.appendText(text));
  }
}
