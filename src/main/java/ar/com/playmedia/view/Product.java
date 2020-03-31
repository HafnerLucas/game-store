package ar.com.playmedia.model;

public class Product {

    public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void mainMenu() {
		Integer option = -1;

        while(option != 0) {
			clearScreen();

			System.out.println("Productos:");
			System.out.println("=========");
			System.out.println("	1) Agregar Articulo");
			System.out.println("	2) Eliminar Articulo");
			System.out.println("	3) Modificar Articulo");
			System.out.println("	4) Listar Articulo");
			System.out.println();
			System.out.println("	0) Salir");
			System.out.println();
			System.out.print("Opcion: ");

			option = Integer.parseInt(keyboard.nextLine());

            switch(option) {
				case 0:
					break;

				default:
					System.out.print("Opcion incorrecta! Presione ENTER para continuar");
					keyboard.nextLine();
			}

    }


}