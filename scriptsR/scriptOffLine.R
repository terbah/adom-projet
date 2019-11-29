library('mco')

setwd("C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\fichiersGeneres")
getwd()

offAllAB <- as.matrix(read.table("offAllAB.txt", dec="."))
offAllCD <- as.matrix(read.table("offAllCD.txt", dec="."))
offAllEF <- as.matrix(read.table("offAllEF.txt", dec="."))

bestAB <- read.table("offFiltreAB.txt", dec = ".")
bestCD <- read.table("offFiltreCD.txt", dec = ".")
bestEF <- read.table("offFiltreEF.txt", dec = ".")

#offAllAB <- offAllAB + 0.0

attach(mtcars)
par(mfrow=c(1,3))


# plot them
plot(offAllAB, main = "instance AB", xlab ="F1", ylab ="F2")
points(bestAB, col='red')

plot(offAllCD, main = "instance CD", xlab ="F1", ylab ="F2")
points(bestCD, col='red')

plot(offAllEF, main = "instance EF", xlab ="F1", ylab ="F2")
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