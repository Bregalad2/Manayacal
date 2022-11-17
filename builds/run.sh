#!/bin/bash

FOLDERS=*/
echo "jarring $FOLDERS"
zip -r latest-build.jar $FOLDERS
java -jar latest-build.jar -XstartOnFirstThread

