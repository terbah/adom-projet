library('mco')

setwd("C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\")
#setwd("/home/m2miage/terbah/Documents/ADOM/fichiers")



best <- as.matrix(read.table("Instances2Objectifs/best.randomAB100.tsp", dec = "."))
best <- best + 0.0
#villeAlea <- best[sample(nrow(best), 100), ]
villeAlea <- as.matrix(read.table("fichiersGeneres/tp4/multiAlea.txt", dec="."))
villeAlea <- villeAlea + 0.0


# plot them
plot(best, xlim = c(0,270000), ylim = c(0,270000), xlab ="F1", ylab="F2", main = "Qualité des solutions aléatoires")
points(villeAlea, col='red')
legend("topleft", legend=c("Meilleur front connu", "solutions aléatoires"),
       col=c("black", "red"), pch=c(1,1), cex=0.8)
axis(4)

#plot.getRangeAxis().setAutoRange( false );


# reference point
refpoint <- c(max(best[,1], villeAlea[,1]), max(best[,2], villeAlea[,2]))
refpoint <- 1.1 * refpoint



# (inverted) generational distance (to be minimized)
generationalDistance(best, villeAlea)



# epsilon indicator (to be minimized)
epsilonIndicator(best, villeAlea)

# hypervolume (to be maximized)
dominatedHypervolume(villeAlea, refpoint)
dominatedHypervolume(best, refpoint)

# relative hypervolume difference (to be minimized)
(dominatedHypervolume(villeAlea, refpoint) - dominatedHypervolume(best, refpoint)) / dominatedHypervolume(villeAlea, refpoint)