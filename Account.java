import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Account {
    private static String userName;
    private static char[] password;
    private static String notes;
    private static boolean authorised;
    public static ProjectDatabase dataInsert = new ProjectDatabase();

    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static int askChoice(){
        Scanner scan = new Scanner(System.in);

        System.out.println("" +
                "==================================\n" +
                "|| New to This System?          ||\n" +
                "|| 1. Create Account            ||\n" +
                "|| Already signed in?           ||\n" +
                "|| 2. Sign In                   ||\n" +
                "||------- Console Notebook -----||\n" +
                "||        Copyright 2020        ||\n" +
                "==================================");
        System.out.print("Your choice: ");
        int choice = scan.nextInt();
        return choice;
    }
    public static int askForNotes(){
        Scanner scan = new Scanner(System.in);

        System.out.println("" +
                "==================================\n" +
                "|| Your Notes in One Place      ||\n" +
                "|| 1. Write some notes          ||\n" +
                "|| Want to see what u've got?   ||\n" +
                "|| 2. Read my notes             ||\n" +
                "||------- Console Notebook -----||\n" +
                "||        Copyright 2020        ||\n" +
                "==================================");
        System.out.print("Your choice: ");
        int choice = scan.nextInt();
        return choice;
    }
    public static void askCredentials(){
        Console cons;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Input your username: ");
        userName = scan.nextLine();
        password = null;
        if( (cons = System.console()) != null ) {
                password = cons.readPassword("Please input your password:");
        }
        else {
           throw new RuntimeException("Can't get password,.. No console");
        }
    }
    public static void createNotes(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome Now you can Write and keep track of your Notes :)");
        System.out.print("Write: ");
        notes = scan.nextLine();
        dataInsert.insertNotes(notes);
        System.out.println("Notes Added Successfully");
    }
    public static void createNewAccount() throws IOException, InterruptedException {
        System.out.println("============================");
        System.out.println("||  Creating new Account  ||");
        System.out.println("");
        askCredentials();
        //Check if UserName Exists, Else Store it in DB
        if(!dataInsert.userNameExists(userName)) {
            //Store Password in DB
            dataInsert.insert(userName, new String(password));
        }
        else {
            clearConsole();
            System.out.println("Sorry the userName Already Exists :(");
            System.out.println("Please try again,...");
            createNewAccount();
        }
    }
    public static void signIn() throws IOException, InterruptedException {
        System.out.println("============================");
        System.out.println("||          Sign In       ||");
        System.out.println("");
        askCredentials();
        //Check whether userName matches with password
        ProjectDatabase check = new ProjectDatabase();
        if(check.checkCredentials(userName,new String(password))) {
            authorised = true;
        }
        else {
            clearConsole();
            System.out.println("UserName or Password not Correct :(");
            System.out.println("Please Try again,...");
            signIn();
            authorised = false;
        }
    }

    //Getters and Setters
    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Account.userName = userName;
    }

    public static String getPassword() {
        return new String(password);
    }

    public static void setPassword(char[] password) {
        Account.password = password;
    }

    public static boolean isAuthorised() {
        return authorised;
    }

    public static void setAuthorised(boolean authorised) {
        Account.authorised = authorised;
    }
}
