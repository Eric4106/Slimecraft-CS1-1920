/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;

public class Squodge extends Squib{
    private static final int speed = 5;
    private static final Color color = Color.RED;
    
    public Squodge(int x, int y) {
        super(speed, x, y, color);
    }
    
    public void fight(Squidge squidge) {
        if (super.collide(squidge)) {
            if (super.getStrength() >= squidge.getStrength()) {
                this.didWin(squidge);
                squidge.die();
            }
            else {
                squidge.didWin(this);
                this.die();
            }
        }
    }
    
    public Squodge reproduce(Squodge mate) {
        int newX = super.getX() + (int) (Math.random() * 20 - 10);
        int newY = super.getY() + (int) (Math.random() * 20 - 10);
        Squodge baby = new Squodge(newX, newY);
        return baby;
    }
}
