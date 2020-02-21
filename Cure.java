/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SlimeCraft;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 641580
 */
public class Cure extends Sprite {

    private static final Color COLOR = new Color(0, 144, 255);
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int SPEED = 0;

    public Cure (int x, int y) {
        super(SPEED, x, y, WIDTH, HEIGHT, COLOR);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.drawOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
}