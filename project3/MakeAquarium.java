/*  @author Suhana Zeutzius
    purpose: create the aquarium
*/

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;

public class MakeAquarium extends JPanel implements MouseListener{
  private ArrayList<Creature> allFish;
  private Image background;
  private int mouseX, mouseY;

  /*  @param ArrayList<Creature> a all the creatures in the tank
  */
  public MakeAquarium(ArrayList<Creature> a) {
    super();
    this.allFish = a;
    this.background = Toolkit.getDefaultToolkit().createImage("background2.jpg");
    addMouseListener(this);
  }

  public void click(){
    for(Creature c : allFish){
      if(c.getBounds().contains(mouseX, mouseY))
        c.clicked();
      else
        c.unClicked();
    }
  }

  public void swim() {
    //update all the fish locations
    for(int i = 0; i < allFish.size(); i++){
      if(!allFish.get(i).getName().equals("plankton"))
        allFish.get(i).updateStats();
      allFish.get(i).swim();
      if(!allFish.get(i).hasHealth())
        allFish.get(i).die();
    }
  }

  /*  @param Graphics g
  */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, null);

    //g2.add(label);
    Graphics2D g2 = (Graphics2D)g;
    // this.setBackground(Color.CYAN);

    g2.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(
      RenderingHints.KEY_RENDERING,
      RenderingHints.VALUE_RENDER_QUALITY);

    //paint all the fish that have been added to the array list
    for(int i = 0; i < allFish.size(); i++)
      allFish.get(i).paint(g2);

    Toolkit.getDefaultToolkit().sync();
  }

  //check for intersections of fish and other things
  public void eat(){
    ArrayList<Creature> eaten = new ArrayList<Creature>();
    ArrayList<Creature> allFish2 = allFish;
    for(Creature c1 : allFish){
      Rectangle r1 = c1.getBounds();
      for(Creature c2 : allFish2){
        Rectangle r2 = c2.getBounds();
        if(!r1.equals(r2) && r1.intersects(r2) && c1.hasHealth() && c2.hasHealth()){
          //fish eat plankton
          if(c1.getName().equals("plankton") && c2.getName().equals("fish")){
            eaten.add(c1);
            c2.ateCreature("plankton");
          }
          else if(c2.getName().equals("plankton") && c1.getName().equals("fish")){
            eaten.add(c2);
            c1.ateCreature("plankton");
          }
          //jellyfish maybe kill fish and sharks
          else if(c1.getName().equals("jellyfish") && !c2.getName().equals("jellyfish")){
            if(c1.sting(c2)){
              c2.die();
              c2.updateStats();
            }
          }
          else if(c2.getName().equals("jellyfish") && !c1.getName().equals("jellyfish")){
            if(c2.sting(c1)){
              c1.die();
              c1.updateStats();
            }
          }
          //shark eats everything fish or jellyfish
          //want to go into these if the jellyfish didn't kill the shark
          if(c1.getName().equals("shark") &&
             (c2.getName().equals("fish") || c2.getName().equals("jellyfish"))){
            c1.ateCreature(c2.getName());
            eaten.add(c2);
          }
          if(c2.getName().equals("shark") &&
             (c1.getName().equals("fish") || c1.getName().equals("jellyfish"))){
            c2.ateCreature(c1.getName());
            eaten.add(c1);
          }
        }
      }
    }

    //remove all the creatures that got eaten
    for(Creature x : eaten){
      try{
        x.die();
        x.updateStats();
      }catch(Exception e){}
      allFish.remove(x);
    }
  }
  /*  @param MouseEvent e
      purpose: if the mouse is clicked then check if the spot is in a creature
  */
  public void mouseClicked(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    click();
  }

  public void mouseEntered(MouseEvent e)  {}
  public void mouseExited(MouseEvent e)   {}
  public void mousePressed(MouseEvent e)  {}
  public void mouseReleased(MouseEvent e) {}

}
