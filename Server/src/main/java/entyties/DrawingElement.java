package entyties;

import java.awt.*;
import java.io.Serializable;

public class DrawingElement implements Serializable {
    public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private String action;
    private double x;
    private double y;
    private String color;

    public DrawingElement(String action, double x, double y, String color) {
        this.action = action;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getX() {
        return x * WIDTH;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y * HEIGHT;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "DrawingElement{" +
                "action='" + action + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", color='" + color + '\'' +
                '}';
    }
}
