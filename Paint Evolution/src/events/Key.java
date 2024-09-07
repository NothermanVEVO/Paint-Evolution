package src.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.Board;

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
        if((ctrlPressed && zPressed) && Board.itemsSize() > 0){
            Board.removeItemAt(Board.itemsSize() - 1);
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
