
data1 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/ville1.txt")
data2 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/ville2.txt")
data3 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/ville3.txt")
data4 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/ville4.txt")
data5 <- read.table("/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/ville5.txt")

mean(data1[, 1])
median(data1[, 1])
min(data1[, 1])
max(data1[, 1])

mean(data2[, 1])
median(data2[, 1])
min(data2[, 1])
max(data2[, 1])

boxplot(data1[, 1], data2[, 1], data3[, 1],data4[, 1],data5[, 1], ylab = "Rapport", 
        names = c("ville 1", "ville 2", "ville 3", "ville 4", "ville 5"))


mean(data3[, 1])
median(data3[, 1])
min(data3[, 1])
max(data3[, 1])

mean(data4[, 1])
median(data4[, 1])
min(data4[, 1])
max(data4[, 1])

mean(data5[, 1])
median(data5[, 1])
min(data5[, 1])
max(data5[, 1])