/*  @author Suhana Zeutzius
    purpose: whats the ocean without jellies
*/

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;


public class Jellyfish extends Creature {
  private int goaly;
  private int maxy;
  public int x, y;
  private JFrame f;
  private int counter = 0;
  private BufferedImage img;
  private boolean isAlive = true;

  /*  @param int h Health
      @param JSlider speed Kill slider
      @param JSlider m Metabolism slider
      @param Random r
      @param JFrame f
  */
  public Jellyfish(int h, JSlider kill, JSlider met, Random r, JFrame f){
    super(h, kill, met);
    this.x = r.nextInt(f.getWidth() - 330);
    //only in middle half of the tank
    this.y = (f.getHeight()/4) + r.nextInt(f.getHeight()/2);
    this.f = f;
    this.maxy = y+10;
    this.goaly = new Random().nextBoolean() ? y-10 : y+10;
    BufferedImage i = null;
    try {
      i = ImageIO.read(new File("jellyfish.png"));
    } catch (IOException e) {}
    this.img = i;
  }

  /*  @param Graphics g
  */
  public void paint(Graphics g){
    Graphics2D g2 = (Graphics2D)g;

    if(clicked){
      g2.setColor(Color.RED);
      g2.fill(new Rectangle(x+5, y+5, 30, 30));
    }
    if(isAlive)
      g2.drawImage(img, x, y, 40, 40, null);
    else if(!isAlive)
      g2.drawImage(img, x, y, 40, -40, null);
  }

  public void swim(){
    if(isAlive){
      if((this.y == goaly) && (goaly == maxy)){
        goaly = goaly-20;
      }
      else if((this.y == goaly) && (goaly == (maxy-20))){
        goaly = goaly + 20;
      }

      if((goaly == maxy) && ((counter % 3) == 0))
      y++;
      else if((goaly == (maxy-20) && ((counter % 3) == 0)))
      y--;
    }
    else if(!isAlive){
      y--;
    }

    counter++;
  }

  /*  @return String "jellyfish"
  */
  public String getName(){
    return "jellyfish";
  }

  /*  @return Rectangle Rectangle containing jellyfish image
  */
  public Rectangle getBounds(){
    return new Rectangle(x+5, y+5, 30, 30);
  }

  public void die(){
    super.die();
    this.isAlive = false;
  }
}
