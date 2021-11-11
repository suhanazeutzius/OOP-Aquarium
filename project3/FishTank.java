/*  @author Suhana Zeutzius
    purpose: create the whole fish tank and control panel, file that runs it all
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class FishTank {
  private static ArrayList<Creature> allCreatures;
  //fish controls
  private static JTextField fHealth;
  private static JSlider fMet;
  private static JSlider fSpeed;
  //jellyfish controls
  private static JTextField jHealth;
  private static JSlider jMet;
  private static JSlider jKill;
  //shark controls
  private static JTextField sHealth;
  private static JSlider sMet;
  private static JSlider sSpeed;
  //start/stop button
  private static JButton pause;
  //textfields of selected fishes
  private static JTextField selSpecies;
  private static JTextField selHealth;
  private static JTextField selSpeed;
  private static JLabel speedOrKill;

  /*  @param JFrame aq
      @param Random r
      @return ArrayList<Creature> initializes the list of all the creatures in the tank
  */
  public static ArrayList<Creature> init(JFrame aq, Random r){
    ArrayList<Creature> allFish = new ArrayList<Creature>();
    for(int i = 0; i<1; i++)
      allFish.add(new Jellyfish(Integer.parseInt(jHealth.getText()), jKill, jMet, r, aq));
    for(int i = 0; i<2; i++)
      allFish.add(new Shark(Integer.parseInt(sHealth.getText()), sSpeed, sMet, r, aq));
    for(int i = 0; i<1; i++)
      allFish.add(new Fish1(Integer.parseInt(fHealth.getText()), fSpeed, fMet, r, aq));
    for(int i = 0; i<5; i++)
      allFish.add(new Plankton(aq, r));

    return allFish;
  }

  /*  @param Random r
      @param String s Type of creature to add
      @param JFrame aq
      purpose: add a creature to the list of everything in the tank
  */
  public static void addCreature(Random r, String s, JFrame aq){
    if(s.equals("shark")){
      if(Integer.parseInt(sHealth.getText()) <= 0)
        sHealth.setText("pos num");
      else
        allCreatures.add(new Shark(Integer.parseInt(sHealth.getText()), sSpeed, sMet, r, aq));
    }
    else if(s.equals("fish")){
      if(Integer.parseInt(fHealth.getText()) <= 0)
        fHealth.setText("pos num");
      else
        allCreatures.add(new Fish1(Integer.parseInt(fHealth.getText()), fSpeed, fMet, r, aq));
    }
    else if(s.equals("jellyfish")){
      if(Integer.parseInt(jHealth.getText()) <= 0)
        jHealth.setText("pos num");
      else
        allCreatures.add(new Jellyfish(Integer.parseInt(jHealth.getText()), jKill, jMet, r, aq));
    }
    else if(s.equals("plankton"))
      allCreatures.add(new Plankton(aq, r));
  }

  /*  @param String name Type of creature
      @param int h Health
      @param int s Speed(or deadliness)
      purpose: updates the stats panel for whatever was last clicked
  */
  public static void clickStats(String name, int h, int s){
    selSpecies.setText(name);
    if(name.equals("jellyfish"))
      speedOrKill.setText("Deadliness:");
    else
      speedOrKill.setText("Speed:");

    if(h!=0){
      selHealth.setText(h + "");
      selSpeed.setText(s + "");
    }
    else{
      selHealth.setText("dead :(");
      selSpeed.setText("0");
    }
  }

  //A thread that runs the aquarium and updates the stats and everything
  //every 20ms checks if anything was eaten, updates positions, repaints frame
  public static class AniThread extends Thread {
    private MakeAquarium makeAq;
    private boolean swimming = true;
    public AniThread(MakeAquarium m) {
      makeAq = m;
    }

    public void switchMovement(){
      if(this.swimming == true)
        this.swimming =  false;
      else if(this.swimming == false)
        this.swimming = true;
    }

    public void run() {
      while( true ) {
        try {
          Thread.sleep(20);
        } catch (Exception e) {}
        makeAq.eat();
        if(swimming)
          makeAq.swim();
        makeAq.repaint();
      }
    }
  }

  /*  @param Random r
      @param JFrame aq
      @return JPanel the panel with all the controls of the fishtank
  */
  public static JPanel controlPanel(Random r, JFrame aq){
    //line of stars by FishTank variables
    JPanel controls = new JPanel(new FlowLayout());

    //plankton part of the control panel
    JPanel plankton = new JPanel(new FlowLayout());
    JTextField numPlankton = new JTextField(5);
    numPlankton.setText("10");
    JButton feed = new JButton("Feed");
    feed.addActionListener(new Feed(r, numPlankton, aq));

    plankton.add(numPlankton, BorderLayout.WEST);
    plankton.add(feed, BorderLayout.EAST);

    //Fish1 part of the control panel
    JPanel fish1 = new JPanel(new FlowLayout());
    JPanel fishStats = new JPanel();
    fishStats.setPreferredSize(new Dimension(120, 120));
    fishStats.setLayout(new BoxLayout(fishStats, BoxLayout.Y_AXIS));
    fHealth = new JTextField(5); //*******************************************
    fHealth.setText("3");
    JButton addFish = new JButton("Add fish");
    addFish.addActionListener(new AddButton(r, aq, "fish"));
    addFish.setPreferredSize(new Dimension(130, 40));
    fMet = new JSlider(1, 10); //***********************************************
    fMet.setMinorTickSpacing(1);
    fMet.setPaintTicks(true);
    fMet.setValue(1);
    fSpeed = new JSlider(1, 5); //*********************************************
    fSpeed.setMinorTickSpacing(1);
    fSpeed.setPaintTicks(true);
    fSpeed.setValue(1);

    fishStats.add(new JLabel("Health"));
    fishStats.add(fHealth);
    fishStats.add(new JLabel("Speed"));
    fishStats.add(fSpeed);
    fishStats.add(new JLabel("Metabolism"));
    fishStats.add(fMet);

    fish1.add(fishStats, BorderLayout.WEST);
    fish1.add(addFish, BorderLayout.EAST);

    //Jellyfish part of the panel!
    JPanel jelly = new JPanel(new FlowLayout());
    JPanel jellyStats = new JPanel();
    jellyStats.setPreferredSize(new Dimension(120, 120));
    jellyStats.setLayout(new BoxLayout(jellyStats, BoxLayout.Y_AXIS));
    jHealth = new JTextField(5); //********************************************
    jHealth.setText("10");
    JButton addJelly = new JButton("Add jellyfish");
    addJelly.addActionListener(new AddButton(r, aq, "jellyfish"));
    addJelly.setPreferredSize(new Dimension(130, 40));
    jMet = new JSlider(1, 10); //**********************************************
    jMet.setMinorTickSpacing(1);
    jMet.setPaintTicks(true);
    jMet.setValue(1);
    jKill = new JSlider(1, 3);//**********************************************
    jKill.setMinorTickSpacing(1);
    jKill.setPaintTicks(true);
    jKill.setValue(1);

    jellyStats.add(new JLabel("Health"));
    jellyStats.add(jHealth);
    jellyStats.add(new JLabel("kill factor"));
    jellyStats.add(jKill);
    jellyStats.add(new JLabel("Metabolism"));
    jellyStats.add(jMet);

    jelly.add(jellyStats, BorderLayout.WEST);
    jelly.add(addJelly, BorderLayout.EAST);

    //Shark part of the panel!
    JPanel shark = new JPanel(new FlowLayout());
    JPanel sharkStats = new JPanel();
    sharkStats.setPreferredSize(new Dimension(120, 120));
    sharkStats.setLayout(new BoxLayout(sharkStats, BoxLayout.Y_AXIS));
    sHealth = new JTextField(5);//*********************************************
    sHealth.setText("10");
    JButton addShark = new JButton("Add shark");
    addShark.addActionListener(new AddButton(r, aq, "shark"));
    addShark.setPreferredSize(new Dimension(130, 40));
    sMet = new JSlider(1, 10); //**********************************************
    sMet.setMinorTickSpacing(1);
    sMet.setPaintTicks(true);
    sMet.setValue(1);
    sSpeed = new JSlider(1, 5); //***********************************************
    sSpeed.setMinorTickSpacing(1);
    sSpeed.setPaintTicks(true);
    sSpeed.setValue(1);

    sharkStats.add(new JLabel("Health"));
    sharkStats.add(sHealth);
    sharkStats.add(new JLabel("Speed"));
    sharkStats.add(sSpeed);
    sharkStats.add(new JLabel("Metabolism"));
    sharkStats.add(sMet);

    shark.add(sharkStats, BorderLayout.WEST);
    shark.add(addShark, BorderLayout.EAST);

    pause = new JButton("PAUSE"); //*******************************************

    //slected creature stats
    JPanel selLabels = new JPanel();
    selLabels.setPreferredSize(new Dimension(300, 100));
    selLabels.setLayout(new BoxLayout(selLabels, BoxLayout.Y_AXIS));

    JPanel selStats = new JPanel();
    selStats.setPreferredSize(new Dimension(300, 100));
    selStats.setLayout(new BoxLayout(selStats, BoxLayout.Y_AXIS));

    selSpecies = new JTextField(5); //****************************************
    selSpecies.setText("-");
    selSpecies.setEditable(false);
    selHealth = new JTextField(5);//********************************************
    selHealth.setText("-");
    selHealth.setEditable(false);
    selSpeed = new JTextField(5);//*******************************************
    selSpeed.setText("-");
    selSpeed.setEditable(false);

    JPanel selSpeciesPan = new JPanel(new FlowLayout());
    selSpeciesPan.add(new JLabel("Species:"));
    selSpeciesPan.add(selSpecies);

    JPanel selHealthPan = new JPanel(new FlowLayout());
    selHealthPan.add(new JLabel("Health:"));
    selHealthPan.add(selHealth);

    JPanel selSpeedPan = new JPanel(new FlowLayout());
    speedOrKill = new JLabel("Speed:");//*************************************
    selSpeedPan.add(speedOrKill);
    selSpeedPan.add(selSpeed);

    //pauseInfo.add(pause, BorderLayout.NORTH);
    selLabels.add(new JLabel("Selected creature information:"));
    selLabels.add(selSpeciesPan);
    selLabels.add(selHealthPan);
    selLabels.add(selSpeedPan);


    controls.add(new JLabel("Add plankton:"));
    controls.add(plankton);
    controls.add(fish1);
    controls.add(jelly);
    controls.add(shark);
    controls.add(pause);
    controls.add(selLabels);

    return controls;
  }

  public static void main(String[] args){
    Random r = new Random();
    JFrame aq = new JFrame();
    //aq.setBackground(Color.BLUE);
    aq.setPreferredSize(new Dimension(912, 437));
    aq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    aq.pack();
    aq.setVisible(true);


    JPanel controls = controlPanel(r, aq);
    controls.setPreferredSize(new Dimension(300, 600));
    JScrollPane scroll = new JScrollPane(controls);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setBounds(0, 0, 300, 437);

    JPanel scrollAndCont = new JPanel(null);
    scrollAndCont.setPreferredSize(new Dimension(300, 500));
    scrollAndCont.add(scroll);

    allCreatures = init(aq, r);
    //this is already a JPanel
    MakeAquarium f = new MakeAquarium(allCreatures);

    AniThread t = new AniThread(f);
    pause.addActionListener(new PauseButton(t, pause)); //******************
    t.start();

    aq.add(scrollAndCont, BorderLayout.WEST);

    aq.add(f, BorderLayout.CENTER);
    aq.pack();
  }
}
