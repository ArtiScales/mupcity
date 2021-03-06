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


package org.thema.mupcity.evaluation;

import java.awt.Frame;
import java.awt.image.DataBuffer;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.precision.GeometryPrecisionReducer;
import org.thema.common.parallel.BufferForkJoinTask;
import org.thema.common.swing.TaskMonitor;
import org.thema.data.GlobalDataStore;
import org.thema.data.feature.DefaultFeature;
import org.thema.data.feature.DefaultFeatureCoverage;
import org.thema.data.feature.Feature;
import org.thema.data.feature.FeatureCoverage;
import org.thema.data.feature.FeatureFilter;
import org.thema.drawshape.layer.RasterLayer;
import org.thema.drawshape.ui.MapInternalFrame;
import org.thema.graph.SpatialGraph;
import org.thema.msca.Cell;
import org.thema.msca.GridFeatureCoverage;
import org.thema.msca.GridGroupLayer;
import org.thema.msca.MSGridBuilder;
import org.thema.msca.SquareGrid;
import org.thema.msca.SquareGridExtent;
import org.thema.msca.operation.AbstractLayerOperation;
import org.thema.msca.operation.MeanOperation;
import org.thema.msca.operation.SimpleCoverageOperation;
import org.thema.mupcity.MainFrame;
import org.thema.mupcity.Project;
import org.thema.mupcity.operation.YagerAgregOperation;
import org.thema.mupcity.scenario.Scenario;

/**
 * Dialog form for launching ex post evaluations.
 * 
 * @author Gilles Vuidel, Florian Litot
 */
public class EvaluationDialog extends javax.swing.JDialog {

    private Project project;

    /**
     * Creates new form EvaluationDialog.
     * @param parent the parent frame
     * @param project the current project
     */
    public EvaluationDialog(java.awt.Frame parent, Project project) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        getRootPane().setDefaultButton(okButton);
        this.project = project;
        scenarioComboBox.setModel(new DefaultComboBoxModel(project.getScenarioAutos().toArray()));
        evalSelectionPanel.setProject(project);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup1 = new javax.swing.ButtonGroup();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        buildResidselectFilePanel = new org.thema.common.swing.SelectFilePanel();
        buildTotselectFilePanel = new org.thema.common.swing.SelectFilePanel();
        seuilSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        netN1SelectFilePanel = new org.thema.common.swing.SelectFilePanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        extScenarioSelectFilePanel = new org.thema.common.swing.SelectFilePanel();
        evalParamButton = new javax.swing.JButton();
        scenarioRadioButton = new javax.swing.JRadioButton();
        scenarioComboBox = new javax.swing.JComboBox();
        externScenarioRadioButton = new javax.swing.JRadioButton();
        evalSelectionPanel = new org.thema.mupcity.evaluation.EvaluatorSelectionPanel();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/thema/mupcity/evaluation/Bundle"); // NOI18N
        setTitle(bundle.getString("EvaluationDialog.title_1")); // NOI18N

