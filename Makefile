# Makefile
 
# PHONY := target is not associated with a physical file 
## (e.g.: the target "clean" is independent from the "file clean", if it exists)
.PHONY: all build wrap clean
  
# Define Variables
G=gradle
GW=./gradlew
GGWOPTS=

MAINNAME=


# --- Makefile Logic ---

all: clean build wrap

bulid:
	${G} ${GGWOPTS} build
	# Get wrapper libs
	${G} ${GGWOPTS} wrapper

wrap:
	${GW} eclipse assemble

clean:
	${G} ${GGWOPTS} cleanEclipse
	${G} ${GGWOPTS} assemble
	${G} ${GGWOPTS} clean


# ----------------------

