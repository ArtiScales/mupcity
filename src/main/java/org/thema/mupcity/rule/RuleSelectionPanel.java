/*
 * Copyright (C) 2015 Laboratoire ThéMA - UMR 6049 - CNRS / Université de Franche-Comté
 * http://thema.univ-fcomte.fr
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package org.thema.mupcity.rule;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.thema.common.collection.HashMap2D;
import org.thema.common.param.XMLObject;
import org.thema.mupcity.AHP;
import org.thema.mupcity.AHPDialog;
import org.thema.mupcity.Project;
import org.thema.mupcity.scenario.Scenario;

/**
 * Panel for selecting rules and configure rule weights.
 * 
 * @author Gilles Vuidel
 */
public class RuleSelectionPanel extends javax.swing.JPanel {

    private Project project;
    private AHP ahp;
    
    /**
     * Creates a new RuleSelectionPanel with default rules.
     * Method {@link #setProject} must be called before using this panel.
     * 
     */
    public RuleSelectionPanel() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // pour la phase de développement dans NetBeans
        for(Rule rule : Project.RULES) {
            model.addRow(new Object[]{rule, true, 1.0});
        }
    }

    /**
     * Sets the current project and update the rule table depending on rules that are usable with this project.
     * @param project the current project
     */
    public void setProject(Project project) {
        this.project = project;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Rule rule : project.getRules()) {
            if(rule.isUsable(project)) {
                model.addRow(new Object[]{rule, true, 1.0});
            }
        }
    }

    private void setCoefRules(Map<String, Double> coefRules) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setNumRows(0);
        for(Rule rule : project.getRules()) {
            if(rule.isUsable(project)) {
                if(coefRules.containsKey(rule.getName())) {
                    model.addRow(new Object[]{rule, true, coefRules.get(rule.getName())});
                } else {
                    model.addRow(new Object[]{rule, false, 0.0});       
                }
            } 
        }
        meanCheckBoxActionPerformed(null);
    }
    
    /**
     * @return the selected rules name with their weight.
     */
    public Map<String, Double> getCoefRules() {
       Map<String, Double> coefRules = new LinkedHashMap<>();
       DefaultTableModel model = (DefaultTableModel) table.getModel();
       for(int i = 0; i < model.getRowCount(); i++) {
           if((Boolean)model.getValueAt(i, 1)) {
               coefRules.put(((Rule)model.getValueAt(i, 0)).getName(), (Double)model.getValueAt(i, 2));
           }
       }
       return coefRules;
    }

    /**
     * @return the AHP matrix
     */
    public AHP getAHP() {
        updateAHP();
        return ahp;
    }
    
    /**
     * @return true if agregation is average, false for Yager agregation
     */
    public boolean isAgregMean() {
        return meanCheckBox.isSelected();      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        ahpButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        meanCheckBox = new javax.swing.JCheckBox();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rule", "Enabled", "Coef"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/thema/mupcity/rule/Bundle"); // NOI18N
        ahpButton.setText(bundle.getString("RuleSelectionPanel.ahpButton.text")); // NOI18N
        ahpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ahpButtonActionPerformed(evt);
            }
        });

        importButton.setText(bundle.getString("RuleSelectionPanel.importButton.text")); // NOI18N
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        meanCheckBox.setText(bundle.getString("RuleSelectionPanel.meanCheckBox.text")); // NOI18N
        meanCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meanCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(importButton)
                .addGap(18, 18, 18)
                .addComponent(meanCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(ahpButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ahpButton)
                    .addComponent(importButton)
                    .addComponent(meanCheckBox)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateAHP() {
        Set<String> rules = getCoefRules().keySet();
        if(ahp == null || !ahp.getMatrix().getKeys1().containsAll(rules)) {
            ahp = new AHP(new ArrayList<>(rules));
        } else if(!rules.containsAll(ahp.getMatrix().getKeys1())) {
            HashSet<String> set = new HashSet<>(ahp.getMatrix().getKeys1());
            set.removeAll(rules);
            HashMap2D<String, String, String> matrix = ahp.getMatrix();
            for(String rule : set)  {
                matrix.removeKey1(rule);
                matrix.removeKey2(rule);
            } 
            ahp = new AHP(new ArrayList<>(rules));
            ahp.setMatrix(matrix);
        }
        for(String rule : rules) {
            ahp.setCoef(rule, getCoefRules().get(rule));
        }
    }
    
    private void ahpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ahpButtonActionPerformed
        updateAHP();
        AHPDialog dlg = new AHPDialog(null, ahp);
        dlg.setVisible(true);
        if(!dlg.isOk) {
            return;
        }
        ahp = dlg.ahp;
        setCoefRules(ahp.getCoefs());
    }//GEN-LAST:event_ahpButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        List<Scenario> scenarios = new ArrayList<Scenario>(project.getScenarioAutos());
        scenarios.addAll(project.getScenarios());
        if(scenarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No scenarios");
            return;
        }
        Scenario sel = (Scenario) JOptionPane.showInputDialog(this, "Select scenario :", "Import parameters", 
                JOptionPane.PLAIN_MESSAGE, null, scenarios.toArray(), null);
        if(sel == null) {
            return;
        }
        ahp = XMLObject.dupplicate(sel.getAHP());
        setCoefRules(ahp.getCoefs());
        
    }//GEN-LAST:event_importButtonActionPerformed

    private void meanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meanCheckBoxActionPerformed
        double sum = 0;
        int n = 0;
        for(Double val : getCoefRules().values()) {
            sum += val;
            n++;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i = 0; i < model.getRowCount(); i++) {
            if((Boolean)model.getValueAt(i, 1)) {
               double val = (Double)model.getValueAt(i, 2);
               if(meanCheckBox.isSelected()) {
                   model.setValueAt(val/sum, i, 2);
               } else {
                   model.setValueAt(n*val/sum, i, 2);
               }
            }
        }
        
    }//GEN-LAST:event_meanCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ahpButton;
    private javax.swing.JButton importButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox meanCheckBox;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
