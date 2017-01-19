/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iww;

import iww.cellLogic.GameIterator;

/**
 *
 * @author xasin
 */
public class ControlPanel extends javax.swing.JPanel {

    /**
     * Creates new form ControlPanel
     */
    private GameIterator gameLogic;
    
    public ControlPanel(GameIterator logic) {
        initComponents();
        
        this.gameLogic = logic;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tickButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        runButton = new javax.swing.JToggleButton();
        tpsSlider = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));
        setLayout(new java.awt.GridLayout(2, 2, 3, 3));

        tickButton.setText("Tick Once");
        tickButton.setToolTipText("Run one simulation tick");
        tickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickButtonActionPerformed(evt);
            }
        });
        add(tickButton);
        add(filler1);

        runButton.setText("Run Simulation");
        runButton.setToolTipText("Start/Stop running the simulation");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });
        add(runButton);

        tpsSlider.setPaintTicks(true);
        tpsSlider.setToolTipText("Simulation FPS");
        tpsSlider.setValue(3);
        tpsSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tpsSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpsSliderStateChanged(evt);
            }
        });
        add(tpsSlider);
    }// </editor-fold>//GEN-END:initComponents

    private void tickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tickButtonActionPerformed
        gameLogic.tick();
    }//GEN-LAST:event_tickButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        gameLogic.doNTicks(runButton.isSelected() ? -1 : 0);
    }//GEN-LAST:event_runButtonActionPerformed

    private void tpsSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpsSliderStateChanged
        gameLogic.setTickrate(tpsSlider.getValue());
    }//GEN-LAST:event_tpsSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JToggleButton runButton;
    private javax.swing.JButton tickButton;
    private javax.swing.JSlider tpsSlider;
    // End of variables declaration//GEN-END:variables
}
