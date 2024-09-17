package src.Items.Shape;

import java.awt.Graphics2D;

public class Oval extends Shape{

    public Oval(boolean auto_draw) {
        super(auto_draw);
    }

    public Oval(boolean auto_draw, double x, double y, double width, double height) {
        super(auto_draw, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g2) {
        double new_width, new_height, offset_x, offset_y, new_x, new_y;
        width = width == 0 ? 1 : width;
        height = height == 0 ? 1 : height;

        // new_width = width * scale.x * 2;
        // new_height = height * scale.y * 2;
        new_width = width * 2;
        new_height = height * 2;
        offset_x = (new_width / width) / 2;
        offset_y = (new_height / height) / 2;
        new_x = x - offset_x;
        new_y = y - offset_y;
        // g2.drawOval((int) x, (int) y, (int) width, (int) height);
        g2.setColor(color);
        g2.drawOval((int) (new_x - (new_width / 2)), (int) (new_y - (new_height / 2)), (int) new_width, (int) new_height);
        g2.setColor(fill_color);
        g2.fillOval((int) (new_x - (new_width / 2)), (int) (new_y - (new_height / 2)), (int) new_width, (int) new_height);
    }

}
