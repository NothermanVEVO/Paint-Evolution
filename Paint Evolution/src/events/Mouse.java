package src.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import src.ToolBar;
import src.Items.Pencil;
import src.sth.Vector2;

public class Mouse implements MouseListener, MouseMotionListener{

    private static boolean mousePressed;
    private static int mouseButton;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButton = e.getButton();
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButton = e.getButton();
        mousePressed = false;
        Pencil.setMouseWasJustReleased(true);
        if(mouseButton == MouseEvent.BUTTON1){
            switch (ToolBar.getTool()) {
                case PENCIL:
                    Pencil.setInitialVector(null);
                    break;
                default:
                    System.err.println("UNKNOWN TOOL RELEASE");
                    System.exit(-1);
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(mouseButton == MouseEvent.BUTTON1){
            switch (ToolBar.getTool()) {
                case PENCIL:
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    if(Pencil.getInitialVector() == null){
                        Pencil.setInitialVector(new Vector2(mouseX, mouseY));
                        return;
                    }
                    Pencil.addLine(new Line2D.Double(Pencil.getInitialVector().x, Pencil.getInitialVector().y, mouseX, mouseY));
                    Pencil.setInitialVector(null);
                    break;
                
                default:
                    System.err.println("UNKNOWN TOOL DRAGGED");
                    System.exit(-1);
                    break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
