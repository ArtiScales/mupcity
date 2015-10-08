
package org.thema.mupcity.rule;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    
    public RuleSelectionPanel() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // pour la phase de développement dans NetBeans
        for(Rule rule : Project.RULES) {
            model.addRow(new Object[]{rule, true, 1.0});
        }
    }
    
    public RuleSelectionPanel(Project project, Map<String, Double> coefRules) {
        this.project = project;
        initComponents();
        setCoefRules(coefRules);
    }

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
                    model.addRow(new Object[]{rule, false, Double.NaN});       
                }
            }
        }
    }
    
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

    public AHP getAHP() {
        updateAHP();
        return ahp;
    }
    
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
            for(String rule : set)  {
                ahp.getMatrix().removeKey1(rule);
                ahp.getMatrix().removeKey2(rule);
            } 
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ahpButton;
    private javax.swing.JButton importButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox meanCheckBox;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
