package src.Items.Tools;

import java.awt.event.MouseEvent;

import src.Window;
import src.GUI.Board;
import src.GUI.ToolBar;
import src.Items.Shape.Oval;
import src.Items.Shape.Polygon;
import src.Items.Shape.Rectangle;
import src.Items.Shape.Shape;
import src.sth.Vector2;

public class PencilShape extends Tool{

    public static enum Shapes{
        SQUARE,
        OVAL,
        POLYGON
    }

    private static Shapes current_shape;

    private static Shape pseudoShape;

    private static Vector2 initialPosition;

    public PencilShape(Shapes shape){
        current_shape = shape;
        switch (current_shape) {
            case SQUARE:
                pseudoShape = new Rectangle(true, 0, 0, 0, 0);
                break;
            case OVAL:
                pseudoShape = new Oval(true, 0, 0, 0, 0);
                break;
            case POLYGON:
                pseudoShape = new Polygon(true, 0, 0, ToolBar.getPolygonSides(), 0);
                break;
            default:
                System.err.println("SHAPE NOT VALID");
                System.exit(-1);
                break;
        }
        pseudoShape.set_pseudo(true);
    }

    public static void setCurrentShape(Shapes shape){
        current_shape = shape;
    }

    @Override
    public void mouseWasJustPressed(MouseEvent e) {
        initialPosition = new Vector2(e.getX(), e.getY());
        switch (current_shape) {
            case SQUARE:
                pseudoShape = new Rectangle(true, e.getX(), e.getY(), 0, 0);
                break;
            case OVAL:
                pseudoShape = new Oval(true, e.getX(), e.getY(), 0, 0);
                break;
            case POLYGON:
                pseudoShape = new Polygon(true, e.getX(), e.getY(), ToolBar.getPolygonSides(), 0);
                break;
            default:
                break;
        }
        pseudoShape.set_pseudo(true);
    }

    @Override
    public void mouseWasJustReleased(MouseEvent e) {
        switch (current_shape) {
            case SQUARE:
                new Rectangle(true, pseudoShape.getX(), pseudoShape.getY(), 
                    pseudoShape.getWidth(), pseudoShape.getHeight()).setAngle(pseudoShape.getAngle());
                break;
            case OVAL:
                new Oval(true, pseudoShape.getX(), pseudoShape.getY(), 
                    pseudoShape.getWidth(), pseudoShape.getHeight()).setAngle(pseudoShape.getAngle());
                break;
            case POLYGON:
                double distance = Math.abs(Vector2.distanceBetween(new Vector2(e.getX(), e.getY()), 
                    pseudoShape.getVector2()));
                new Polygon(true, pseudoShape.getX(), pseudoShape.getY(), 
                    ToolBar.getPolygonSides(), distance).setAngle(pseudoShape.getAngle());
                break;
            default:
                break;
        }
        Board.removeItem(pseudoShape);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(pseudoShape.getClass() != Polygon.class){
            pseudoShape.setX(initialPosition.x);
            pseudoShape.setY(initialPosition.y);
            pseudoShape.setWidth(Math.abs(e.getX() - initialPosition.x));
            pseudoShape.setHeight(Math.abs(e.getY() - initialPosition.y));
            // double distanceX = e.getX() - initialPosition.x;
            // double distanceY = e.getY() - initialPosition.y;
            // if(distanceX >= 0){
            //     pseudoShape.setX(initialPosition.x);
            //     pseudoShape.setWidth(distanceX);
            // } else{
            //     pseudoShape.setX(initialPosition.x - Math.abs(distanceX));
            //     pseudoShape.setWidth(Math.abs(distanceX));
            // }
            // if(distanceY >= 0){
            //     pseudoShape.setY(initialPosition.y);
            //     pseudoShape.setHeight(distanceY);
            // } else{
            //     pseudoShape.setY(initialPosition.y - Math.abs(distanceY));
            //     pseudoShape.setHeight(Math.abs(distanceY));
            // }
        } else{
            double distance = Math.abs(Vector2.distanceBetween(new Vector2(e.getX(), e.getY()), 
            pseudoShape.getVector2()));
            pseudoShape.setSize(distance);
        }
        Window.board.grabFocus();
        Window.board.repaint();
    }

}
