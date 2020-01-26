/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text_adventure;

/**
 *
 * @author Isaac
 */
public class BackPack {
    
    public int food ;
    public int water ;
    public int torches ;

    public BackPack(int food, int water, int torches) {
        this.food = food;
        this.water = water;
        this.torches = torches;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public int getTorches() {
        return torches;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setTorches(int torches) {
        this.torches = torches;
    }
    
    
    
    
    
    
}
