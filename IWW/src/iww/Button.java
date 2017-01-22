package iww;

import iww.cellLogic.GameIterator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Button extends JButton{

  private final int posX, posY;
  private GameIterator gameLogic;
  private PositionCall onButtonPress;
  
  public Button(int x, int y, GameIterator game, PositionCall btnPressAction) {
    this.posX = x;
    this.posY = y;
    
    this.gameLogic = game;
    this.onButtonPress = btnPressAction;
    
    setBackground(gameLogic.getCellColorAt(posX, posY));

    addActionListener((ActionEvent evt) -> onButtonPress.click(posX, posY));    
  }
  
  public void redoColor() {
    setBackground(gameLogic.getCellColorAt(posX, posY));
  } 
} 