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


package org.thema.mupcity;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.type.AttributeType;

/**
 * Set shapefile and fields associated with one predefined layer.
 * 
 * @author Gilles Vuidel
 */
public class SetLayerDialog extends javax.swing.JDialog {

    private Project project;
    private Class attr1, attr2;
    
    /** user has validated the dialog ? */
    public boolean isOk = false;
    /** the selected predefined layer */
    public LayerDef layer;
    /** the selected shapefile */
    public File file;
    /** the corresponding attributes if any */
    public List<String> attrs;
    
    /**
     * Creates new form SetLayerDialog
     * @param parent the parent frame
     * @param project the current project
     */
    public SetLayerDialog(java.awt.Frame parent, Project project) {
        super(parent, true);
        initComponents();

        this.project = project;
        layerComboBox.setModel(new DefaultComboBoxModel(Project.LAYERS.toArray()));
        layerComboBoxActionPerformed(null);
        setLocationRelativeTo(parent);
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });
    }

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        layerComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        selectFilePanel = new org.thema.common.swing.SelectFilePanel();
        attr1Label = new javax.swing.JLabel();
        attr1ComboBox = new javax.swing.JComboBox();
        attr2Label = new javax.swing.JLabel();
        attr2ComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Set layer...");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        layerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Layer");

        selectFilePanel.setDescription("Shapefile");
        selectFilePanel.setFileDesc("Shapefile");
        selectFilePanel.setFileExts(".shp");
        selectFilePanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFilePanelActionPerformed(evt);
            }
        });

        attr1Label.setText("Field1");

        attr2Label.setText("Field2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(layerComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(attr2Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(attr2ComboBox, 0, 157, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(attr1Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(attr1ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(52, 52, 52)))
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(selectFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 226, Short.MAX_VALUE)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(layerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(selectFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attr1Label)
                    .addComponent(attr1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attr2Label)
                    .addComponent(attr2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        layer = (LayerDef) layerComboBox.getSelectedItem();
        if(project.isLayerExist(layer.getLayer())) {
            int res = JOptionPane.showConfirmDialog(this, "This layer already exist. Do you want to replace it ?");
            if(res != JOptionPane.YES_OPTION) {
                return;
            }
        }
        file = selectFilePanel.getSelectedFile();
        attrs = new ArrayList<>();
        if(attr1 != null) {
            attrs.add((String)attr1ComboBox.getSelectedItem());
        }
        if(attr2 != null) {
            attrs.add((String)attr2ComboBox.getSelectedItem());
        }
        isOk = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void layerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerComboBoxActionPerformed
        layer = (LayerDef) layerComboBox.getSelectedItem();
        if(layer == null) {
            return;
        }

        attr1Label.setVisible(layer.getAttrNames().size() > 0);
        attr1ComboBox.setVisible(layer.getAttrNames().size() > 0);
        attr2Label.setVisible(layer.getAttrNames().size() > 1);
        attr2ComboBox.setVisible(layer.getAttrNames().size() > 1);
        attr1 = attr2 = null;
        
        if(layer.getAttrNames().isEmpty()) {
            return;
        }
        
        attr1Label.setText(layer.getAttrNames().get(0));
        attr1 = layer.getAttrClasses().get(0);
        if(layer.getAttrNames().size() < 2) {
            return;
        }
        attr2Label.setText(layer.getAttrNames().get(1));
        attr2 = layer.getAttrClasses().get(1);
    }//GEN-LAST:event_layerComboBoxActionPerformed

    private void selectFilePanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFilePanelActionPerformed
        if(attr1 != null) {
            attr1ComboBox.setModel(getAttributes(selectFilePanel.getSelectedFile(), attr1));
        }
        if(attr2 != null) {
            attr2ComboBox.setModel(getAttributes(selectFilePanel.getSelectedFile(), attr2));
        }
    }//GEN-LAST:event_selectFilePanelActionPerformed
    
    private DefaultComboBoxModel getAttributes(File file, Class cls) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            ShapefileDataStore dataStore = new ShapefileDataStore(file.toURI().toURL());
            List<AttributeType> attrs = dataStore.getSchema().getTypes();
            for(AttributeType attr : attrs) {
                if(!Geometry.class.isAssignableFrom(attr.getBinding()) && 
                        cls.isAssignableFrom(attr.getBinding())) {
                    model.addElement(attr.getName().getLocalPart());
                }
            }

            dataStore.dispose();
            
        } catch(IOException e) {
            Logger.getLogger(SetLayerDialog.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "An error occured while loading shapefile :\n" + e.getLocalizedMessage());
        }
        return model;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox attr1ComboBox;
    private javax.swing.JLabel attr1Label;
    private javax.swing.JComboBox attr2ComboBox;
    private javax.swing.JLabel attr2Label;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox layerComboBox;
    private javax.swing.JButton okButton;
    private org.thema.common.swing.SelectFilePanel selectFilePanel;
    // End of variables declaration//GEN-END:variables

}
