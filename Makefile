# Makefile for Deadwood
# Ryan Lingg and Michael Albert

# Instructions:
# 1) To build the program navigate to "Deadwood-Csci-345/" directory and use command "make".
# 2) To run the program cd to "Deadwood-Csci-345/build/" and use command "java Deadwood".

# Environment variables
JC = javac

# Make commands
compile: clean
	mkdir build
	$(JC) -d build **/*.java

clean: 
	rm -rf build
