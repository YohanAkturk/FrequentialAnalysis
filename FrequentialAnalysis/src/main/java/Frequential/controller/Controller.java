package Frequential.controller;

import Frequential.model.CipherModel;
import Frequential.view.View;

/**
 * @author 56133 Leong Paeg-Hing
 *         56514 Akturk Yohan
 */
public class Controller {
    
    private boolean loop;
    private CipherModel cypher;
    private View view;
    
    public Controller() {
        this.loop = true;
        this.cypher = new CipherModel();
        this.view = new View();
    }
    
    public void run() {
        this.view.displayWelcomeMessage();
        while(loop) {
            try {
                String command = this.view.askUser();
                whichCommand(command);
            } catch(Exception ex) {
                this.view.displayError(ex.getMessage());
            }
        }
    }
    
    public void whichCommand(String command) {
        String[] arguments = command.split("\\s+");
        if(arguments.length < 1){
            this.view.displayError("There is no arguments");
        }else if(arguments[0].equalsIgnoreCase("dir")) {
            this.view.displayCurrentDirectory();
        }else if(arguments[0].equalsIgnoreCase("help")) {
            whichHelp(arguments);
        }else if(arguments[0].equalsIgnoreCase("exit")){
            this.loop = false;
            this.view.displayGoodbyeMessage();
        }else {
            this.cypher.whichCipher(arguments);
        }
    }
    
    public void whichHelp(String[] arguments) {
        if(arguments.length < 2) {
            this.view.displayError("The help command is not correct.");
        }else {
            this.view.displayAllOption(arguments[1]);
        }
    }
}
