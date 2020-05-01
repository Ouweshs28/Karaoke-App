#!/bin/bash

rm *.class

javac --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.graphics,javafx.media  KaraokeApp.java

java --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.graphics,javafx.media KaraokeApp


