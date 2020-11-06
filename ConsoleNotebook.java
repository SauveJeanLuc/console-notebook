//Console Notebook
import java.io.IOException;
import java.util.Scanner;
public class ConsoleNotebook {


    public static void main(String[] args) throws IOException, InterruptedException {

            Account account1 = new Account();
            switch (account1.askChoice()){
                case 1:
                    account1.clearConsole();
                    account1.createNewAccount();
                    account1.signIn();

                    switch (account1.askForNotes()){
                        case 1: account1.createNotes();
                    }

                    break;
                case 2:
                    account1.signIn();
                    account1.createNotes();

            }
    }

}
