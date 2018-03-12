input <- read.csv('/csv/input.csv', header = TRUE, col.names = c("name", "address", "lat", "long"))

library(sp)
library(geosphere)
x <- input$lat
y <- input$long

xy <- SpatialPointsDataFrame(matrix(c(x,y), ncol=2), data.frame(ID=seq(1:length(x))), proj4string=CRS("+proj=longlat +ellps=WGS84 +datum=WGS84"))

mdist <- distm(xy)


hc <- hclust(as.dist(mdist), method="complete")
xy$clust <- cutree(hc, k=4)

temp <- cbind(mdist, xy$clust)

data <- data.frame(xy$ID, input$name, input$address);
write.csv(data, file="/csv/reference.csv", quote = FALSE, row.names = FALSE);
write.table(temp, file="/csv/master.csv", quote = FALSE, col.names = FALSE)