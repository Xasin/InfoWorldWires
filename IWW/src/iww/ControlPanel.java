/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iww;

import iww.cellLogic.*;
/**
 *
 * @author xasin
 */
public class ControlPanel extends javax.swing.JPanel implements PositionCall {

    /**
     * Creates new form ControlPanel
     */
    private GameIterator gameLogic;
    private CellField pen;
    
    public ControlPanel(GameIterator logic) {
        initComponents();
        
        this.pen = new CellField();
        this.gameLogic = logic;

        typeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(gameLogic.getCellHandler().getTypeNames()));
    }
    
    public void click(int x, int y) {
        gameLogic.applyCellType(x, y, typeSelector.getSelectedIndex());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tickButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        runButton = new javax.swing.JToggleButton();
        tpsSlider = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        typeSelector = new javax.swing.JComboBox<>();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        tickButton.setText("Tick Once");
        tickButton.setToolTipText("Run one simulation tick");
        tickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickButtonActionPerformed(evt);
            }
        });
        jPanel1.add(tickButton);
        jPanel1.add(filler1);

        runButton.setText("Run Simulation");
        runButton.setToolTipText("Start/Stop running the simulation");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });
        jPanel1.add(runButton);

        tpsSlider.setPaintTicks(true);
        tpsSlider.setToolTipText("Simulation FPS");
        tpsSlider.setValue(3);
        tpsSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tpsSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpsSliderStateChanged(evt);
            }
        });
        jPanel1.add(tpsSlider);

        jTabbedPane1.addTab("Time Controls", jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        typeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeSelectorActionPerformed(evt);
            }
        });
        jPanel2.add(typeSelector);

        jTabbedPane1.addTab("Pen Options", jPanel2);

        add(jTabbedPane1);
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

    private void typeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeSelectorActionPerformed

    }//GEN-LAST:event_typeSelectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton runButton;
    private javax.swing.JButton tickButton;
    private javax.swing.JSlider tpsSlider;
    private javax.swing.JComboBox<String> typeSelector;
    // End of variables declaration//GEN-END:variables
}
