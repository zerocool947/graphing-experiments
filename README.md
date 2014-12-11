graph-experiments
=================

A sandbox for experimenting with graph APIs

###Required libraries:

TestFX 4.0.0-SNAPSHOT (TestFX)

TestFX 3.0 (TestFX) [Legacy, converting to 4.x]

###Importing Project:

Import the project as a gradle project in intellij, that should set you up with all the tasks and whatnot that you need. If you want to import it as a module, add "graphing-experiments" to you settings.gradle and you should be all set.

###Running Tests:

For now, I recommend you make a custom JUnit run configuration that excludes `JavaFXStageTest`.  Because of a deadlock in TestFX4.0-Beta, that test blocks the rest. 
