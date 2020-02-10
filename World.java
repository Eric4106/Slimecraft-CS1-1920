/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class World extends JPanel{
    private ArrayList<Sprite> sprites = new ArrayList<>();
    Timer timer;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000 / 30);
        int width = 800;
        int height = 600;
        int margin = 300;
        super.setSize(width, height);
        for (int i = 0; i < 250; i++) {
            int lx = (int) (Math.random() * (width - margin));
            int ly = (int) (Math.random() * height);
            sprites.add(new Squidge(lx, ly));
            int rx = (int) ((Math.random() * (width - margin)) + margin);
            int ry = (int) (Math.random() * height);
            sprites.add(new Squodge(rx, ry));
        }
        
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            sprites.add(new Food(x, y));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        for (Sprite sprite : sprites) {
            sprite.draw(g);
            sprite.update();
            for (Sprite other : sprites) {
                if (sprite != other) {
                    sprite.collide(other);
                    sprite.eat(other);
                }
            }
        }
    }
    
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
}
