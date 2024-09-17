package src.Items.Line;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import src.GUI.Board;
import src.Items.GraphicItem;

public class Lines extends GraphicItem{

    private ArrayList<Line2D> linesArray = new ArrayList<>();

    public Lines(boolean auto_draw){
        super(auto_draw);
    }

    public Lines(boolean auto_draw, ArrayList<Line2D> linesArray){
        super(auto_draw);
        this.linesArray = linesArray;
        this.color = Board.getColor();
        adjust_center();
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Line2D line : linesArray) {
            // double mx, my, new_x1, new_y1, new_x2, new_y2;
            // mx = (line.getX1() + line.getX2()) / 2;
            // my = (line.getY1() + line.getY2()) / 2;
            
            // new_x1 = mx + (line.getX1() - mx) * scale.x;
            // new_y1 = my + (line.getY1() - my) * scale.y;
            // new_x2 = mx + (line.getX2() - mx) * scale.x;
            // new_y2 = my + (line.getY2() - my) * scale.y;
            // Line2D new_line = new Line2D.Double(new_x1, new_y1, new_x2, new_y2);

            // g2.draw(new_line);
            g2.draw(line);
        }
    }

    public void setLines2DArray(ArrayList<Line2D> linesArray){
        this.linesArray = linesArray;
        adjust_center();
    }

    public void adjust_center(){
        int lowest_x = Integer.MAX_VALUE;
        int highest_x = Integer.MIN_VALUE;

        int lowest_y = Integer.MAX_VALUE;
        int highest_y = Integer.MIN_VALUE;

        for (Line2D line2d : linesArray) {
            int x1 = (int) line2d.getX1();
            int x2 = (int) line2d.getX2();
            int y1 = (int) line2d.getY1();
            int y2 = (int) line2d.getY2();

            if (x1 < lowest_x) {
                lowest_x = x1;
            } else if (x1 > highest_x) {
                highest_x = x1;
            }
            if (x2 < lowest_x) {
                lowest_x = x2;
            } else if (x2 > highest_x) {
                highest_x = x2;
            }

            if (y1 < lowest_y) {
                lowest_y = y1;
            } else if (y1 > highest_y) {
                highest_y = y1;
            }
            if (y2 < lowest_y) {
                lowest_y = y2;
            } else if (y2 > highest_y) {
                highest_y = y2;
            }
        }

        x = (lowest_x + highest_x) / 2;
        y = (lowest_y + highest_y) / 2;
    }

}
