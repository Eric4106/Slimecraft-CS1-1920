/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;

public class Squidge extends Squib{
    private static final int speed = 5;
    private static final Color color = Color.CYAN;
    
    public Squidge(int x, int y) {
        super(speed, x, y, color);
    }
    
    public void fight(Squodge squodge) {
        if (super.collide(squodge)) {
            if (super.getStrength() >= squodge.getStrength()) {
                this.didWin(squodge);
                squodge.die();
            }
            else {
                squodge.didWin(this);
                this.die();
            }
        }
    }
    
    public Squidge reproduce(Squidge mate) {
        int newX = super.getX() + (int) (Math.random() * 20 - 10);
        int newY = super.getY() + (int) (Math.random() * 20 - 10);
        Squidge baby = new Squidge(newX, newY);
        return baby;
    }
}
