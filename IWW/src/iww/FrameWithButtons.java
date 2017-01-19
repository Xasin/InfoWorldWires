package iww;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

/**    Test für das Schaffen vieler angeordneter Buttons
  */

public class FrameWithButtons extends JFrame {
  private final int sizeX = 20, sizeY = 20;
  
  private GameIterator gameLogic;
  private GameWindow gameWindow;
  
  private JButton tick, run;
  private JSlider tickrateSelector;
  
  private boolean doTicks = false;
  
  
  public FrameWithButtons(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 500; 
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x-100, y-100);
    
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    
    gameLogic = new GameIterator(sizeX, sizeY, new CellHandler());
    gameLogic.setTickrate(50);
 
    gameWindow = new GameWindow(gameLogic);
    gameWindow.setBounds(10, 10, 200, 200);
    
    TitledBorder gameBorder = new TitledBorder("Simulation:");
    gameWindow.setBorder(gameBorder);
    
    cp.add(gameWindow);
    
    tick = new JButton();
    tick.setBounds(220, 10, 100, 30);
    tick.setText("Tick");
    tick.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent something) {
            gameLogic.tick();
        }
    });
    run = new JButton();
    run.setBounds(220, 40, 100, 30);
    run.setText("Run");
    run.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent something) {
            doTicks ^= true;
            if(doTicks)
                run.setText("Stop");
            else
                run.setText("Run");
            
            gameLogic.doNTicks((doTicks) ? -1 : 0);
        }
    });
    
    cp.add(tick);
    cp.add(run);
    
    tickrateSelector = new JSlider(0, 100);
    tickrateSelector.setBounds(330, 40, 100, 30);
    tickrateSelector.setMajorTickSpacing(10);
    tickrateSelector.setPaintTicks(true);
    tickrateSelector.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent evt) {
            gameLogic.setTickrate(tickrateSelector.getValue());
        }
    });
    
    cp.add(tickrateSelector);
    
    
    
    // Ende Komponenten
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new FrameWithButtons("Test Buttons");
  }
}