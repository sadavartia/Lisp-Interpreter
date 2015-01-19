# Java compiler
JAVAC = /usr/local/jdk1.7.0_51/bin/javac

# Java compiler flags
JAVAFLAGS = -g 

# Creating a .class file
COMPILE = $(JAVAC) $(JAVAFLAGS)

# One of these should be the "main" class listed in Runfile
CLASS_FILES = myInterpreter.class myScanner.class Node.class tok.class computeTree.class helpers.class DList.class

# The first target is the one that is executed when you invoke
# "make". 

all: $(CLASS_FILES) 

# The line describing the action starts with <TAB>
%.class : %.java
	$(COMPILE) $<




