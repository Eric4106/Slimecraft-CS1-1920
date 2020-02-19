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
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class World extends JPanel{
    private ArrayList<Squidge> squidges = new ArrayList<>();    
    private ArrayList<Squodge> squodges = new ArrayList<>();    
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();   
    private ArrayList<Virus> viruses = new ArrayList<>();
    private ArrayList<Squib> squibs = new ArrayList<>();    
    Timer timer;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/30);
        super.setSize(800, 600);
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2);
            int y = (int) (Math.random() * 600);
            Squidge squidge = new Squidge(x,y);
            sprites.add(squidge);
            squibs.add(squidge);
            squidges.add(squidge);
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Squodge squodge = new Squodge(x,y);
            sprites.add(squodge);    
            squibs.add(squodge);    
            squodges.add(squodge);    
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Virus virus = new Virus(x,y);
            viruses.add(virus);
            sprites.add(virus);
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.BLACK);
        
        if (Math.random() < .001) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }
        
        for (Squib squib : squibs) {
            for (Food food : foods) {
                squib.eat(food);
            }            
        }
        
        for (Virus virus : viruses) {
            for (Squib squib : squibs) {
                double distance = Math.sqrt((((virus.getX() + virus.getRadius()) - (squib.getX() + squib.getRadius())) * ((virus.getX() + virus.getRadius()) - (squib.getX() + squib.getRadius()))) + (((virus.getY() + virus.getRadius()) - (squib.getY() + squib.getRadius())) * ((virus.getY() + virus.getRadius()) - (squib.getY() + squib.getRadius()))));
                if (distance - (virus.getRadius() + squib.getRadius()) <= 0) {
                    virus.infect(squib);
                }
            }
        }
        
        ArrayList<Squidge> newSquidges = new ArrayList<>();
        ArrayList<Squodge> newSquodges = new ArrayList<>();
        
        for (Squidge squidge : squidges) {
            for (Squodge squodge : squodges) {
                squidge.fight(squodge);
            }
            for (Squidge otherSquidge : squidges) {
                if (squidge == otherSquidge) continue;
                if (squidge.collide(otherSquidge) && Math.random() < 0.01) {
                    newSquidges.add(squidge.reproduce(otherSquidge));
                }
            }
        }
        for (Squodge squodge : squodges) {
            for (Squodge otherSquodge : squodges) {
                if (squodge == otherSquodge) continue;
                if (squodge.collide(otherSquodge) && Math.random() < 0.02 && squodge.getAge() > 20) {
                    newSquodges.add(squodge.reproduce(otherSquodge));
                }
            }
        }
        for (Sprite sprite : sprites) {
            sprite.draw(g);
            sprite.update();
            sprite.collideWorldBounds(800,600);
        }
        
        takeOutTheTrash();
        addNewSquibs(newSquidges, newSquodges);
    }
    
    private void addNewSquibs(ArrayList<Squidge> newSquidges, ArrayList<Squodge> newSquodges) {
        squidges.addAll(newSquidges);
        squodges.addAll(newSquodges);
        sprites.addAll(newSquidges);
        sprites.addAll(newSquodges);
    }
    
    private void takeOutTheTrash() {
        ArrayList<Sprite> trash = new ArrayList<>();
        for (Sprite sprite : sprites) {
            if (!sprite.isAlive())
                trash.add(sprite);
        }
        sprites.removeAll(trash);
        trash.clear();
        for (Food food : foods) {
            if (!food.isAlive())
                trash.add(food);
        }
        foods.removeAll(trash);
        trash.clear();
        for (Squidge squidge : squidges) {
            if (!squidge.isAlive())
                trash.add(squidge);
        }
        squidges.removeAll(trash);
        trash.clear();
        for (Squodge squodge : squodges) {
            if (!squodge.isAlive())
                trash.add(squodge);
        }
        squodges.removeAll(trash);
        trash.clear();       
    }
    
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
    
    public void mousePressed(MouseEvent e) {
        Rectangle mousePos = new Rectangle(e.getX(), e.getY(), 1, 1);
        for (Sprite sprite : sprites) {
            if (mousePos.intersects(sprite.getBounds())) {
                sprite.die();
            }
        }
    }
}
