/*  @author Suhana Zeutzius
    purpose: makes a creature that can get added to the tank
*/

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;


public abstract class Creature{
  protected int health;
  protected int metabolism;
  protected int speed = 1; //***deadliness if its the jellyfish***
  private int counter = 0;
  private JSlider speedSlider;
  private JSlider metSlider;
  protected boolean clicked = false;

  /*  @param int health
      @param JSlider speed
      @param JSlider met
  */
  public Creature(int health, JSlider speed, JSlider met){
    this.speedSlider = speed;
    this.metSlider = met;
    this.health = health;
  }

  /*  @param String s type of creature eaten adds different amount of health
  */
  public void ateCreature(String s){
    if(s.equals("fish"))
      health = health + 5;
    if(s.equals("jellyfish"))
      health = health + 3;
    if(s.equals("plankton"))
      health = health + 1;
  }

  public void clicked(){
    this.clicked = true;
  }

  public void unClicked(){
    this.clicked = false;
  }

  /*  purpose: updates the stats panel for whatever the last fish clicked was,
               also updates the health depending on the metabolism speed
  */
  public void updateStats(){
    if(clicked)
      FishTank.clickStats(this.getName(), this.health, this.speed);
      
    this.metabolism = metSlider.getValue();
    this.speed = speedSlider.getValue();

    counter++;
    //when metabolism is one the health goes down 1 every 5 seconds
    //when metabolism is 10 is goes down 1 every ~1 sec
    if((counter % (250 - 20 * (metabolism - 1)) == 0) && health>0)
      this.health = this.health - 1;
  }

  public boolean hasHealth(){
    if(health == 0)
      return false;
    return true;
  }

  public void die(){
    this.health = 0;
  }

  /*  @param Creature c Creature that gets stung (if jellyfish or plankton nothing happens)
      purpose: Jellyfish MIGHT kill fish or shark based on luck/deadliness
  */
  public boolean sting(Creature c){
    if(this.getName().equals("jellyfish")){
      int n = 90;
      if(c.getName().equals("fish"))
      // 1 in 3 chance fish  will not be stung if deadliness is 1
      //basically 100% will die if deadliness is 3
        n = 90; //has to be high so fish can swim all the way across
      else if(c.getName().equals("shark"))
        // 1 in 6 if deadliness is 2, 1 in 4 if deadliness is 3
        //1 in 12 it kills it on the first pixel interaction or else shark eats it
        n = 12;

      Random rand = new Random();
      int x = this.speed * rand.nextInt(180);
      if( (x % n) == 0)
        return true;
    }

    return false;
  }

  public abstract void swim();
  public abstract void paint(Graphics g);
  public abstract String getName();
  public abstract Rectangle getBounds();
}
