
package org.thema.mupcity.evaluation;

import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.thema.mupcity.AHP;
import org.thema.mupcity.Project;
import org.thema.mupcity.scenario.Scenario;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.thema.data.IOImage;
import org.thema.drawshape.image.RasterShape;
import org.thema.drawshape.layer.DefaultGroupLayer;
import org.thema.drawshape.layer.RasterLayer;
import org.thema.drawshape.style.RasterStyle;
import org.thema.drawshape.style.table.UniqueColorTable;
import org.thema.msca.MSGridBuilder;
import org.thema.msca.SquareGrid;
import org.thema.msca.SquareGridExtent;

/**
 * A false scenario to be used to evaluate a scenario created outside MupCity or from another project.
 * 
 * @author Gilles Vuidel
 */
public class ExternalScenario extends Scenario {

    /**
     * Creates a new false scenario based on the given tiff file.
     * The tiff must have the same width and height than the finest scale of the multiscale grid.
     * @param rasterFile the tiff file containing the scenario created outside mup-city
     * @param msGrid the multiscale grid of the current project to import the tiff file
     * @throws IOException 
     */
    public ExternalScenario(File rasterFile, MSGridBuilder<SquareGridExtent> msGrid) throws IOException {
        super(rasterFile.getName(), new AHP(Collections.EMPTY_LIST), 0, false);
        GridCoverage2D cov = IOImage.loadTiff(rasterFile);
        WritableRaster raster = (WritableRaster) cov.getRenderedImage().getData();
        SquareGridExtent grid = msGrid.getGrid(msGrid.getResolutions().last());
        RasterLayer.invertRaster(raster);
        grid.addLayer(getResultLayerName(), raster);
    }
    
    
    @Override
    public final String getResultLayerName() {
        return "ext-" + getName();
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public String getEvalLayerName() {
        throw new UnsupportedOperationException("External scenario has no evaluation");
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public String getBuildFreeLayerName() {
        throw new UnsupportedOperationException("External scenario has no morpho rule");
    }

    @Override
    protected void createLayers(MSGridBuilder<? extends SquareGrid> msGrid) {
        layers = new DefaultGroupLayer(getName());
        SquareGrid grid = msGrid.getGrid(msGrid.getResolutions().last());
        RasterStyle style = new RasterStyle(new UniqueColorTable(Project.COLOR_MAP));
        style.setDrawGrid(false);
        RasterLayer l = new RasterLayer(String.format("%s", Project.SIMUL),
            new RasterShape(grid.getRaster(getResultLayerName()),
                org.geotools.geometry.jts.JTS.getEnvelope2D(grid.getEnvelope(),
                    DefaultGeographicCRS.WGS84).getBounds2D()));
        l.setVisible(false);
        l.setCRS(Project.getProject().getCRS());
        l.setStyle(style);

        layers.addLayerLast(l);
    }
    
}
