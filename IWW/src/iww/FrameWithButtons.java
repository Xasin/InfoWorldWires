import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**    Test für das Schaffen vieler angeordneter Buttons
  */

public class FrameWithButtons extends JFrame {
  // Anfang Attribute
  private Button[][] newButtons = new Button[20][10];  // Speicherverwaltung für die Buttons schaffen
  // Ende Attribute
  
  public FrameWithButtons(String title) { 
    // Frame-Initialisierung
    super(title);              // 
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
    // Anfang Komponenten
    for (int j=0;j<10;j++) {
      for (int i=0;i<20;i++) {
        newButtons[i][j] = new Button(i,j,randomInt(0,9),cp);
      }
    } 
    // Ende Komponenten
    setVisible(true);
  }
   
  // Anfang Methoden
  public static int randomInt(int low, int high) {
    return (int) (Math.random() * (++high - low) + low);
  }
  // Ende Methoden
  
  public static void main(String[] args) {
    new FrameWithButtons("Test Buttons");
  }
}