/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digbuild;

//@author 710568

import java.awt.Color;

public class Squidge extends Squib{
    private static final int SPEED = 5;
    private static final Color COLOR = Color.BLUE;
    
    public Squidge(int x, int y) {
        super(SPEED, x, y, COLOR);
    }
}
