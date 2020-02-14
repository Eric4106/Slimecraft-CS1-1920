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
public class Slime extends Sprite {
    //static constants
    private static final int WIDTH = 10;
    private static final int HEIGHT = 15;
    private int strength;
    private int health = 100;
    boolean isInfected;
   
    
    
    public Slime(int speed, int x, int y, Color color) {
        super(speed, x, y, WIDTH, HEIGHT, color);
        this.strength = (int) (Math.random() * 20);
        this.isInfected = false;
    }
    
     public void eat(Food food) {
        if (super.getBounds().intersects(food.getBounds()) && food.isAlive()) {
            super.setHeight(super.getHeight() + 5);
            super.setWidth(super.getWidth() + 5);
            this.health += 10;
            food.die();
        }
    }
    public void virusKilling() {
        this.health -= 1;
        if (this.health == 0){
            super.die();
        }
     }
     public void eat(Virus virus) {
        if (super.getBounds().intersects(virus.getBounds()) && virus.isAlive()) {
            this.isInfected = true;
            virus.die();
        }
    }
      public void eat(Cure cure) {
        if (super.getBounds().intersects(cure.getBounds()) && cure.isAlive()) {
            this.isInfected = false;
            this.health += 20;
            cure.die();
        }
    }
     
     public Slime reproduce(Slime mate) {
         Slime baby = new Slime(super.getSpeed(), super.getY(), super.getY(), super.getColor());
         return baby;
     }
     
     public void didWin (Slime other) {
         this.strength += this.strength - other.strength;
         super.grow(1.2);
     }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    public int getStrength() {
        return strength;
    }
    
}