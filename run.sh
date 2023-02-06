#!/bin/bash

echo Building...
rm /Users/eddylabadorf/Documents/GitHub/manayacal/desktop/build/libs/desktop-*.jar
rm hs_err_pid*
./gradlew createJar | grep error
echo Build complete! launching...
java -XstartOnFirstThread -jar /Users/eddylabadorf/Documents/GitHub/manayacal/desktop/build/libs/desktop-*.jar
echo Done!
