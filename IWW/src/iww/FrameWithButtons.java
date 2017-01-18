package iww;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**    Test für das Schaffen vieler angeordneter Buttons
  */

public class FrameWithButtons extends JFrame {
  private final int sizeX = 10, sizeY = 10;
    
    
  private Button[][] gameField;
  private GameIterator gameLogic;
  
  private JButton tick;
  
  
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
    
    gameField = new Button[sizeX][sizeY];
    
    // Anfang Komponenten
    for (x=0;x<sizeX;x++) {
      for (y=0;y<sizeY;y++) {
        gameField[x][y] = new Button(x, y, gameLogic, cp);
      }
    } 
    
    tick = new JButton();
    tick.setBounds(220, 10, 50, 30);
    tick.setText("Tick Once");
    tick.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent something) {
            gameLogic.tick();
            
            for (int x=0;x<sizeX;x++)
                for (int y=0;y<sizeY;y++)
                    gameField[x][y].redoColor();
        }
    });
    
    cp.add(tick);
    
    // Ende Komponenten
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new FrameWithButtons("Test Buttons");
  }
}