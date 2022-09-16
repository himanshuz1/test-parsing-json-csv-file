# test-parsing-json-csv-file


step 1 :- suppose there are two file which is in download folder of local machine 

as path for the file as :- 
a)/home/himanshu/Downloads/order1.csv 
b)/home/himanshu/Downloads/order2.json

step 2:-mvn clean install

Please run this command inside the project folder where we have pom.xml file is present.

mvn clean install tells Maven to do the clean phase in each module before running the install phase for each module.

step 3:- java -jar test-parse-file-0.0.1-SNAPSHOT.jar /home/himanshu/Downloads/order2.json,/home/himanshu/Downloads/order1.csv

Please run this command after mvn clean install which will create build for the spring boot application and produces jar file 



