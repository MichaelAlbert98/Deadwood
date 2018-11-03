JC = javac
JVM = java



compile: clean
	mkdir build
	$(JC) -d build **/*.java

jcr: compile
	$(JVM) */build/Deadwood

clean: 
	rm -rf build
