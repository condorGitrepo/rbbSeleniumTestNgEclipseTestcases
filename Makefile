# Makefile
 
# PHONY := target is not associated with a physical file 
## (e.g.: the target "clean" is independent from the "file clean", if it exists)
.PHONY: all initBuild build clean info showOwnTasks specificTask
  
# Define Variables
G=gradle
GW=./gradlew
GGWOPTS=-i



# --- Makefile Logic ---

all: clean build


#touch .project ## Is not necessary anymore for configure-->converting into Gradle STS 
## because of "eclipseProject"

all: clean initBuild build

clean:
	${G} ${GGWOPTS} cleanEclipse
	${G} ${GGWOPTS} clean

initBuild:
	${G} ${GGWOPTS} build
	${G} ${GGWOPTS} wrapper # Get wrapper libs

build:
	${GW} ${GGWOPTS} eclipse # Download Jars
	${GW} ${GGWOPTS} assemble # Java files into JAR-file  
	${GW} ${GGWOPTS} eclipseProject

test:
	${GW} ${GGWOPTS} clean
	${GW} ${GGWOPTS} test

info:
	${GW} ${GGWOPTS} project

showOwnTasks:
	${GW} ${GGWOPTS} -q tasks --all | grep --color=always -A 100 'MGE tasks' | grep --color=always '#' 

specificTask:
	${GW} ${GGWOPTS} printSomething
	#${G} ${GGWOPTS} -q tasks printSomething 


# --- --- ---

### Usefull knowledge:
# If startuptime is slow then use: # export GRADLE_OPTS=-Xmx1024m 
# This defines a higher heap size OR loacally in gradle.properties: 
# 	org.gradle.jvmargs=-Xms2g -Xmx4g -XX\:MaxHeapSize\=3g

### Gradle shorts:
# gradle -q build    invokes checks and assemble tasks

### Create JAR-File
## Create manifest.mf:
# Manifest-version: 1.0
# Main-Class: Test
## Create Java class File ...
## Compile: # javac Test.java
## Jar:     # jar cfm test.jar manifest.mf Test.class
## Execute: # java -jar test.jar


