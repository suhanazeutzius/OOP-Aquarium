/*  @author Suhana Zeutzius
    purpose: drops a specified number of plantons into the tank
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class MakePlankton extends Thread{
  private Random r;
  private JTextField tf;
  private JFrame f;

  /*  @param Random r
      @param JTextField tf
      @param JFrame f
  */
  public MakePlankton(Random r, JTextField tf, JFrame f){
    this.r = r;
    this.tf = tf;
    this.f = f;
  }

  /*  @param Random r
      @param JTextField tf
      @param JFrame f
  */
  public void drop(Random r, JTextField tf, JFrame f){
    int numPlankton = 0;
    try{
      numPlankton = Integer.parseInt(tf.getText());
      if(numPlankton<0){
        tf.setText("pos num");
      }
      else{
        for(int i = 0; i<numPlankton; i++){
          FishTank.addCreature(r, "plankton", f);
        }
      }
    }catch(NumberFormatException e){
      tf.setText("pos num");
    }catch(Exception e){
      tf.setText("pos num");
    }
  }

  public void run(){
    drop(r, tf, f);
  }

}
