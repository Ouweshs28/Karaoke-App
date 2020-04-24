#!/bin/bash

rm *class

javac --module-path $JAVAFX_HOME --add-modules javafx.controls KaraokeApp.java

javac --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.fxml KaraokeApp.java

java --module-path $JAVAFX_HOME --add-modules javafx.controls KaraokeApp.java

