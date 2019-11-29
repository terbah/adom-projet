library('mco')

setwd("/home/m2miage/lela/Documents/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/")
getwd()

bestAB <- read.table("Instances2Objectifs/best.randomAB100.tsp", dec = ".")
bestAB <- bestAB + 0.0
bestCD <- read.table("Instances2Objectifs/best.randomCD100.tsp", dec = ".")
bestCD <- bestCD + 0.0
bestEF <- read.table("Instances2Objectifs/best.randomEF100.tsp", dec = ".")
bestEF <- bestEF + 0.0

paretoAB <- as.matrix(read.table("fichiersGeneres/tp5/paretoAB.txt", dec="."))
paretoCD <- as.matrix(read.table("fichiersGeneres/tp5/paretoCD.txt", dec="."))
paretoEF <- as.matrix(read.table("fichiersGeneres/tp5/paretoEF.txt", dec="."))

attach(mtcars)
par(mfrow=c(1,3))

# plot them
plot(paretoAB, main = "instance AB", xlab ="F1", ylab ="F2")
points(bestAB, col='red')
legend("topright", legend=c("paretoAB", "Meilleur front connu"),
       col=c("black", "red"), pch=c(1,1), cex=0.8)

plot(paretoCD, main = "instance CD", xlab ="F1", ylab ="F2")
points(bestCD, col='red')
legend("topright", legend=c("paretoCD", "Meilleur front connu"),
       col=c("black", "red"), pch=c(1,1), cex=0.8)

plot(paretoEF, main = "instance EF", xlab ="F1", ylab ="F2")
points(bestEF, col='red')
legend("topright", legend=c("paretoEF", "Meilleur front connu"),
       col=c("black", "red"), pch=c(1,1), cex=0.8)


# reference point
refpoint <- c(max(bestAB[,1], frontScalaireAB10X100[,1]), max(bestAB[,2], frontScalaireAB10X100[,2]))
refpoint <- 1.1 * refpoint

# (inverted) generational distance (to be minimized)
generationalDistance(bestAB, frontScalaireAB10X100)

# epsilon indicator (to be minimized)
epsilonIndicator(frontScalaireAB10X100, bestAB)

# hypervolume (to be maximized)
dominatedHypervolume(bestAB, refpoint)
dominatedHypervolume(frontScalaireAB10X100, refpoint)

# relative hypervolume difference (to be minimized)
(dominatedHypervolume(bestAB, refpoint) - dominatedHypervolume(frontScalaireAB10X100, refpoint)) / dominatedHypervolume(bestAB, refpoint)