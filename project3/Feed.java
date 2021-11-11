/*  @author Suhana Zeutzius
    purpose: feed button drops plankton into the fish tank
*/
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Feed implements ActionListener{
  private JTextField tf;
  private Random r;
  private MakePlankton p;
  private JFrame f;

  /*  @param JLabel label
      @param JTextField tf
  */
  public Feed(Random r, JTextField num, JFrame f) {
    this.r = r;
    this.tf = num;
    this.f = f;
    this.p = new MakePlankton(r, tf, f);
  }


  /*  @param ActionEvent e
  */
  public void actionPerformed(ActionEvent e) {
    if(!p.isAlive()){
      p = new MakePlankton(r, tf, f);
      p.start();
    }
  }

}
