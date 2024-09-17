package src.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.GUI.Board;
import src.Items.GraphicItem;
import src.Items.Line.Lines;

public class Key implements KeyListener{

    private static boolean ctrl_pressed;
    private static boolean z_pressed;
    private static boolean y_pressed;
    private static boolean alt_pressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case 17:
                ctrl_pressed = true;
                break;
            case 18:
                alt_pressed = true;
                break;
            case 89:
                y_pressed = true;
                break;
            case 90:
                z_pressed = true;
                break;
        }
        if(!Mouse.isMousePressed()){
            if((ctrl_pressed && z_pressed) && Board.itemsSize() > 0){
                Board.removeItemAt(Board.itemsSize() - 1);
            } else if((ctrl_pressed && y_pressed) && Board.itemsCopySize() > 0){
                Board.dropItemsCopyAt(Board.itemsCopySize() - 1);
            }
        } else{
            if(alt_pressed && Board.itemsSize() > 0){
                GraphicItem item = Board.getItemAt(Board.itemsSize() - 1);
                double angle = 0;
                if (item instanceof Lines) {
                    angle = Math.atan2(Mouse.getY() - item.getY(), 
                        Mouse.getX() - item.getX());
                } else if (item instanceof src.Items.Shape.Shape){
                    angle = Math.atan2(Mouse.getY() - Mouse.getY_when_pressed(), 
                        Mouse.getX() - Mouse.getX_when_pressed());
                    angle += Math.toRadians(90);
                }
                item.setAngle(angle);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 17:
                ctrl_pressed = false;
                break;
            case 18:
                alt_pressed = false;
                break;
            case 89:
                y_pressed = false;
                break;
            case 90:
                z_pressed = false;
                break;
        }
    }

    public static boolean is_ctrl_pressed() {
        return ctrl_pressed;
    }

    public static boolean is_alt_pressed() {
        return alt_pressed;
    }

    public static boolean is_y_pressed() {
        return y_pressed;
    }

    public static boolean is_z_pressed() {
        return z_pressed;
    }

}
