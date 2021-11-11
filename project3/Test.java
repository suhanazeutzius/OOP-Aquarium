/*  @author Suhana Zeutzius
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class Test {
  // private static ArrayList<Creature> allCreatures;
  //
  // public static ArrayList<Creature> init(JFrame aq, Random r){
  //   ArrayList<Creature> allFish = new ArrayList<Creature>();
  //   allFish.add(new Fish1(10, 4, r, aq));
  //   allFish.add(new Jellyfish(10, 1, r, aq));
  //   return allFish;
  // }
  //
  // public static void addCreature(Creature c){
  //   allCreatures.add(c);
  // }
  // 
  // public static class AniThread extends Thread {
  //   private MakeAquarium makeAq;
  //   public AniThread(MakeAquarium m) {
  //     makeAq = m;
  //   }
  //
  //   public void run() {
  //     while( true ) {
  //       try {
  //         Thread.sleep(20);
  //       } catch (Exception e) {}
  //       makeAq.eat();
  //       makeAq.swim();
  //       makeAq.repaint();
  //     }
  //   }
  // }

  public static void main(String... args) {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    for (int i = 0; i < 10; i++) {
        panel.add(new JButton("Hello-" + i));
    }
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);
    JPanel contentPane = new JPanel(null);
    contentPane.setPreferredSize(new Dimension(500, 400));
    contentPane.add(scrollPane);
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
  }
}
