
package org.thema.mupcity;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.geotools.feature.SchemaException;
import org.thema.common.Config;
import org.thema.common.JavaLoader;
import org.thema.common.Util;
import org.thema.common.swing.PreferencesDialog;
import org.thema.drawshape.AbstractSelectableShape;
import org.thema.drawshape.layer.DefaultGroupLayer;
import org.thema.drawshape.layer.DefaultLayer;
import org.thema.drawshape.layer.FeatureLayer;
import org.thema.drawshape.layer.ShapeFileLayer;
import org.thema.drawshape.style.SimpleStyle;
import org.thema.drawshape.ui.MapInternalFrame;
import org.thema.mupcity.evaluation.EvaluationDialog;
import org.thema.mupcity.scenario.ScenarioAuto;
import org.thema.mupcity.scenario.ScenarioFrame;
import org.thema.mupcity.scenario.ScenarioManual;

/**
 * The main frame of the software.
 * 
 * @author  Gilles Vuidel
 */
public class MainFrame extends javax.swing.JFrame {

    private Project project;
    
    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        tree.setModel(new DefaultTreeModel(null));
        setTitle("MUP-City " + JavaLoader.getVersion(MainFrame.class));
        updateMenu();
        setLocationRelativeTo(null);
        
        AbstractSelectableShape.DEFAULT_SELECTSTYLE.setStyle(
                new SimpleStyle(Color.BLUE, 1.5f));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        treeScrollPane = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        setLayerMenuItem = new javax.swing.JMenuItem();
        addInfoLayerMenuItem = new javax.swing.JMenuItem();
        prefMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        quitMenuItem = new javax.swing.JMenuItem();
        simMenu = new javax.swing.JMenu();
        decompMenuItem = new javax.swing.JMenuItem();
        startSimMenuItem = new javax.swing.JMenuItem();
        monoSimMenuItem = new javax.swing.JMenuItem();
        newScenarioMenuItem = new javax.swing.JMenuItem();
        evalMenu = new javax.swing.JMenu();
        evalExpostMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        splitPane.setDividerLocation(150);

        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                treeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                treeMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        treeScrollPane.setViewportView(tree);

        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(desktopPane);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle"); // NOI18N
        fileMenu.setText(bundle.getString("MainFrame.fileMenu.text")); // NOI18N

        newMenuItem.setText(bundle.getString("MainFrame.newMenuItem.text")); // NOI18N
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setText(bundle.getString("MainFrame.openMenuItem.text")); // NOI18N
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        setLayerMenuItem.setText(bundle.getString("MainFrame.setLayerMenuItem.text")); // NOI18N
        setLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLayerMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(setLayerMenuItem);

        addInfoLayerMenuItem.setText(bundle.getString("MainFrame.addInfoLayerMenuItem.text")); // NOI18N
        addInfoLayerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInfoLayerMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(addInfoLayerMenuItem);

        prefMenuItem.setText(bundle.getString("MainFrame.prefMenuItem.text")); // NOI18N
        prefMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(prefMenuItem);
        fileMenu.add(jSeparator1);

        quitMenuItem.setText(bundle.getString("MainFrame.quitMenuItem.text")); // NOI18N
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        simMenu.setText(bundle.getString("MainFrame.simMenu.text")); // NOI18N

