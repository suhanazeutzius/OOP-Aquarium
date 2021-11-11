/*  @author Suhana Zeutzius
    purpose: when pressed stops movement in the simulation but you can still add
    things and check stats 
*/
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class PauseButton implements ActionListener{
  private FishTank.AniThread t;
  private JButton pauseButton;
  private boolean isPaused = false;

  /*  @param FishTank.AniThread t
      @param JButton pause
  */
  public PauseButton(FishTank.AniThread t, JButton pause) {
    this.t = t;
    this.pauseButton = pause;
  }


  /*  @param ActionEvent e
  */
  public void actionPerformed(ActionEvent e) {
    t.switchMovement();
    if(!isPaused){
      pauseButton.setText("PLAY");
      isPaused = true;
    }
    if(isPaused)
      pauseButton.setText("PAUSE");
      isPaused = false;
  }

}
