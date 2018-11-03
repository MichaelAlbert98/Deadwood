# Makefile for Deadwood
# Ryan Lingg and Michael Albert
# To run the program cd to Deadwood-Csci-345/build and use command "java Deadwood".

# Environment variables
JC = javac
JVM = java

# Make commands
compile: clean
	mkdir build
	$(JC) -d build **/*.java

clean: 
	rm -rf build
