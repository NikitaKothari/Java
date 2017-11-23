G32 - Vinaya Ganiga, Pradeep Nair, Mohanakrishna Vanamala Hariprasad, Nikita Kothari

All files in the submission are in package cs6301.g32

LP2.java: Driver for the project

Euler.java: Contains the main code for the program, makes use of the following files:
	StronglyConnected.java, PlusVertex.java - to find SCC
	GraphAlgorithm - for maintaining parellel array
	Graph.java and ArrayIterator.java

Please use files from the submission since we would have to make some members of GraphAlgorithm public if kept in g00 package, so we kept all files under one package.

Note: on one of our systems lp2-big.txt needed atleast 64M stack size
