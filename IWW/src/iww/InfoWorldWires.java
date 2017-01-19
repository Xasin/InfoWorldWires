package iww;

import iww.cellLogic.GameIterator;
import iww.cellLogic.CellHandler;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

/**    Test für das Schaffen vieler angeordneter Buttons
  */

public class InfoWorldWires extends JFrame {
  private final int sizeX = 49, sizeY = 15;
  
  private GameIterator gameLogic;
  private GameWindow gameWindow;
 
  private ControlPanel controlPanel;
  
  public InfoWorldWires(String title) { 
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
    gameWindow.setBounds(5, 5, 490, 150);
    
    cp.add(gameWindow);
    
    controlPanel = new ControlPanel(gameLogic);
    controlPanel.setBounds(5, 160, 490, 100);
    
    cp.add(controlPanel);
    
    // Ende Komponenten
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new InfoWorldWires("InfoWorldWires Simulator");
  }
}