TRG_CLS_PATH=target/classes/
PCK_MAIN_NAME=Frequential.app
EXE_NAME=frequentialAnalysis

cryptanalysis: $(EXE_NAME)-1.0.jar
	@echo "The jar file has been created in the current directory."
	@echo "To execute the jar just write : java -jar ./$(EXE_NAME)-1.0.jar"

$(EXE_NAME)-1.0.jar: maven
	@jar --create --file $@ --main-class $(PCK_MAIN_NAME).Main -C $(TRG_CLS_PATH) .
	@echo "jar file created and ready to be executed."

maven: 
	@mvn compile
	@echo "Everything has been compiled"