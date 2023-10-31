SRC_PATH=src/main/java/
TRG_CLS_PATH=target/classes/
CTRL_PATH=Frequential/controller/
VIEW_PATH=Frequential/view/
MAIN_PATH=Frequential/app/
MODEL_PATH=Frequential/model/
PCK_MAIN_NAME=Frequential.app
EXE_NAME=frequentialAnalysis

cryptanalysis: $(EXE_NAME)-1.0.jar
	@echo "The jar file has been created in the current directory."
	@echo "To execute the jar just write java -jar ./$(EXE_NAME)-1.0.jar"

$(EXE_NAME)-1.0.jar: compile
	@jar --create --file $@ --main-class $(PCK_MAIN_NAME).Main -C $(TRG_CLS_PATH) .
	@echo "jar file created and ready to be executed."

compile: 
	@javac -d ./target/classes $(SRC_PATH)$(VIEW_PATH)*.java $(SRC_PATH)$(MODEL_PATH)*.java $(SRC_PATH)$(CTRL_PATH)*.java $(SRC_PATH)$(MAIN_PATH)*.java 
	@echo "Everything has been compiled"