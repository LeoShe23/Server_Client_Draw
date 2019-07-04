package services;

import entyties.DrawingElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    final private List<DrawingElement> list;

    public DrawPanel(List<DrawingElement> list) {
        this.list = list;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x1, x2, y1, y2;
        for (int i = 0; i < list.size() - 1; i ++) {
            if (!list.get(i+1).getAction().equals("start")) {
                x1 = Math.abs((int) list.get(i).getX());
                y1 = Math.abs((int) list.get(i).getY());
                x2 = Math.abs((int) list.get(i + 1).getX());
                y2 = Math.abs((int) list.get(i + 1).getY());
            }
            else {
                x1 = Math.abs((int) list.get(i).getX());
                y1 = Math.abs((int) list.get(i).getY());
                x2 = Math.abs((int) list.get(i).getX());
                y2 = Math.abs((int) list.get(i).getY());
            }
            System.out.println(list.get(i));
            System.out.println("x = " + x1);
            System.out.println("y = " + y1);
            g2.setColor(Color.decode(list.get(i).getColor()));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private List<DrawingElement> getFigures (List<DrawingElement> list) {
        List<DrawingElement> res = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i ++) {
            res.add(list.get(i));
            list.remove(0);
            if (list.get(i + 1).getAction().equals("move"))
                res.add(list.get(i + 1));
        }
        return res;
    }
}
