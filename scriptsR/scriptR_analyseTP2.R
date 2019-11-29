data1 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/premierVoisinAmeliorantSwap_Alea.txt")
data2 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/premierVoisinAmeliorantSwap_HeurConst.txt")


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


