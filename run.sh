#!/bin/bash

echo Building...
rm /Users/eddylabadorf/Documents/GitHub/manayacal/desktop/build/libs/desktop-*.jar | grep wololo
rm hs_err_pid* | grep wololo
./gradlew createJar | grep error
echo Build complete! launching...
java -XstartOnFirstThread -jar /Users/eddylabadorf/Documents/GitHub/manayacal/desktop/build/libs/desktop-*.jar
echo Done!
