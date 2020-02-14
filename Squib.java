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
    private static final int radius = 10;
    private int strength;
    
    public Squib(int speed, int x, int y, Color color) {
        super("Squib", speed, 500, 0, x, y, radius, color, false);
        this.strength = (int) (Math.random() * 20);
    }
    
    public void eat(Food food) {
        if (super.collide(food)) {
            super.setRadius(super.getRadius() + 5);
            food.die();
        }
    }
    
    public void didWin(Squib other) {
        this.strength += this.strength - other.strength;
        super.grow(1.2);
    }
    
    public int getStrength() {
        return strength;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillOval(super.getX(), super.getY(), 2 * super.getRadius(), 2 * super.getRadius());
        if (super.isInfected()) {
            g.setColor(Color.MAGENTA);
            g.fillOval(super.getX() + (super.getRadius() / 2), super.getY() + (super.getRadius() / 2), super.getRadius(), super.getRadius());
        }
    }
}
