JFLAGS = -g
JC = javac --module-path '$JAVAFX_HOME' --add-modules javafx.controls,javafx.graphics,javafx.media

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  AddSong.java \
		  FileManagement.java \
		  HashST.java \
		  KaraokeApp.java \
		  KaraokeMediaplayer.java \
		  MessageBox.java \
		  PlayList.java \
		  Quit.java \
		  Song.java \
		  ViewAllSongs.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java --module-path $JAVAFX_HOME --add-modules javafx.controls,javafx.graphics,javafx.media KaraokeApp