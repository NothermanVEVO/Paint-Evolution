package src.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.ToolBar;
import src.Items.Pencil;

public class Key implements KeyListener{

    private boolean ctrlPressed;
    private boolean zPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 17:
                ctrlPressed = true;
                break;
            case 90:
                zPressed = true;
                break;
        }
        switch (ToolBar.getTool()) {
            case PENCIL:
                if((ctrlPressed && zPressed) && Pencil.linesSize() > 0){
                    Pencil.removeLineAt(Pencil.linesSize() - 1);
                }
                break;
            default:
                System.err.println("UNKNOWN TOOL KEY PRESSED");
                System.exit(-1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 17:
                ctrlPressed = false;
                break;
            case 90:
                zPressed = false;
                break;
        }
    }

    public boolean isCtrlPressed() {
        return ctrlPressed;
    }

    public boolean isZPressed() {
        return zPressed;
    }

}
