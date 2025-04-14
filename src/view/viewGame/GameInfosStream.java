package view.viewGame;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class GameInfosStream extends OutputStream{
    private final TextArea infosArea;

    public GameInfosStream(TextArea ta)
    {
        this.infosArea = ta;
    }

    @Override
    public void write(int b) throws IOException
    {
        this.infosArea.appendText(String.valueOf((char)b));
        //this.infosArea.appendText("\n");
    }
}
