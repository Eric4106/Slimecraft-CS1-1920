/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Sprite {
    private int speed, x, y, vx, vy, width, height;
    private String type;
    private Color color;
    private Rectangle bounds;
    
    public Sprite(String type, int speed, int x, int y, int width, int height, Color color) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.vx = (int) (Math.random() * this.speed);
        this.vy = (int) (Math.random() * this.speed);
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = color;
        this.bounds = new Rectangle(x, y, width, height);
    }
    
    public void update() {
        x += vx;
        y += vy;
        bounds = new Rectangle(x, y, width, height);
    }
    
    public abstract void draw(Graphics g);
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void collide(Sprite other) {
        if (this.bounds.intersects(other.bounds)) {
            this.didCollide();
            other.didCollide();
        }
    }
    
    public void didCollide() {
        vx = -vx;
        vy = -vy;
    }
    
    public void eat(Sprite other) {
        if (this.type.equals("Squib") && other.type.equals("Food")) {
            if (this.bounds.intersects(other.bounds)) {
                this.width += 5;
                this.height += 5;
                other.die();
            }
        }
    }
    
    public void die() {
        x = - 1000;
        y = - 1000;
    }
}
