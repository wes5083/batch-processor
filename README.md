# Batch process: monitor XML file and move PDF and XML file into different folder

## Download jar into your local

download the jar from [target/batch-processor-1.0-SNAPSHOT.jar] into your local folder.
the jar folder for example: 
  c:/batch-processor/batch-processor-1.0-SNAPSHOT.jar

## Create the file source folder `data/in` at the same  folder with jar, 

folder look like this:
![image](https://user-images.githubusercontent.com/67679233/126707090-057cb4fb-b6ff-4288-8ec2-ffb2cb97a032.png)

## Run jar

though terminal and into the same folder with jar. (  c:/batch-processor/ )
run jar:
  java -jar batch-processor-1.0-SNAPSHOT.jar

## Test the result

copy PDF and XML file into the folder `data/in` and validate the output result, include folder `data/in`, `data/out`, `data/error`, `data/archive`
