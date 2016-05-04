package org.thema.mupcity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;

import org.geotools.feature.SchemaException;
import org.thema.common.swing.TaskMonitor;
import org.thema.mupcity.rule.OriginDistance;
import org.thema.mupcity.rule.Rule;
import org.thema.mupcity.scenario.ScenarioAuto;

/**
 * Create automatic tests and loop on defined values
 * 
 * @author Maxime Colomb
 */

public class ExplorationTest {
	public static void main(String[] args) throws IOException, SchemaException {

		String folderData = "/home/mickael/data/mbrasebin/donnees/Donnee_Maxime/data/";
		String folderOut =  folderData + "out/";
		// définition des variables fixes
		String name = "testExplo";
		File dir = new File(folderOut);
		File buildFile = new File(folderData + "BATI_AU.shp");
		int exp = 3;
		double minSize = 20;
		double maxSize = 25273;
		boolean useNoBuild = true;
		boolean network = true;// true => network distance
		File roadFile = new File(folderData + "route_sans_chemin.shp");
		File facilityFile = new File(folderData + "CS_au_besac_sirene_2012.shp");
		File leisureFile = new File(folderData + "loisirs.shp");
		File busFile = new File(folderData + "stations_besac_tram_2015.shp");
		File trainFile = new File(folderData + "gare_train_ICONE_docs_2015.shp");
		File restrictFile = new File(folderData+"ICONE-zonesNU_AU.shp");
		double seuilDensBuild = 0.0;// NO PARAMETER FOR THAT
		boolean isTest = false; // si l'on veut tester le programme, quelques
								// shortcuts pour que ça aille plus vite
		boolean memedos = true;// si l'on veut ou non une organisation dans des
								// dossiers déocupant les scénario, ou
								// uniquement les grilles

		// empty monitor
		TaskMonitor mon = new TaskMonitor.EmptyMonitor();

		// definition de la grille
		double width = 32243;
		double height = 33602;
		double minX = 911598;
		double minY = 6670519;
		if (isTest) {
			width = width / 6;
			height = height / 6;
		}
		String g = "G1";
		// variation de la grille -- 3 valeurs
		for (int a = 0; a <= 2; a++) {
			switch (a) {
			case 1:
				minX = minX + 150;
				g = "G2";
				System.out.println("première grille calculé");
				break;
			case 2:
				minX = minX - 200;
				minY = minY + 100;
				g = "G3";
				System.out.println("deuxième grille calculé");
				break;
			}

			// create a new project
			File dirgrid = new File(dir + "/" + g);
			dirgrid.mkdir();
			Project project = Project.createProject(name, dirgrid, buildFile, minX, minY, width, height, mon);

			project.setNetPrecision(0.1); // Le réseau routier apparait peut
											// être un peu moins déformé avec
											// cette contrainte, mais ce n'est
											// pas pour ça qu'il n'y a plus de
											// tache =0 dans fac3

			// set layers and attributes for the decomposition
			List<String> roadAttrs = Arrays.asList("Speed");// SPEED(numeric)
			project.setLayer(Project.LAYERS.get(Project.Layers.ROAD.ordinal()), roadFile, roadAttrs);
			List<String> facilityAttrs = Arrays.asList("LEVEL", "TYPE");// LEVEL
																		// (numeric),
																		// TYPE
																		// (any)
			project.setLayer(Project.LAYERS.get(Project.Layers.FACILITY.ordinal()), facilityFile, facilityAttrs);
			List<String> leisureAttrs = Arrays.asList("LEVEL", "TYPE");// LEVEL
																		// (numeric),
																		// TYPE
																		// (any)
			project.setLayer(Project.LAYERS.get(Project.Layers.LEISURE.ordinal()), leisureFile, leisureAttrs);
			List<String> emptyAttrs = Arrays.asList("");
			project.setLayer(Project.LAYERS.get(Project.Layers.BUS_STATION.ordinal()), busFile, emptyAttrs);
			project.setLayer(Project.LAYERS.get(Project.Layers.TRAIN_STATION.ordinal()), trainFile, emptyAttrs);
			project.setLayer(Project.LAYERS.get(Project.Layers.RESTRICT.ordinal()), restrictFile, emptyAttrs);
			project.setDistType(
					(network) ? OriginDistance.NetworkDistance.class : OriginDistance.EuclideanDistance.class);

			// setting of the six different AHP matrix
			// we first take the names of the different working rules
			List<String> items = new ArrayList<>();
			for (Rule rule : project.getRules()) {
				if (rule.isUsable(project)) {
					items.add(rule.getName());
				}
			}

			// setting on our six ahp objects
			AHP ahpE_Yag = new AHP(items);// creation of the true objects
			ahpE_Yag.realName = "ahpE_Yag";// putting a String name
			AHP ahpT_Yag = new AHP(items);
			ahpT_Yag.realName = "ahpT_Yag";
			AHP ahpS_Yag = new AHP(items);
			ahpS_Yag.realName = "ahpS_Yag";
			AHP ahpE_Moy = new AHP(items);
			ahpE_Moy.realName = "ahpE_Moy";
			AHP ahpT_Moy = new AHP(items);
			ahpT_Moy.realName = "ahpT_Moy";
			AHP ahpS_Moy = new AHP(items);
			ahpS_Moy.realName = "ahpS_Moy";

			HashMap<String, Double> coefsE_Yag = new HashMap<>();// collection
																	// of the
																	// eigenvector
																	// values
			coefsE_Yag.put(items.get(8), 1.0);
			coefsE_Yag.put(items.get(7), 1.0);
			coefsE_Yag.put(items.get(6), 1.0);
			coefsE_Yag.put(items.get(5), 1.0);
			coefsE_Yag.put(items.get(4), 1.0);
			coefsE_Yag.put(items.get(3), 1.0);
			coefsE_Yag.put(items.get(2), 1.0);
			coefsE_Yag.put(items.get(1), 1.0);
			coefsE_Yag.put(items.get(0), 1.0);
			ahpE_Yag.setCoeff(coefsE_Yag);// we force the vector to the ahp
											// objects

			HashMap<String, Double> coefsT_Yag = new HashMap<>();
			coefsE_Yag.put(items.get(8), 0.458);
			coefsE_Yag.put(items.get(7), 0.458);
			coefsE_Yag.put(items.get(6), 0.458);
			coefsT_Yag.put(items.get(5), 3.625);
			coefsT_Yag.put(items.get(4), 1.199);
			coefsT_Yag.put(items.get(3), 1.199);
			coefsT_Yag.put(items.get(2), 1.199);
			coefsT_Yag.put(items.get(1), 0.202);
			coefsT_Yag.put(items.get(0), 0.202);
			ahpT_Yag.setCoeff(coefsT_Yag);

			HashMap<String, Double> coefsS_Yag = new HashMap<>();
			coefsE_Yag.put(items.get(8), 0.745);
			coefsE_Yag.put(items.get(7), 0.745);
			coefsE_Yag.put(items.get(6), 0.745);
			coefsS_Yag.put(items.get(5), 0.359);
			coefsS_Yag.put(items.get(4), 1.965);
			coefsS_Yag.put(items.get(3), 1.965);
			coefsS_Yag.put(items.get(2), 1.965);
			coefsS_Yag.put(items.get(1), 0.269);
			coefsS_Yag.put(items.get(0), 0.243);
			ahpS_Yag.setCoeff(coefsS_Yag);

			HashMap<String, Double> coefsE_Moy = new HashMap<>();
			coefsE_Yag.put(items.get(8), 0.111);
			coefsE_Yag.put(items.get(7), 0.111);
			coefsE_Yag.put(items.get(6), 0.111);
			coefsE_Moy.put(items.get(5), 0.111);
			coefsE_Moy.put(items.get(4), 0.111);
			coefsE_Moy.put(items.get(3), 0.111);
			coefsE_Moy.put(items.get(2), 0.111);
			coefsE_Moy.put(items.get(1), 0.111);
			coefsE_Moy.put(items.get(0), 0.111);
			ahpE_Moy.setCoeff(coefsE_Moy);

			HashMap<String, Double> coefsT_Moy = new HashMap<>();
			coefsE_Yag.put(items.get(8), 0.051);
			coefsE_Yag.put(items.get(7), 0.051);
			coefsE_Yag.put(items.get(6), 0.051);
			coefsT_Moy.put(items.get(5), 0.403);
			coefsT_Moy.put(items.get(4), 0.133);
			coefsT_Moy.put(items.get(3), 0.133);
			coefsT_Moy.put(items.get(2), 0.133);
			coefsT_Moy.put(items.get(1), 0.022);
			coefsT_Moy.put(items.get(0), 0.022);
			ahpT_Moy.setCoeff(coefsT_Moy);

			HashMap<String, Double> coefsS_Moy = new HashMap<>();
			coefsE_Yag.put(items.get(8), 0.083);
			coefsE_Yag.put(items.get(7), 0.083);
			coefsE_Yag.put(items.get(6), 0.083);
			coefsS_Moy.put(items.get(5), 0.04);
			coefsS_Moy.put(items.get(4), 0.218);
			coefsS_Moy.put(items.get(3), 0.218);
			coefsS_Moy.put(items.get(2), 0.218);
			coefsS_Moy.put(items.get(1), 0.03);
			coefsS_Moy.put(items.get(0), 0.027);
			ahpS_Moy.setCoeff(coefsS_Moy);

			// list of AHP to loop in
			List<AHP> ahpList = new ArrayList<AHP>();
			ahpList.add(ahpE_Yag);
			ahpList.add(ahpT_Yag);
			ahpList.add(ahpS_Yag);
			ahpList.add(ahpE_Moy);
			ahpList.add(ahpT_Moy);
			ahpList.add(ahpS_Moy);

			// create new decomp
			project.decomp(exp, maxSize, minSize, seuilDensBuild, mon);
			project.save();
			// looping for scenarios
			// loop on Nmax
			for (int nMax = 3; nMax <= 7; nMax++) {
				String nname = "N" + nMax;// part of the folder's name
				// loop on strict/basic
				for (int s = 0; s <= 1; s++) {
					String nstrict;// part of the folder's name
					boolean strict;
					if (s == 0) {
						strict = true;
						nstrict = "St";
					} else {
						strict = false;
						nstrict = "Ba";
					}
					// loop on the AHP
					for (AHP ahp : ahpList) {
						String nahp = ahp.getName();
						int lgt = nahp.length();
						boolean mean; // determination de par le nom de l'ahp si
										// la methode de calcul sera avec mean
										// ou Yager
						if (nahp.substring(lgt - 3).equals("Moy")) {
							mean = true;
						} else {
							mean = false;
						}
						for (long seed = 1; seed <= 3; seed++) {

							String nameseed = "replication_" + seed;// part of
																	// the
																	// folder's
																	// name
							String titre = g + "--" + nname + "--" + nstrict + "--" + nahp + "--" + nameseed;// part
																												// of
																												// the
																												// folder's
																												// name
							File testFile;
							if (memedos) {
								testFile = dirgrid;
							} else {
								testFile = new File("/home/mcolomb/informatique/MUP/explo/result/testExplo/" + g + "/"
										+ nMax + "/" + titre);
							}

							NavigableSet<Double> res = project.getMSGrid().getResolutions();
							ScenarioAuto scenario = ScenarioAuto.createMultiScaleScenario(titre, res.first(),
									res.last(), nMax, strict, ahp, useNoBuild, mean, exp, seed);
							project.performScenarioAuto(scenario);
<<<<<<< HEAD

							// save the project
							// scenario.save(testFile,project);
							// project.getMSGrid().saveRaster(scenario.getEvalLayerName(),
							// testFile); pas besoin de ces couches
							scenario.extractEvalAnal(testFile, project);
							// delete of the saved layer to unload the heap
							// space
							project.getMSGrid().removeLayer(titre + "-morpho");
							project.getMSGrid().removeLayer(titre + "-eval_anal");
							project.getMSGrid().removeLayer(titre + "-analyse");
							project.getMSGrid().removeLayer(titre + "-eval");

							// write the seed into a text file, uselesse now as
							// the seeds are fixed
=======
								// save the project
							//scenario.save(testFile,project);
							//project.getMSGrid().saveRaster(scenario.getEvalLayerName(), testFile);	pas besoin de ces couches					
							scenario.extractEvalAnal(testFile,project); 
								//delete of the saved layer to unload the heap space
							project.getMSGrid().removeLayer(titre+"-morpho");
							project.getMSGrid().removeLayer(titre+"-eval_anal");
							project.getMSGrid().removeLayer(titre+"-analyse");
							project.getMSGrid().removeLayer(titre+"-eval");
						
								// write the seed into a text file, uselesse now as the seeds are fixed
>>>>>>> 075c26f3aa2e1e26e2a6f7b96376bfd3373c3ef5
							Charset charset = Charset.forName("US-ASCII");
							String nseed = String.valueOf(seed);
							File testFiletext = new File(testFile + "/nbseed");
							try (BufferedWriter nbseed = Files.newBufferedWriter(testFiletext.toPath(), charset)) {
								nbseed.write(nseed);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
