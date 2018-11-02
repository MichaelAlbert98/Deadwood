compile: clean
	mkdir build
	javac -d build **/*.java

clean: 
	rm -rf build