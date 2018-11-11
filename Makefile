# Makefile for Deadwood
# Ryan Lingg and Michael Albert

# Instructions:
# To build and run the game, navigate to "Deadwood-Csci-345/" directory then use the command "make run".

# Environment variables
JC = javac

# Make commands
compile: clean
	mkdir build
	$(JC) -d build **/*.java

clean: 
	rm -rf build

run: compile
	cd build && java Deadwood
