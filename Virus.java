/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;

public class Virus extends Sprite{
    private static final int speed = 5;
    private static final int radius = 5;
    private static final Color color = Color.MAGENTA;
    public Virus(int x, int y) {
        super("Virus", speed, 1000, 0, x, y, radius, color, false);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillOval(super.getX(), super.getY(), 2 * super.getRadius(), 2 * super.getRadius());
    }
    
    public void infect(Squib other) {
        if (Math.random() < .9) {
            other.setInfected();
        }
        if (Math.random() < .9) {
            this.die();
        }
    }
}
