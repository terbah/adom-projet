#data1 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/premierVoisinAmeliorantSwap_Alea.txt")
#data2 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/premierVoisinAmeliorantSwap_HeurConst.txt")

data1 <- read.table("D:/Malik/ECOLE/Lille1/M2 (2019-2020)/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/fichiersGeneres/tp2/premierVoisinAmeliorantTwoOpt_Alea.txt")
data2 <- read.table("D:/Malik/ECOLE/Lille1/M2 (2019-2020)/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/fichiersGeneres/tp2/premierVoisinAmeliorantTwoOpt_HeurConst.txt")


mean(data1[, 1])
median(data1[, 1])
min(data1[, 1])
max(data1[, 1])

mean(data2[, 1])
median(data2[, 1])
min(data2[, 1])
max(data2[, 1])

boxplot(data1[, 1], data2[, 1], ylab = "Rapport", 
        names = c("Alea", "HeurConst"))


