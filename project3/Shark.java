/*  @author Suhana Zeutzius
    purpose: king of the ocean??
*/

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Shark extends Creature {
  private int goalx, goaly;
  private int xmax, ymax;
  private int x, y;
  private JFrame f;
  private BufferedImage img;
  private boolean isAlive = true;
  private boolean onScreen = true;

  /*  @param int h Health
      @param JSlider speed Speed
      @param JSlider m Metabolism slider
      @param Random r
      @param JFrame f
  */
  public Shark(int h, JSlider speed, JSlider m, Random r, JFrame f){
    super(h, speed, m);
    this.x = r.nextInt(f.getWidth() - 300);
    this.y = r.nextInt(f.getHeight());
    this.f = f;
    this.xmax = f.getWidth() - 200;
    this.ymax = f.getHeight() - 100;
    this.goalx = new Random().nextBoolean() ? 0 : xmax;
    this.goaly = new Random().nextBoolean() ? 0 : ymax;
    BufferedImage i = null;
    try {
      i = ImageIO.read(new File("shark.png"));
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
      g2.fill(new Rectangle(x+20, y+35, 25, 25));
      else if(goalx == 0)
      g2.fill(new Rectangle(x-45, y+35, 25, 25));
    }

    if(isAlive){
      if(goalx > 0)
        g2.drawImage(img, (x - 50), y, 100, 100, null);
      else if(goalx == 0)
        g2.drawImage(img, (x + 50), y, -100, 100, null);
    }
    else if(!isAlive){
      if(goalx > 0)
        g2.drawImage(img, (x - 50), y+70, 100, -100, null);
      else if(goalx == 0)
        g2.drawImage(img, (x + 50), y+70, -100, -100, null);
    }
  }

  public void swim(){
    if(isAlive){
      xmax = f.getWidth() - 200;
      ymax = f.getHeight() - 70;
      if (x >= (xmax-130)){
        goalx = 0;
      }
      else if(x <= 50){
        goalx = xmax;
      }
      if (y >= (ymax)){
        goaly = 0;
      }
      else if(y <= -30){
        goaly = ymax;
      }

      if(goalx > 0)
      this.x = this.x + this.speed;
      else if(goalx == 0)
      this.x = this.x - this.speed;

      if(goaly > 0)
      this.y = this.y + this.speed;
      else if(goaly == 0)
      this.y = this.y - this.speed;
    }
    else if(!isAlive){
      if(y < 0)
        onScreen = false;
      y--;
    }
  }

  /*  @return String "shark"
  */
  public String getName(){
    return "shark";
  }

  /*  @return Rectangle Rectangle containing shark MOUTH
  */
  public Rectangle getBounds(){
    Rectangle r = new Rectangle(0,0,0,0);
    if(goalx > 0)
      r = new Rectangle(x+20, y+35, 25, 25);
    else if(goalx == 0)
      r = new Rectangle(x-45, y+35, 25, 25);
    return r;
  }

  public void die(){
    super.die();
    this.isAlive = false;
  }
}
