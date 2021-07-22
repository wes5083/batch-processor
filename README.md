## Download jar into your local

for example local folder name is c:/00_zyw/batch-processor/batch-processor-1.0-SNAPSHOT.jar

## Create the file source folder `data/in` at the same  folder with jar, look like this:

└── batch-processor
    ├── batch-processor-1.0-SNAPSHOT.jar
    └── data
          ├── archive
          ├── error
          ├── in
          └── out

## Run jar

though terminal and into the same folder with jar. (  c:/00_zyw/batch-processor )
run jar:
  java -jar batch-processor-1.0-SNAPSHOT.jar

### Test the result

copy PDF and XML file into the folder `data/in` and validate the output result, include folder `data/in`, `data/out`, `data/error`, `data/archive`
