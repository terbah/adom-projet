
setwd("C:/Users/aghil/Documents/M2MIAGE/ADOM/adom-projet/Ressources/fichiersGeneres/tp3")

naifAlea <- read.table("naifAlea.txt")
naifHeuristic <- read.table("naifHeuristic.txt")
memetiqueAlea <- read.table("memetiqueAlea.txt")
memetiqueHeuristic <- read.table("memetiqueHeuristic.txt")

mean(naifAlea[, 1])
median(naifAlea[, 1])
min(naifAlea[, 1])
max(naifAlea[, 1])

mean(naifHeuristic[, 1])
median(naifHeuristic[, 1])
min(naifHeuristic[, 1])
max(naifHeuristic[, 1])

boxplot (naifAlea[, 1])

boxplot (memetiqueHeuristic[, 1], memetiqueAlea[,1], 
         names = c("memetiqueHeuristic", "memetiqueAlea"), ylab="Rapport")


boxplot(naifAlea[, 1], naifHeuristic[, 1], ylab = "Rapport", 
        names = c("naifAlea", "naifHeuristic"))


mean(memetiqueAlea[, 1])
median(memetiqueAlea[, 1])
min(memetiqueAlea[, 1])
max(memetiqueAlea[, 1])

mean(memetiqueHeuristic[, 1])
median(memetiqueHeuristic[, 1])
min(memetiqueHeuristic[, 1])
max(memetiqueHeuristic[, 1])