        decompMenuItem.setText(bundle.getString("MainFrame.decompMenuItem.text")); // NOI18N
        decompMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decompMenuItemActionPerformed(evt);
            }
        });
        simMenu.add(decompMenuItem);

        startSimMenuItem.setText(bundle.getString("MainFrame.startSimMenuItem.text")); // NOI18N
        startSimMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimMenuItemActionPerformed(evt);
            }
        });
        simMenu.add(startSimMenuItem);

        monoSimMenuItem.setText(bundle.getString("MainFrame.monoSimMenuItem.text")); // NOI18N
        monoSimMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monoSimMenuItemActionPerformed(evt);
            }
        });
        simMenu.add(monoSimMenuItem);

        newScenarioMenuItem.setText(bundle.getString("MainFrame.newScenarioMenuItem.text")); // NOI18N
        newScenarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newScenarioMenuItemActionPerformed(evt);
            }
        });
        simMenu.add(newScenarioMenuItem);

        menuBar.add(simMenu);

        evalMenu.setText(bundle.getString("MainFrame.evalMenu.text")); // NOI18N

        evalExpostMenuItem.setText(bundle.getString("MainFrame.evalExpostMenuItem.text")); // NOI18N
        evalExpostMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evalExpostMenuItemActionPerformed(evt);
            }
        });
        evalMenu.add(evalExpostMenuItem);

        menuBar.add(evalMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private DefaultGroupLayer getDefaultLayers() {
        DefaultGroupLayer gl = new DefaultGroupLayer(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Layers"), true);
        
        for(LayerDef lDef : Project.LAYERS) {
            if (project.isLayerExist(lDef.getLayer())) {
                gl.addLayerLast(new FeatureLayer(lDef.getDesc(), project.getLayerFeatures(lDef.getLayer()), project.getBounds(), lDef.getStyle()));
            }
        }
        
        gl.addLayerFirst(project.getInfoLayer());

       return gl;
    }

    private DefaultGroupLayer getZoneLayers() {
        DefaultGroupLayer gl = getDefaultLayers();
        gl.addLayerLast(new DefaultLayer(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Zone_layer"), project.getRectShape(), new SimpleStyle(new Color(255, 0, 255, 127))));
       return gl;
    }

    private DefaultGroupLayer getDecompLayers() {
        DefaultGroupLayer gl = getDefaultLayers();      
        gl.addLayerLast(project.getGridLayer());
        gl.addLayerLast(project.getDecompLayer());
        
       return gl;
    }

    private DefaultGroupLayer getAnalyseLayers(String name) {
        ScenarioAuto anal = project.getScenarioAuto(name);
        DefaultGroupLayer gl = getDefaultLayers();
        gl.addLayerLast(project.getGridLayer());
        DefaultGroupLayer agl = anal.getLayers();
        if(anal.isMonoScale()) {
            agl.getLayerFirst().setVisible(true);
        } else {
            ((DefaultGroupLayer)agl.getLayerFirst()).getLayerFirst().setVisible(true);
        }
        gl.addLayerLast(agl);


       return gl;
    }

    private DefaultGroupLayer getScenarioLayers(String name) {
        ScenarioManual sce = project.getScenario(name);
        DefaultGroupLayer gl = getDefaultLayers();
        gl.addLayerLast(project.getGridLayer());
        gl.addLayerLast(sce.getLayers());

       return gl;
    }

    private void maybeShowPopup(MouseEvent e) {
        if (!e.isPopupTrigger() || tree.isSelectionEmpty()) {
            return;
        }

        JPopupMenu menu = new JPopupMenu();
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return ;
        }

        String name = path.getLastPathComponent().toString();
        if(name.equals(Project.NODE_DECOMP)) {
            menu.add(new JMenuItem(statDecompAction));
        }


        String parentName = path.getParentPath().getLastPathComponent().toString();
        if(parentName.equals(Project.NODE_ANALYSE)) {
            menu.add(new JMenuItem(removeAnalyseAction));
            menu.add(new JMenuItem(propertyAnalyseAction));
        }
         else if(parentName.equals(Project.NODE_SCENARIO)) {
             menu.add(new JMenuItem(removeScenarioAction));
             //menu.add(new JMenuItem(propertyAction));
         }

        if(menu.getComponentCount() > 0) {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }

    }

    private void startSimMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimMenuItemActionPerformed
        final NewScenarioAutoDialog dlg = new NewScenarioAutoDialog(this, project);
        dlg.setVisible(true);
        if(!dlg.returnOk) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    project.performScenarioAuto(dlg.scenario);
                    project.save();
                    updateTree();
                    updateMenu();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "Error :\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }).start();
        
        
    }//GEN-LAST:event_startSimMenuItemActionPerformed

    private void decompMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decompMenuItemActionPerformed
        DecompDlg dlg = new DecompDlg(this, project.getRectShape());
        dlg.setVisible(true);        
    }//GEN-LAST:event_decompMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        File prjFile = Util.getFile(".xml", "Project XML");
        if(prjFile == null) {
            return;
        }

        if(project != null) {
            project = null;
            tree.setModel(new DefaultTreeModel(null));
        }
        try {
            project = Project.load(prjFile);
            tree.setModel(new DefaultTreeModel(project));
            updateMenu();
        } catch (IOException e) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, e);
	    JOptionPane.showMessageDialog(null, "Impossible de lire le projet !\nDétails : " + e, "Erreur", JOptionPane.ERROR_MESSAGE);      
	}
	
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        if(project != null) {
            project = null;
            tree.setModel(new DefaultTreeModel(null));
        }
        NewProjectDialog dlg = new NewProjectDialog(this);
        dlg.setVisible(true);
  
        project = dlg.getProject();
        tree.setModel(new DefaultTreeModel(project));
        updateMenu();
        
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void addInfoLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInfoLayerMenuItemActionPerformed
        File f = Util.getFile(".shp", "Shapefile *.shp");
        if (f == null) {
            return;
        }

        try {
            project.addInfoLayer(new ShapeFileLayer(f));
            project.save();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error while loading shapefile :\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addInfoLayerMenuItemActionPerformed

    private void monoSimMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monoSimMenuItemActionPerformed
        final NewScenarioMonoDialog dlg = new NewScenarioMonoDialog(this, project);
        dlg.setVisible(true);
        if(!dlg.returnOk) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    project.performScenarioAuto(dlg.scenario);
                    project.save();
                    updateTree();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "Error :\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
        }).start();

    }//GEN-LAST:event_monoSimMenuItemActionPerformed

    private void newScenarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newScenarioMenuItemActionPerformed
        NewScenarioManualDialog dlg = new NewScenarioManualDialog(this, project);
        dlg.setVisible(true);
        if(!dlg.isOk) {
            return;
        }
        
        try {
            project.createManualScenario(dlg.name, dlg.nMax, dlg.ahp, dlg.isAgregMean);
            updateTree();
            project.save();
            final ScenarioFrame frm = new ScenarioFrame(project.getScenario(dlg.name),
                    getScenarioLayers(dlg.name), project);
            desktopPane.add(frm);
            frm.setMaximum(true);
            frm.setVisible(true);
            frm.setSelected(true);
            frm.getMapViewer().getMap().setZoom(project.getBounds());
        } catch (IOException | PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(MainFrame.this, "Error :\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_newScenarioMenuItemActionPerformed

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        if(evt.getClickCount() < 2) {
            return;
        }

        TreeNode node = (TreeNode)path.getLastPathComponent();
        if(node.isLeaf()) {
            final String name = node.toString();
            String pathName = path.toString();
            JInternalFrame frame = null;
            for(JInternalFrame fr : desktopPane.getAllFrames()) {
                if (fr.getName().equals(pathName)) {
                    frame = fr;
                }
            }
            if(frame != null) {
                try {
                    frame.setSelected(true);
                } catch(PropertyVetoException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            if(name.equals(Project.NODE_ZONE)) {
                try {
                    MapInternalFrame frm = new MapInternalFrame();
                    frm.getMapViewer().setRootLayer(getZoneLayers());
                    frm.setName(pathName);
                    frm.setTitle(Project.NODE_ZONE);
                    desktopPane.add(frm);
                    frm.setMaximum(true);
                    frm.setVisible(true);
                    frm.setSelected(true);

                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            if(name.equals(Project.NODE_DECOMP)) {
                try {
                    MapInternalFrame frm = new MapInternalFrame();
                    frm.getMapViewer().setRootLayer(getDecompLayers());
                    frm.setName(pathName);
                    frm.setTitle(Project.NODE_DECOMP);
                    desktopPane.add(frm);
                    frm.setMaximum(true);
                    frm.setVisible(true);
                    frm.setSelected(true);

                    frm.getMapViewer().setTreeLayerVisible(true);
                    frm.getMapViewer().getMap().setZoom(project.getBounds());
                    
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }


            if(node.getParent().toString().equals(Project.NODE_ANALYSE)) {
                try {
                    MapInternalFrame frm = new MapInternalFrame();
                    frm.getMapViewer().setRootLayer(getAnalyseLayers(name));
                    frm.setName(pathName);
                    frm.setTitle(Project.NODE_ANALYSE + " : " + name);
                    desktopPane.add(frm);
                    frm.setMaximum(true);
                    frm.setVisible(true);
                    frm.setSelected(true);
                    frm.getMapViewer().setTreeLayerVisible(true);
                    frm.getMapViewer().getMap().setZoom(project.getBounds());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
            if(node.getParent().toString().equals(Project.NODE_SCENARIO)) {
                try {
                    final ScenarioFrame frm = new ScenarioFrame(project.getScenario(name),
                            getScenarioLayers(name), project);
                    frm.setName(pathName);
                    desktopPane.add(frm);
                    frm.setMaximum(true);
                    frm.setVisible(true);
                    frm.setSelected(true);
                    frm.getMapViewer().getMap().setZoom(project.getBounds());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

        }
    }//GEN-LAST:event_treeMouseClicked

    private void treeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMousePressed
        maybeShowPopup(evt);
    }//GEN-LAST:event_treeMousePressed

    private void treeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseReleased
        maybeShowPopup(evt);
    }//GEN-LAST:event_treeMouseReleased

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void prefMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefMenuItemActionPerformed
        PreferencesDialog dlg = new PreferencesDialog(this, true);
        dlg.setProcPanelVisible(true);
        dlg.setVisible(true);
    }//GEN-LAST:event_prefMenuItemActionPerformed

    private void setLayerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setLayerMenuItemActionPerformed
        final SetLayerDialog dlg = new SetLayerDialog(this, project);
        dlg.setVisible(true);
        if(!dlg.isOk) {
            return;
        }
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    project.setLayer(dlg.layer, dlg.file, dlg.attrs);
                } catch (IOException | SchemaException ex) {
                    Logger.getLogger(SetLayerDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "An error occured : \n" + ex);
                }
            }
        }).start();
    }//GEN-LAST:event_setLayerMenuItemActionPerformed

    private void evalExpostMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evalExpostMenuItemActionPerformed
        new EvaluationDialog(this, project).setVisible(true);
    }//GEN-LAST:event_evalExpostMenuItemActionPerformed

    /**
     * Removes previous decomposition, launches a new decomposition, saves the project and updates UI.
     * 
     * @param exp the factor between scale
     * @param maxSize the max size of cells
     * @param minSize the min size of cells
     * @param seuilDensBuild the minimum density for a cell to be built
     */
    public void decomp(final int exp, final double maxSize, final double minSize, 
            final double seuilDensBuild) {
        project.removeDecomp();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    project.decomp(exp, maxSize, minSize, seuilDensBuild);
                    project.save();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "An error occured :\n" + ex.getLocalizedMessage() + "\n");
                    project.removeDecomp();
                }
                updateTree();
                updateMenu();                
                
            }
        }).start();
    }

    /**
     * Updates the tree by recreating the tree model
     */
    private void updateTree() {
        tree.setModel(new DefaultTreeModel(project));
    }

    /**
     * Updates the states (enable or disable) of menus
     */
    private void updateMenu() {
        simMenu.setEnabled(project != null);
        addInfoLayerMenuItem.setEnabled(project != null);
        setLayerMenuItem.setEnabled(project != null);

        if(project == null) {
            return;
        }
        startSimMenuItem.setEnabled(project.isDecomp());
        monoSimMenuItem.setEnabled(project.isDecomp());
        newScenarioMenuItem.setEnabled(project.isDecomp());
    }

    /**
     * @return the desktop pane used in this frame.
     */
    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    /**
     * @return the current project
     */
    public Project getProject() {
        return project;
    }

    private AbstractAction removeAnalyseAction = new AbstractAction(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Supprimer")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            TreeNode node = (TreeNode)tree.getSelectionPath().getLastPathComponent();
            String analName = node.toString();
            int res = JOptionPane.showConfirmDialog(MainFrame.this, java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Voulez-vous_supprimer_l'analyse_") + analName + " ?",
                    java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Suppression..."), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION) {
                try {
                    project.removeAnalysis(project.getScenarioAuto(analName));
                    project.save();
                    updateTree();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "An error occured : \n" + ex);
                }
            }
            
        }
    };
    private AbstractAction propertyAnalyseAction = new AbstractAction(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Propriétés")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            TreeNode node = (TreeNode)tree.getSelectionPath().getLastPathComponent();
            ScenarioAuto anal = project.getScenarioAuto(node.toString());
            JOptionPane.showMessageDialog(MainFrame.this, anal.getInfo());
        }
    };
    
    private AbstractAction statDecompAction = new AbstractAction(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Statistique")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this, new JScrollPane(new JTextArea(project.getStatDecomp())));
        }
    };

    private AbstractAction removeScenarioAction = new AbstractAction(java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Supprimer")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            TreeNode node = (TreeNode)tree.getSelectionPath().getLastPathComponent();
            String sceName = node.toString();
            int res = JOptionPane.showConfirmDialog(MainFrame.this, java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Voulez-vous_supprimer_l'analyse_") + sceName + " ?",
                    java.util.ResourceBundle.getBundle("org/thema/mupcity/Bundle").getString("Suppression..."), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.YES_OPTION) {
                try {
                    project.removeScenario(project.getScenario(sceName));
                    project.save();
                    updateTree();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(MainFrame.this, "An error occured : \n" + ex);
                }
            }

        }
    };

    /**
     * Main entry point of the software.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Config.setNodeClass(MainFrame.class);
        PreferencesDialog.initLanguage();

        JavaLoader.launchGUI(MainFrame.class, args.length == 0, 1024);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addInfoLayerMenuItem;
    private javax.swing.JMenuItem decompMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem evalExpostMenuItem;
    private javax.swing.JMenu evalMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem monoSimMenuItem;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem newScenarioMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem prefMenuItem;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JMenuItem setLayerMenuItem;
    private javax.swing.JMenu simMenu;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JMenuItem startSimMenuItem;
    private javax.swing.JTree tree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
    
}
