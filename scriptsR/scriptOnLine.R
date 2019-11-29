library('mco')

setwd("/home/m2miage/lela/Documents/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/fichiersGeneres/tp4/onLine")
getwd()

onAllAB <- as.matrix(read.table("onAllAB.txt", dec="."))
onAllCD <- as.matrix(read.table("onAllCD.txt", dec="."))
onAllEF <- as.matrix(read.table("onAllEF.txt", dec="."))

bestAB <- read.table("onFiltreAB.txt", dec = ".")
bestCD <- read.table("onFiltreCD.txt", dec = ".")
bestEF <- read.table("onFiltreEF.txt", dec = ".")

#onAllAB <- onAllAB + 0.0

attach(mtcars)
par(mfrow=c(1,3))


# plot them
plot(onAllAB, main = "instance AB", xlab ="F1", ylab ="F2")
points(bestAB, col='red')

plot(onAllCD, main = "instance CD", xlab ="F1", ylab ="F2")
points(bestCD, col='red')

plot(onAllEF, main = "instance EF", xlab ="F1", ylab ="F2")
points(bestEF, col='red')


# reference point
refpoint <- c(max(villeAlea[,1], best[,1]), max(villeAlea[,2], best[,2]))
refpoint <- 1.1 * refpoint



# (inverted) generational distance (to be minimized)
generationalDistance(villeAlea, best)



# epsilon indicator (to be minimized)
epsilonIndicator(best, villeAlea)

# hypervolume (to be maximized)
dominatedHypervolume(villeAlea, refpoint)
dominatedHypervolume(best, refpoint)

# relative hypervolume difference (to be minimized)
(dominatedHypervolume(villeAlea, refpoint) - dominatedHypervolume(best, refpoint)) / dominatedHypervolume(villeAlea, refpoint)