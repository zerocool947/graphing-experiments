graph-experiments
=================

A sandbox for experimenting with graph APIs

###Required libraries:

TestFX 4.0.0-SNAPSHOT (TestFX)

TestFX 3.0 (TestFX) [Legacy, converting to 4.x]

###Running Tests:

For now, I recommend you make a custom JUnit run configuration that excludes `JavaFXStageTest`.  Because of a deadlock in TestFX4.0-Beta, that test blocks the rest. 
