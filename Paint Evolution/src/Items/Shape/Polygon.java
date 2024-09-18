package src.Items.Shape;

import java.awt.Graphics2D;

import src.sth.Mat;
import src.sth.Vector2;
import src.sth.Vector2D;

public class Polygon extends Shape{

    private Vector2[] points;
    private Vector2[] originalPoints;
    private int vertices;

    public Polygon(boolean auto_draw, double x, double y, Vector2[] points) {
        super(auto_draw);
        this.points = points;
    }

    public Polygon(boolean auto_draw, double x, double y, int vertices, double size){
        super(auto_draw);
        
        this.x = x;
        this.y = y;
        this.vertices = Mat.clamp(vertices, 3, 90);
        
        final Vector2 startPos = Vector2D.UP.getVct();
        points = new Vector2[vertices];
        originalPoints = new Vector2[vertices];
        double n = 360.0 / vertices;
        double sum = 0.0;
        for (int i = 0; i < vertices; i++) {
            originalPoints[i] = Vector2.rotate(startPos, Math.toRadians(sum));
            points[i] = Vector2.sum(Vector2.mult(originalPoints[i], size), new Vector2(x, y));
            sum += n;
        }
    }

    @Override
    public void setSize(double size){
        this.size = size;
        for (int i = 0; i < points.length; i++) {
            points[i] = Vector2.sum(Vector2.mult(originalPoints[i], size), new Vector2(x, y));
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int x[] = new int[vertices];
        int y[] = new int[vertices];
        for (int i = 0; i < points.length; i++) {
            x[i] = (int) points[i].x;
            y[i] = (int) points[i].y;
            // if (i < points.length - 1) {
            //     g2.drawLine((int) points[i].x, (int) points[i].y, (int) points[i + 1].x, (int) points[i + 1].y);
            //     continue;
            // }
            // g2.drawLine((int) points[i].x, (int) points[i].y, (int) points[0].x, (int) points[0].y);
        }
        g2.setColor(color);
        g2.drawPolygon(x, y, vertices);
        g2.setColor(fill_color);
        g2.fillPolygon(x, y, vertices);
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

}
