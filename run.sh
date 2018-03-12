#!/bin/bash
set -e
set -u
set -o pipefail

log_opts=${LOGGER_OPTS:--s -t $0}

logger ${log_opts} "preprocessing the file and creating distance matrix"
Rscript /R/preProcessing.R

logger ${log_opts} "sorting master file by cluster number"
sort -k53,53 -k1,1  -n /csv/master.csv > /csv/sorted_master.csv

sed -n 1,10p /csv/sorted_master.csv > /csv/first_range.csv
sed -n 11,20p /csv/sorted_master.csv > /csv/second_range.csv
sed -n 21,30p /csv/sorted_master.csv > /csv/third_range.csv
sed -n 31,40p /csv/sorted_master.csv > /csv/fourth_range.csv
sed -n 41,51p /csv/sorted_master.csv > /csv/fifth_range.csv


logger ${log_opts} "preparing files for tsp"
line=$(head -n 1 /csv/master.csv)

echo $line | cat - /csv/second_range.csv > temp && mv temp /csv/second_range.csv
echo $line | cat - /csv/third_range.csv > temp && mv temp /csv/third_range.csv
echo $line | cat - /csv/fourth_range.csv > temp && mv temp /csv/fourth_range.csv
echo $line | cat - /csv/fifth_range.csv > temp && mv temp /csv/fifth_range.csv


logger ${log_opts} "running tsp"
java -jar /java/tsp.jar