#!/bin/bash

javac -cp "src/test/resources/junit-4.12.jar:src/test/resources/hamcrest-core-1.3.jar:/classes" src/test/java/is/ru/stringcalculator/*.java -d classes
