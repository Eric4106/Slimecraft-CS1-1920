/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

import java.awt.Color;
import java.awt.Graphics;

//@author 710568

public class Food extends Sprite{
    private static final int speed = 0;
    private static final int radius = 10;
    private static final Color color = Color.YELLOW;
    
    public Food(int x, int y) {
        super("Food", speed, 1, x, y, radius, color, false);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillOval(super.getX(), super.getY(), super.getRadius(), super.getRadius());
    }
}
