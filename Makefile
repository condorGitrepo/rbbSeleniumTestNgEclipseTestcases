# Makefile
 
# PHONY := target is not associated with a physical file 
## (e.g.: the target "clean" is independent from the "file clean", if it exists)
.PHONY: all build clean info ownTasks specificTask
  
# Define Variables
G=gradle
GW=./gradlew
GGWOPTS=
#-i

MAINNAME=


# --- Makefile Logic ---

all: clean build


clean:
	touch .project
	${G} ${GGWOPTS} cleanEclipse
	${G} ${GGWOPTS} assemble
	${G} ${GGWOPTS} clean

build:
	${G} ${GGWOPTS} build
	${G} ${GGWOPTS} wrapper # Get wrapper libs
	${GW} ${GGWOPTS} eclipse assemble # Download Chars

info:
	${GW} ${GGWOPTS} project

ownTasks:
	${GW} ${GGWOPTS} -q tasks --all | grep --color=always -A 100 'MGE tasks' | grep --color=always '#' 

specificTask:
	${GW} printSomething
	#${G} ${GGWOPTS} -q tasks printSomething 


# ----------------------

### Usefull knowledge:
# If startuptime is slow then use: # export GRADLE_OPTS=-Xmx1024m 
# This defines a higher heap size OR loacally in gradle.properties: 
# 	org.gradle.jvmargs=-Xms2g -Xmx4g -XX\:MaxHeapSize\=3g




