package src.Items.Tools;

import java.awt.event.MouseEvent;

public abstract class Tool {

    public abstract void mouseWasJustPressed(MouseEvent e);
    public abstract void mouseWasJustReleased(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);

}
