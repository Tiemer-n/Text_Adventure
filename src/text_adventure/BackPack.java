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
    public int wood ;
    public int leather ;
    public int iron ;
    public int steel ;
    public int woodenClub ;
    public int sword ;
    public int knife ;
    public int medicine ;
    
    public BackPack(int food, int water, int wood, int leather, int iron, int steel, int woodenClub, int sword, int knife, int medicine) {
        this.food = food;
        this.water = water;
        this.wood = wood;
        this.leather = leather;
        this.iron = iron;
        this.steel = steel;
        this.woodenClub = woodenClub;
        this.sword = sword;
        this.knife = knife;
        this.medicine = medicine;
    }

    public int getFood() {
        return food;
    }

    public int getWater() {
        return water;
    }

    public int getWood() {
        return wood;
    }

    public int getLeather() {
        return leather;
    }

    public int getIron() {
        return iron;
    }

    public int getSteel() {
        return steel;
    }

    public int getWoodenClub() {
        return woodenClub;
    }

    public int getSword() {
        return sword;
    }

    public int getKnife() {
        return knife;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setLeather(int leather) {
        this.leather = leather;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public void setSteel(int steel) {
        this.steel = steel;
    }

    public void setWoodenClub(int woodenClub) {
        this.woodenClub = woodenClub;
    }

    public void setSword(int sword) {
        this.sword = sword;
    }

    public void setKnife(int knife) {
        this.knife = knife;
    } 
    
}
