## Changelog

##### version 1.2.2 (19/02/2018)
- Merge with CLI branch for using MupCity with OpenMole

##### version 1.2.1 (19/02/2018)
- Le paramétrage des règles pour la décomposition n'était pas pris en compte 
- Optimisation du calcul de l'accessibilité aux commerces de niveau 3

##### version 1.2 
- La modification manuelle des pondérations d'un scénario n'était pas prise en compte, ni enregistrée

##### version 1.1.6 (16/10/2015)
- Le mode stricte des scénarios n'était pas propagé à toutes les échelles

##### version 1.1.5 (16/10/2015)
- pondération normalisée à 1 pour la moyenne arithmétique
- les coefficients n'étaient pas mis à jour après l'édition de l'AHP

##### version 1.1.4 (16/10/2015)
- suppression de 2 évaluations Ex-post inutiles
- Ex-post : les 2 évaluations de l'accès TC (train et bus) se font sur le réseau fin (MAP)

##### version 1.1.3 (10/10/2015)
WARNING projects are not compatible with previous versions !

- cleaning code + javadoc

##### version 1.1.2 (28/09/2015)
- bug construction cellule dans scénario manuel

##### version 1.1.1 (24/11/2014)
- bug ouverture fenêtre scénario manuel

##### Version 1.1
- pb sur evaluation des distances par type
- possibilité d'agrandir les fenêtres d'évaluations

##### version 1.0
- pb enregistrement dans le projet des couches additionnelles
- passage à maven sur le serveur dev
- restructuration des évaluations ex-post 

##### version 1.0 beta1(20/06/2013)
ATTENTION cette version n'est pas compatible avec les anciennes !

- refonte complète de la structure du projet et des règles
- évaluation ex-post possibilité d'ajouter un scénario externe en tif
- évaluation ex-post ajout des évaluations pour les loisirs
- évaluation ex-post : ajout des évaluations pour le bus et le train
- ajout dans propriété d'un scénario fractal du nombre de cellules nouvellement construites à l'échelle la plus fine

##### version 0.8beta(14/04/2011)
- passage common-dev et drawshape-dev
- parallélisation des calculs réseaux à la décomposition
- enregistrement en float au lieu de double des raster des distances aux services 

##### version 0.8alpha(05/05/2011)
- add layer raster
- scénario interactif : affichage nb new build cell

##### version 0.7.9 (09/02/2011)
- scénario interactif : meilleur gestion du mode edition
- affichage de l'évaluation de la cellule sélectionnée
- scénario interactif : ajout bouton add layer et export

##### version 0.7.8 (05/01/2011)
- bug dans calcul scénario mono échelle avec zones non constructible (boucle infinie)
  la solution actuelle fonctionne mais n'est pas très belle....

##### version 0.7.7 (03/01/2011)
- bug impossible de sélectionner un shp dans Evaluation Road network (directory only)

##### version 0.7.6 (22/12/2010)
- bug au début de la décomposition quand le projet a été enregistré avant.
- affiche un message quand les champs de la couche service ne sont pas corrects

##### version 0.7.5 (04/11/2010)
- modif de la règle sur les stations TC : loi linéaire décroissante de 0 à 500m de la station la plus proche

##### version 0.7.4 (06/09/2010)
- ajout de la couche station et de la règle correspondante
- correction bug calcul du nombre de gap dans scénario interactif

##### version 0.7.3 (30/08/2010)
- mise à jour de gt-2.5 vers gt-2.6
- réécriture de l'opération distance réseau

##### version 0.7.2 (21/06/2010)
- gestion français/anglais
- scénario plus basé sur une analyse

##### version 0.7.1 (07/05/2010)
- ajout dans évaluation du nombre de cellule inférieures à la moyenne

##### version 0.7 (29/03/2010)
- ajout du menu évaluation
- bug chemin relatif non géré pour shp zone non-constructible
- ajout stat decomp en faisant un clic droit sur decomp

##### version 0.6rc2 (25/02/2010)
- Correction gestion zone non-constructible en mono échelle
- Scénario : blocage des coulées vertes
- Scénario : affichage par un cadre noir des mailles nouvellement construites

##### version 0.6rc1 (13/11/2009)
- Correction du seuil de densité pour les cellules non constructibles
  qui n'était pas cohérent avec Nmax
- Ajout de 2 paramètres à la décomposition :
	- seuil de densité du bati au dela duquel une cellule est construite (noire) ex 0.005
	- réduction de la précision du réseau pour corriger les petits problèmes topologiques 
	  pour un réseau ayant une précision métrique on peut mettre une précision au cm (0.01)

##### version 0.6beta (12/11/2009)
- ajout des scénarios manuels
- restructuration du code et de l'interface
- gestion de zones rectangulaires
- gestion des zones non-constructibles
- packaging one-jar
- gestion mémoire par JavaLoader

##### version 0.5.3 (22/05/2009)
- correction bug dans analyse multi-échelle stricte sans règle de service

##### version 0.5.2 (16/04/2009)
- ajout des couches DistMinN1 et DistMinN2

##### version 0.5.1 (09/04/2009)
- on peut créer un nouveau project avec la zone d'un autre projet
version 0.5 :
- gestion particulière de la version mono échelle sans règle
- on peut maintenant supprimer une analyse

