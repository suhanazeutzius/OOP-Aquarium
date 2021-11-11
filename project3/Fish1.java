/*  @author Suhana Zeutzius
    purpose: create a little fish creature
*/

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Fish1 extends Creature {
  private int goalx, goaly;
  private int xmax, initgy;
  private int x, y;
  private JFrame f;
  private BufferedImage img;
  private boolean isAlive = true;

  /*  @param int h Health
      @param JSlider speed Speed
      @param JSlider m Metabolism slider
      @param Random r
      @param JFrame f
  */
  public Fish1(int h, JSlider speed, JSlider m, Random r, JFrame f){
    super(h, speed, m);
    this.x = r.nextInt(f.getWidth() - 350);
    this.y = r.nextInt(f.getHeight());
    this.f = f;
    this.initgy = y;
    this.xmax = f.getWidth() - 200;
    this.goalx = new Random().nextBoolean() ? 0 : xmax;
    this.goaly = y+5;
    BufferedImage i = null;
    try {
      i = ImageIO.read(new File("fish1.png"));
    } catch (IOException e) {}
    this.img = i;
  }

  /*  @param Graphics g
  */
  public void paint(Graphics g){
    Graphics2D g2 = (Graphics2D)g;

    if(clicked){
      g2.setColor(Color.RED);
      if(goalx > 0)
        g2.fill(new Rectangle(x-45, y+15, 38, 17));
      else if(goalx == 0)
        g2.fill(new Rectangle(x+5, y+15, 38, 17));
    }

    if(isAlive){
      if(goalx > 0)
        g2.drawImage(img, x, y, -50, 50, null);
      else if(goalx == 0)
        g2.drawImage(img, x, y, 50, 50, null);
    }
    else if(!isAlive){
      if(goalx > 0)
        g2.drawImage(img, x-50, y+50, 50, -50, null);
      else if(goalx == 0)
        g2.drawImage(img, x+50, y+50, -50, -50, null);
    }
  }

  public void swim(){
    if(isAlive){
      xmax = f.getWidth() - 200;
      if (x >= (xmax-150)){
        goalx = 0;
        //x = x - 100;
      }
      else if(x <= 0){
        goalx = xmax;
        x = 0;
      }
      else if((this.y >= goaly) && (goaly == (initgy + 5))){
        goaly = goaly-10;
      }
      else if((this.y <= goaly) && (goaly == (initgy - 5))){
        goaly = goaly + 10;
      }

      if(goalx > 0)
        this.x = this.x + this.speed;
      else if(goalx == 0)
        this.x = this.x - this.speed;

      if((goaly == (initgy+5)) && ((x % 5) == 0))
        this.y = this.y + 1;
      else if((goaly == (initgy-5)) && ((x % 5) == 0))
        this.y = this.y - 1;
    }
    else if(!isAlive){
      y--;
    }
  }

  /*  @return String "fish"
  */
  public String getName(){
    return "fish";
  }

  /*  @return Rectangle Rectangle containing fish image
  */
  public Rectangle getBounds(){
    Rectangle r = new Rectangle(0,0,0,0);
    if(goalx > 0)
      r = new Rectangle(x-45, y+15, 38, 17);
    else if(goalx == 0)
      r = new Rectangle(x+5, y+15, 38, 17);
    return r;
  }

  public void die(){
    super.die();
    this.isAlive = false;
  }
}
