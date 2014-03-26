/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.thema.mupcity.evaluation;


import java.io.IOException;
import org.thema.common.fuzzy.DiscreteFunction;
import org.thema.mupcity.scenario.Scenario;
import org.thema.common.param.XMLParams;
import org.thema.graph.SpatialGraph;
import org.thema.msca.Cell;
import org.thema.mupcity.Project;

/**
 *
 * @author gvuidel
 */
public class NbAmenEvaluator extends Evaluator {

    @XMLParams.NoParam
    private Project project;
    @XMLParams.NoParam
    private Project.Layers layer;
    @XMLParams.NoParam
    private int level;
    
    @XMLParams.NoParam
    private transient DistAmenities distAmen;
    
    @XMLParams.NoParam
    private transient SpatialGraph graph;
    
    double distMax;

    public NbAmenEvaluator(Project project, Project.Layers layer, int level, double distMax, double[] x, double[] y ) {
        super(new DiscreteFunction(x, y));
        this.distMax = distMax;
        this.project = project;
        this.layer = layer;
        this.level = level;
    }

    @Override
    protected double eval(Scenario scenario, Cell cell) {
        return getDistAmen().getNbAmen(cell, distMax);
    }

    @Override
    public String getShortName() {
        return "NB_" + layer.toString() + "-" + level;
    }
    
    @Override
    public boolean isUsable() {
        return project.isLayerExist(layer);
    }
    
    public void setGraph(SpatialGraph graph) {
        this.graph = graph;
        distAmen = null;
    }
    
    private synchronized DistAmenities getDistAmen() {
        if(distAmen == null)
            try {
                if(graph == null ||level != 1)
                    distAmen = new DistAmenities(project, layer, level);
                else
                    distAmen = new DistAmenities(project, layer, level, graph);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        return distAmen;
    }

//    @Override
//    public Double[] eval(final Scenario anal, final double mean) {
//
//        return grid.agregate(new AbstractAgregateOperation<Double[]>(4) {
//            int nb = 0, nbInf = 0;
//            double sum = 0;
//            public void perform(Cell cell) {
//                if(!isEvaluated(cell, anal))
//                    return;
//                int nbServ = distAmen.getNbAmen(cell, distMax);
//                sum += nbServ;
//                nb++;
//                if(nbServ < mean && isNewBuild(cell, anal))
//                    nbInf++;
//            }
//
//            @Override
//            public Double[] getResult() {
//                return new Double[] {sum / nb, (double)nb, (double)nbInf};
//            }
//
//        });
//    }


}
