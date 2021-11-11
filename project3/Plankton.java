/*  @author Suhana Zeutzius
    purpose: a fish gotta eat
*/


import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Plankton extends Creature {
  private int bottom;
  public int x, y;
  private JFrame f;
  private int counter = 0;

  /*  @param JFrame f
      @param Random r
  */
  public Plankton(JFrame f, Random r){
    super(1, null, null);
    this.x = r.nextInt(f.getWidth() - 320);
    this.y = 0;
    this.f = f;
    this.bottom = 10 + r.nextInt(f.getHeight() - 20);
  }

  /*  @param Graphics g
  */
  public void paint(Graphics g){
    Graphics2D g2 = (Graphics2D)g;

    g2.setColor(Color.GREEN);
    g2.fill(new Ellipse2D.Double(x, y, 5, 5));
  }

  public void swim(){
    if(this.y < bottom)
      this.y++;
  }

  /*  @return String "plankton"
  */
  public String getName(){
    return "plankton";
  }

  /*  @return Rectangle Rectangle containing plankton image
  */
  public Rectangle getBounds(){
    return new Rectangle(x, y, 5, 5);
  }
}
