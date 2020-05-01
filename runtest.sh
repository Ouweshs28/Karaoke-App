#!/bin/bash

javac --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.graphics,javafx.media *.java

java --module-path $JAVAFX_HOME --add-modules javafx.controls org.junit.runner.JUnitCore KaraokeAppTest