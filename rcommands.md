
1. import csv

```
test <- read.csv('test.csv', header = TRUE, col.names = c("name", "address", "lat", "long") )
```

2. create distance matrix

```
library(sp)
library(rgdal)
library(geosphere)
x <- test$lat
y <- test$long

xy <- SpatialPointsDataFrame(matrix(c(x,y), ncol=2), data.frame(ID=seq(1:length(x))), proj4string=CRS("+proj=longlat +ellps=WGS84 +datum=WGS84"))

mdist <- distm(xy)

```


hc <- hclust(as.dist(mdist), method="complete")
xy$clust <- cutree(hc, k=4)

temp <- cbind(mdist, xy$clust)

3. export distance matrix 

```



```
data <- data.frame(xy$ID, test$name, test$address);
write.csv(data, file="reference.csv", quote = FALSE, row.names = FALSE);
write.table(temp, file="master.csv", quote = FALSE, col.names = FALSE)


TODO:
add start node to csv


y<-dist(x)
clust<-hclust(y)
groups<-cutree(clust, k=3)
x<-cbind(x,groups)
now you will get for each record, the cluster group. You can subset the dataset as well:

x1<- subset(x, groups==1)
x2<- subset(x, groups==2)
x3<- subset(x, groups==3)