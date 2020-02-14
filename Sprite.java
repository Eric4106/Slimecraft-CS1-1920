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
    private int speed, x, y, vx, vy, radius, health, age;
    private String type;
    private Color color;
    private Rectangle bounds;
    private boolean alive, infected, onceHad;
    
    public Sprite(String type, int speed, int health, int age, int x, int y, int radius, Color color, boolean infected) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.vx = (int) (Math.random() * this.speed);
        this.vy = (int) (Math.random() * this.speed);
        this.health = health;
        this.age = age;
        this.radius = radius;
        this.type = type;
        this.color = color;
        this.bounds = new Rectangle(x, y, 2 * radius, 2 * radius);
        this.alive = true;
        this.infected = infected;
    }
    
    public void update() {
        if (Math.random() < .001) {
            vx = (int) (Math.random() * speed);
        }
        if (Math.random() < .005) {
            vy = (int) (Math.random() * speed);
        }
        x += vx;
        y += vy;
        age++;
        bounds = new Rectangle(x, y, 2 * radius, 2 * radius);
        if (isInfected()) {
            health -= 2;
        }
        if (health <= 0) {
            die();
        }
        if (this.type.equals("Virus")) {
            if (Math.random() < .1) {
                health--;
            }
        }
        if (Math.random() < .00001 && !onceHad) {
            setInfected();
        }
        if (Math.random() < .00001 && infected) {
            infected = false;
        }
    }
    
    public void grow(double rate) {
        this.radius *= rate;
    }
    
    public abstract void draw(Graphics g);
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getAge() {
        return age;
    }

    public void die() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    
    public void setInfected() {
        this.infected = true;
        this.onceHad = true;
    }

    public boolean isInfected() {
        return infected;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public boolean collide(Sprite other) {
        boolean collides = false;
        double distance = Math.sqrt((((this.x + this.radius) - (other.x + other.radius)) * ((this.x + this.radius) - (other.x + other.radius))) + (((this.y + this.radius) - (other.y + other.radius)) * ((this.y + this.radius) - (other.y + other.radius))));
        if (distance - (this.radius + other.getRadius()) <= 0) {
            collides = true;
            if (other.isInfected()) {
                this.setInfected();
            }
            else if (this.isInfected()) {
                other.setInfected();
            }
        }
        return collides;
    }
    
    public void didCollide() {
        vx = -vx;
        vy = -vy;
    }
    
    public void eat(Sprite other) {
        if (this.type.equals("Squib") && other.type.equals("Food")) {
            if (this.collide(other)) {
                this.radius = (int) (this.radius * 1.4);
                other.die();
            }
        }
    }
    
    public void collideWorldBounds(int cWidth, int cHeight) {
        if (this.x < 0 || this.x + this.radius > cWidth)
            this.vx = -this.vx;
        if (this.y < 0 || this.y + this.radius > cHeight)
            this.vy = -this.vy;       
    }
}
