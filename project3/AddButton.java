/*  @author Suhana Zeutzius
    purpose: Button that adds a creature of a given type to the arraylist
*/
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddButton implements ActionListener{
  private Random r;
  private String s;
  private JFrame f;
  //private AddCreature a;

  /*  @param Random r
      @param JFrame f
      @param String s Name of creature type to add
  */
  public AddButton(Random r, JFrame f, String s) {
    this.r = r;
    this.f = f;
    this.s = s;
  }

  /*  @param ActionEvent e
  */
  public void actionPerformed(ActionEvent e) {
    FishTank.addCreature(r, s, f);
  }

}
