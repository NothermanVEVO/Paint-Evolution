package src.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import src.GUI.Board;

public class Mouse implements MouseListener, MouseMotionListener{

    private static boolean mousePressed;
    private static int mouseButton;

    private static int x;
    private static int y;

    private static int x_when_pressed;
    private static int y_when_pressed;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x_when_pressed = e.getX();
        y_when_pressed = e.getY();
        mouseButton = e.getButton();
        mousePressed = true;
        if (mouseButton == MouseEvent.BUTTON1) {
            Board.getCurrentTool().mouseWasJustPressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButton = e.getButton();
        mousePressed = false;
        if (mouseButton == MouseEvent.BUTTON1) {
            Board.getCurrentTool().mouseWasJustReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if(mouseButton == MouseEvent.BUTTON1){
            Board.getCurrentTool().mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getX_when_pressed() {
        return x_when_pressed;
    }

    public static int getY_when_pressed() {
        return y_when_pressed;
    }

}
