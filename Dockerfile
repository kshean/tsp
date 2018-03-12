FROM centos:latest

RUN yum install -y java-1.8.0-openjdk && yum install -y epel-release && yum install -y R

RUN R -e "install.packages(c('sp', 'geosphere'), repos='http://cran.r-project.org', INSTALL_opts = c('--no-lock'))"

ADD tsp.jar /java/tsp.jar

ADD ./R/preProcessing.R /R/

ADD run.sh /opt/scripts/

#remove this for final since mounting
COPY ./csv/* /csv/

RUN chmod +x /opt/scripts/run.sh

#ENTRYPOINT ["java", "-jar", "/java/tsp.jar"]

CMD ["/opt/scripts/run.sh"]