        okButton.setText(bundle.getString("EvaluationDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(bundle.getString("EvaluationDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        buildResidselectFilePanel.setDescription(bundle.getString("EvaluationDialog.buildResidselectFilePanel.description")); // NOI18N
        buildResidselectFilePanel.setFileDesc(bundle.getString("EvaluationDialog.buildResidselectFilePanel.fileDesc")); // NOI18N
        buildResidselectFilePanel.setFileExts(bundle.getString("EvaluationDialog.buildResidselectFilePanel.fileExts")); // NOI18N

        buildTotselectFilePanel.setDescription(bundle.getString("EvaluationDialog.buildTotselectFilePanel.description")); // NOI18N
        buildTotselectFilePanel.setFileDesc(bundle.getString("EvaluationDialog.buildTotselectFilePanel.fileDesc")); // NOI18N
        buildTotselectFilePanel.setFileExts(bundle.getString("EvaluationDialog.buildTotselectFilePanel.fileExts")); // NOI18N

        jLabel1.setText(bundle.getString("EvaluationDialog.jLabel1.text")); // NOI18N

        netN1SelectFilePanel.setDescription(bundle.getString("EvaluationDialog.netN1SelectFilePanel.description")); // NOI18N
        netN1SelectFilePanel.setFileDesc(bundle.getString("EvaluationDialog.netN1SelectFilePanel.fileDesc")); // NOI18N
        netN1SelectFilePanel.setFileExts(bundle.getString("EvaluationDialog.netN1SelectFilePanel.fileExts")); // NOI18N

        jLabel4.setText(bundle.getString("EvaluationDialog.jLabel4.text")); // NOI18N

        jLabel6.setText(bundle.getString("EvaluationDialog.jLabel6.text")); // NOI18N

        extScenarioSelectFilePanel.setDescription(bundle.getString("EvaluationDialog.extScenarioSelectFilePanel.description")); // NOI18N
        extScenarioSelectFilePanel.setFileDesc(bundle.getString("EvaluationDialog.extScenarioSelectFilePanel.fileDesc")); // NOI18N
        extScenarioSelectFilePanel.setFileExts(bundle.getString("EvaluationDialog.extScenarioSelectFilePanel.fileExts")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, externScenarioRadioButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), extScenarioSelectFilePanel, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        evalParamButton.setText(bundle.getString("EvaluationDialog.evalParamButton.text")); // NOI18N
        evalParamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evalParamButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(scenarioRadioButton);
        scenarioRadioButton.setSelected(true);
        scenarioRadioButton.setText(bundle.getString("EvaluationDialog.scenarioRadioButton.text")); // NOI18N

        scenarioComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, scenarioRadioButton, org.jdesktop.beansbinding.ELProperty.create("${selected}"), scenarioComboBox, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        buttonGroup1.add(externScenarioRadioButton);
        externScenarioRadioButton.setText(bundle.getString("EvaluationDialog.externScenarioRadioButton.text")); // NOI18N

        jLabel5.setText(bundle.getString("EvaluationDialog.jLabel5.text")); // NOI18N

        nameTextField.setText(bundle.getString("EvaluationDialog.nameTextField.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(nameTextField))
                            .add(buildResidselectFilePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(netN1SelectFilePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(buildTotselectFilePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(evalParamButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(evalSelectionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(scenarioRadioButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(scenarioComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(externScenarioRadioButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(extScenarioSelectFilePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(seuilSpinner))
                            .add(layout.createSequentialGroup()
                                .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(3, 3, 3)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(buildResidselectFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                .add(6, 6, 6)
                .add(netN1SelectFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(1, 1, 1)
                .add(buildTotselectFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(seuilSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11)
                .add(evalParamButton)
                .add(18, 18, 18)
                .add(evalSelectionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(scenarioRadioButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 1, Short.MAX_VALUE)
                        .add(scenarioComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(externScenarioRadioButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(extScenarioSelectFilePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(okButton))
                .add(24, 24, 24))
        );

        externScenarioRadioButton.getAccessibleContext().setAccessibleName(bundle.getString("EvaluationDialog.externScenarioRadioButton.AccessibleContext.accessibleName")); // NOI18N
        externScenarioRadioButton.getAccessibleContext().setAccessibleDescription(bundle.getString("EvaluationDialog.externScenarioRadioButton.AccessibleContext.accessibleDescription")); // NOI18N

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    calcEvaluation();
                    setVisible(false);
                    dispose();
                } catch (IOException ex) {
                    Logger.getLogger(EvaluationDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(EvaluationDialog.this, "Error : " + ex);
                }
            }
        }).start();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void evalParamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evalParamButtonActionPerformed
        EvalParamDialog evalParamDialog = new EvalParamDialog((Frame)this.getParent(), project.getEvaluators());
        evalParamDialog.setVisible(true);
    }//GEN-LAST:event_evalParamButtonActionPerformed


    private void calcEvaluation() throws IOException {
        final TaskMonitor monitor = new TaskMonitor(getParent(), "Evaluation...", "", 0, 10);
        monitor.popupNow();
        Scenario scenario;

        if(scenarioRadioButton.isSelected()) {
            scenario = (Scenario) scenarioComboBox.getSelectedItem();
        } else {
            scenario = new ExternalScenario(extScenarioSelectFilePanel.getSelectedFile(), project.getMSGrid());
        }

        monitor.setNote("Initialisation...");
        MSGridBuilder<SquareGridExtent> msGrid = project.getMSGrid();
        File residFile = buildResidselectFilePanel.getSelectedFile();
        // chargement du shapefile
        DefaultFeatureCoverage<DefaultFeature> residCov = new DefaultFeatureCoverage<>(DefaultFeature.loadFeatures(residFile));
        // crée la couche raster 
        msGrid.addLayer(Evaluator.BATI_RESID, DataBuffer.TYPE_BYTE, 0);
        // rasterisation du bati résidentiel
        msGrid.execute(new SimpleCoverageOperation(SimpleCoverageOperation.ISEMPTY, Evaluator.BATI_RESID, residCov), true);
        // on supprime les cellules qui ne sont pas bati
        msGrid.execute(new AbstractLayerOperation(4) {
            @Override
            public void perform(Cell cell) {
                if (cell.getLayerValue(Project.BUILD) == 0 && cell.getLayerValue(Evaluator.BATI_RESID) == 1) {
                    cell.setLayerValue(Evaluator.BATI_RESID, 0);
                }
            }
        }, true);

        // récupère la grille à la résolution la plus fine
        SquareGrid grid = msGrid.getGrid(msGrid.getResolutions().last());

        if(buildTotselectFilePanel.getSelectedFile() != null) {
            // chargement du bati total
            Geometry totBuild = GlobalDataStore.createDataStore(buildTotselectFilePanel.getSelectedFile().getParentFile())
                    .getGeometry(buildTotselectFilePanel.getSelectedFile().getName());
            double radius = ((Number)seuilSpinner.getValue()).doubleValue() / 2;
            Geometry totBuildBuf = BufferForkJoinTask.threadedBuffer(totBuild, radius);

            // Création de la bordure urbaine en tenant compte des nouvelles cellules baties du scénario
            GridFeatureCoverage cov = new GridFeatureCoverage(grid);
            final String analLayer = scenario.getResultLayerName();
            FeatureCoverage<GridFeatureCoverage.GridFeature> newBuild = cov.getCoverage(new FeatureFilter() {
                @Override
                public boolean accept(Feature f) {
                    return ((Number)f.getAttribute(analLayer)).intValue() == 2;
                }
            });

            ArrayList<Geometry> geoms = new ArrayList<>();
            for(Feature f : newBuild.getFeatures()) {
                geoms.add(f.getGeometry().getCentroid());
            }

            Geometry buildBuf = BufferForkJoinTask.threadedBuffer(new GeometryFactory().buildGeometry(geoms), radius + 5);
            if(buildBuf == null) {
                buildBuf = totBuildBuf;
            } else {
                buildBuf = buildBuf.union(totBuildBuf);
            }
            Geometry envelope = BufferForkJoinTask.threadedBuffer(buildBuf, -radius);
            // bordure urbaine
            Geometry envLine = envelope.getBoundary();

            for(Evaluator evaluator : project.getEvaluators()) {
                if(evaluator instanceof NbCellOnEnvelopeEvaluator) {
                    ((NbCellOnEnvelopeEvaluator)evaluator).setUrbanBorder(envLine);
                } else if(evaluator instanceof DistEnvelopeEvaluator) {
                    ((DistEnvelopeEvaluator)evaluator).setUrbanBorder(envLine);
                }
            }
        }

        if(netN1SelectFilePanel.getSelectedFile() != null) {
            Geometry netGeom = GlobalDataStore.createDataStore(netN1SelectFilePanel.getSelectedFile().getParentFile())
                    .getGeometry(netN1SelectFilePanel.getSelectedFile().getName());
            SpatialGraph graph= new SpatialGraph(GlobalDataStore.getFeatures(netN1SelectFilePanel.getSelectedFile(), null, null),
                    new GeometryPrecisionReducer(new PrecisionModel(10)));
            for(Evaluator evaluator : project.getEvaluators()) {
                if(evaluator instanceof AbstractAmenEvaluator) {
                    ((AbstractAmenEvaluator)evaluator).setGraph(graph);
                } else if(evaluator instanceof DistEnvelopeEvaluator) {
                    ((DistEnvelopeEvaluator)evaluator).setGraph(graph);
                    ((DistEnvelopeEvaluator)evaluator).setNetGeom(netGeom);
                }
            }
        }

        Map<String, Double> coefEvaluators = evalSelectionPanel.getCoefEvaluators();
        monitor.setMaximum(coefEvaluators.size());
        Map<String, Double> coefLayers = new HashMap<>();
        for(Evaluator evaluator : project.getEvaluators()) {
            if(!coefEvaluators.containsKey(evaluator.getShortName())) {
                continue;
            }

            evaluator.execute(scenario, grid, monitor.getSubMonitor(0, 100, 1));
            // change le shortname en nom du layer pour l'opération d'agrégation
            coefLayers.put(evaluator.getEvalLayerName(scenario), coefEvaluators.get(evaluator.getShortName()));
        }

        if(evalSelectionPanel.isAgregMean()) {
            grid.addDynamicLayer(scenario.getName() + "_eval_agreg", new MeanOperation(coefLayers, 4, false));
        } else {
            grid.addDynamicLayer(scenario.getName() + "_eval_agreg", new YagerAgregOperation(coefLayers));
        }


        MapInternalFrame frm = new MapInternalFrame();
        GridGroupLayer gridGroupLayer = new GridGroupLayer("grid", grid, null);

        // création des sous répertoires 
        File rep = new File (project.getDirectory(), nameTextField.getText());
        rep.mkdir();
        File fichier = new File(rep.getAbsolutePath(), scenario.getName() + "_eval_agreg.tif");
        ((RasterLayer)gridGroupLayer.getLayer(scenario.getName() + "_eval_agreg")).saveRaster(fichier);


        List <Evaluator> listSelectEvaluator = new ArrayList<>();
        // boucler sur chaque evaluator et enregistrer le tif correspondant
        for(Evaluator evaluator : project.getEvaluators()) {
            // sélectionne uniquement les evaluators
            if(!coefEvaluators.containsKey(evaluator.getShortName())) {
                continue;
            }
            listSelectEvaluator.add(evaluator);
            fichier = new File(rep.getAbsolutePath(),  evaluator.getShortName() + ".tif");
            ((RasterLayer)gridGroupLayer.getLayer(evaluator.getEvalLayerName(scenario))).saveRaster(fichier);
        }

        // enregistrement en fichier xml des evaluators et ahp
        EvaluatorSerialization eval = new EvaluatorSerialization(listSelectEvaluator, evalSelectionPanel.getAHP(), coefEvaluators);
        eval.save(rep);

        try {    
            gridGroupLayer.setExpanded(true);
            frm.getMapViewer().setRootLayer(gridGroupLayer);
            frm.setName("Evaluation - " + scenario.getName());
            frm.setTitle("Evaluation - " + scenario.getName());
            ((MainFrame)getParent()).getDesktopPane().add(frm);
            frm.setMaximum(true);
            frm.setVisible(true);
            frm.setSelected(true);
            frm.getMapViewer().setTreeLayerVisible(true);
            frm.getMapViewer().getMap().setZoom(project.getBounds());
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        monitor.close();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.thema.common.swing.SelectFilePanel buildResidselectFilePanel;
    private org.thema.common.swing.SelectFilePanel buildTotselectFilePanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton evalParamButton;
    private org.thema.mupcity.evaluation.EvaluatorSelectionPanel evalSelectionPanel;
    private org.thema.common.swing.SelectFilePanel extScenarioSelectFilePanel;
    private javax.swing.JRadioButton externScenarioRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nameTextField;
    private org.thema.common.swing.SelectFilePanel netN1SelectFilePanel;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox scenarioComboBox;
    private javax.swing.JRadioButton scenarioRadioButton;
    private javax.swing.JSpinner seuilSpinner;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
