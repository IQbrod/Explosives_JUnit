JAVA = "java"
JAVAC = "javac"
OPENJMLJAR = "../../OpenJML/openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ../bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = rm
JUNIT4 = "../../OpenJML/junit-4.12.jar"
HAMCREST = "../../OpenJML/hamcrest-core-1.3.jar"
JMLRUNTIME = "../../OpenJML/jmlruntime.jar"
JMLCLASSPATH = .:$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH):$(JUNIT4):$(HAMCREST)

run : ../bin/ExplosivesMain.class
	$(JAVA) -cp ../bin:$(JMLCLASSPATH) ExplosivesMain

../bin/Explosives.class : Explosives.java
	$(JMLC) Explosives.java

../bin/ExplosivesMain.class : ../bin/Explosives.class ExplosivesMain.java
	$(JAVAC) -cp ../bin:$(JMLCLASSPATH) -d ../bin  ExplosivesMain.java


../bin/TestExplosivesJUnit4.class : ../bin/Explosives.class TestExplosivesJUnit4.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TestExplosivesJUnit4.java

../bin/TestExplosivesJUnit4Public.class : ../bin/Explosives.class TestExplosivesJUnit4Public.java
	$(JAVAC) -cp ../bin:$(JMLJUNITCLASSPATH) -d ../bin  TestExplosivesJUnit4Public.java

TestExplosivesJUnit4.run : ../bin/TestExplosivesJUnit4.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TestExplosivesJUnit4

TestExplosivesJUnit4Public.run : ../bin/TestExplosivesJUnit4Public.class
	 $(JAVA) -cp ../bin:$(JMLJUNITCLASSPATH) TestExplosivesJUnit4Public

clean : 
	$(RM) ../bin/*.class
