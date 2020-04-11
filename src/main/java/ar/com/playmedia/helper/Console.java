package ar.com.playmedia.helper;
import java.util.Scanner;

public class Console{

    public static void clean(){
        System.out.print("\033[H\033[2J");
		System.out.flush();
    }

    public static void showMessage(String message){
        clean();
        Scanner keyboard = new Scanner(System.in);

        underline();
        System.out.println("");
        System.out.println(message);
        System.out.println("\n");
        underline();
        System.out.println("\nPresione enter para continuar");
        
        keyboard.nextLine();
    }

    public static void underline(){
        System.out.println("============================================\n");
    }
}