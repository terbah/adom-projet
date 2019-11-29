library('mco')

setwd("/home/m2miage/lela/Documents/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/fichiersGeneres/tp4")
getwd()

off <- as.matrix(read.table("compOffLine.txt", dec="."))
on <- as.matrix(read.table("compOnLine.txt", dec="."))

#plot.new()
plot(off, main = "comparaisons entre le filtre off-Line et on-Line", xlab ="Nombre de solutions", ylab ="Complexit? (en nombre de comparaisons)", type = "b", pch=19)
lines(on, col='red', pch=19)
legend("topleft", legend=c("Filtre off-line", "Filtre on-line"),
       col=c("black", "red"), lty=2:1, cex=0.8)