library('mco')

setwd("/home/m2miage/lela/Documents/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/")
getwd()

bestAB <- read.table("Instances2Objectifs/best.randomAB100.tsp", dec = ".")
bestAB <- bestAB + 0.0
bestCD <- read.table("Instances2Objectifs/best.randomCD100.tsp", dec = ".")
bestCD <- bestCD + 0.0
bestEF <- read.table("Instances2Objectifs/best.randomEF100.tsp", dec = ".")
bestEF <- bestEF + 0.0

frontScalaireAB10X100 <- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireAB10X100.txt", dec="."))
frontScalaireAB10X100 <- frontScalaireAB10X100 + 0.0

frontScalaireCD10X100 <- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireCD10X100.txt", dec="."))
frontScalaireEF10X100 <- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireEF10X100.txt", dec="."))

frontScalaireAB2X500 <- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireAB2X500.txt", dec="."))
frontScalaireCD2X500 <- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireCD2X500.txt", dec="."))
frontScalaireEF2X500<- as.matrix(read.table("fichiersGeneres/tp5/frontScalaireEF2X500.txt", dec="."))


attach(mtcars)
par(mfrow=c(1,3))


# plot them
plot(frontScalaireAB10X100, main = "instance AB", xlab ="F1", ylab ="F2")
points(bestAB, col='red')
points(frontScalaireAB2X500, col='blue')
legend("topright", legend=c("frontScalaire10X100", "Meilleur front connu", "frontScalaire2X500"),
       col=c("black", "red", "blue"), pch=c(1,1), cex=0.8)

plot(frontScalaireCD10X100, main = "instance CD", xlab ="F1", ylab ="F2")
points(bestCD, col='red')
points(frontScalaireCD2X500, col='blue')
legend("topright", legend=c("frontScalaire10X100", "Meilleur front connu", "frontScalaire2X500"),
       col=c("black", "red", "blue"), pch=c(1,1), cex=0.8)

plot(frontScalaireEF10X100, main = "instance EF", xlab ="F1", ylab ="F2")
points(bestEF, col='red')
points(frontScalaireEF2X500, col='blue')
legend("topright", legend=c("frontScalaire10X100", "Meilleur front connu", "frontScalaire2X500"),
       col=c("black", "red", "blue"), pch=c(1,1), cex=0.8)


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