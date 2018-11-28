# Makefile for Deadwood
# Ryan Lingg and Michael Albert

# Instructions:
# To build and run the game, navigate to "Deadwood-Csci-345/" directory then use the command "make". Then navigate to the build folder and use the command "java Deadwood 'player_number' where 'player_number' is an integer between 2-3. 

# Environment variables
JC = javac

# Make commands
compile: clean
	mkdir build
	$(JC) -Xlint -d build **/*.java

clean:
	rm -rf build

run: compile
	cd build && java Deadwood
