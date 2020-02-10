/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;

public class Squib extends Sprite {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 15;
    
    public Squib(int speed, int x, int y, Color color) {
        super("Squib", speed, x, y, WIDTH, HEIGHT, color);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
}
