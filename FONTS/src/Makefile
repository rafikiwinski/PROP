CLASS_INPUT =	./main/domain/classes/Lista_Palabras.java \
 				./main/domain/controllers/CtrlListaPalabras.java \
 				./main/domain/controllers/CtrlTeclado.java \
 				./main/domain/classes/Teclado.java \
 				./main/domain/classes/Alfabeto.java \
 				./main/domain/classes/Hungarian.java \
 				./main/domain/classes/Genetic.java \
 				./main/domain/classes/Population.java \
 				./main/domain/classes/exceptions/*.java \
 				./main/domain/controllers/CtrlAlfabeto.java

 CLASS_INPUT_PRESENTACIO = ./main/presentation/controllers/*.java \
 						  ./main/presentation/views/*.java \
 						  ./main/domain/controllers/*.java \
 						  ./main/domain/classes/*.java \
 						  ./main/domain/classes/exceptions/*.java


 CLASS_OUTPUT =	../../EXE/out/

 JAR_OUTPUT =	../../EXE/

 JUNIT_TESTS = ./test/*.java

 TESTS = ./test/*.java

 JUNIT_JARS = ../../lib/junit-4.12.jar:../../lib/hamcrest-core-1.3.jar

 CLASS_SOURCES =			$(CLASS_OUTPUT)drivers/*.class \
 						$(CLASS_OUTPUT)main/domain/classes/*.class \
 						$(CLASS_OUTPUT)main/domain/controllers/*.class

jarLlista:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverListaPalabras.java
	jar cmvf ./drivers/Lista.mf $(JAR_OUTPUT)DriverListaPalabras.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverListaPalabras.jar

jarTeclado:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverTeclado.java
	jar cmvf ./drivers/Teclado.mf $(JAR_OUTPUT)DriverTeclado.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverTeclado.jar

jarAlfabeto:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverAlfabeto.java
	jar cmvf ./drivers/Alfabeto.mf $(JAR_OUTPUT)DriverAlfabeto.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverAlfabeto.jar

jarDomini:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverDomini.java
	jar cmvf ./drivers/Domini.mf $(JAR_OUTPUT)DriverDomini.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverDomini.jar

jarPresentacion:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT_PRESENTACIO) ./drivers/DriverPresentacion.java
	jar cmvf ./drivers/Presentacion.mf $(JAR_OUTPUT)DriverPresentacion.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverPresentacion.jar

executaDriverLlista:
	java -jar $(JAR_OUTPUT)DriverLlista.jar

executaDriverTeclado:
	java -jar $(JAR_OUTPUT)DriverTeclado.jar

executaDriverAlfabeto:
	java -jar $(JAR_OUTPUT)DriverAlfabeto.jar

executaDriverDomini:
	java -jar $(JAR_OUTPUT)DriverDomini.jar

buildTests:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(TESTS)

TestLista: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestLista

TestTeclado: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestTeclado

TestAlfabeto: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestAlfabeto

TestHungarian: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestHungarian

all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

clean:
	rm -r ../../EXE/out/*

distclean:
	rm -r ../../EXE/*
