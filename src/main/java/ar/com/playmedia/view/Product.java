package ar.com.playmedia.view;

import java.util.Scanner;
import java.util.ArrayList;


public class Product {
	private Scanner keyboard;
	private ar.com.playmedia.controller.Product handler;
	private ar.com.playmedia.view.Platform handlerPlatform;

	public Product() {
		keyboard = new Scanner(System.in);
		handler = new ar.com.playmedia.controller.Product();
		handlerPlatform = new ar.com.playmedia.view.Platform();
	}

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
			System.out.println("	1) Agregar Producto");
			System.out.println("	2) Eliminar Producto");
			System.out.println("	3) Modificar Producto");
			System.out.println("	4) Listar Producto");
			System.out.println("	6) Gestionar Plataformas");
			System.out.println();
			System.out.println("	0) Volver al menu principal");
			System.out.println();
			System.out.println("Opcion: ");

			option = Integer.parseInt(keyboard.nextLine());

            switch(option) {
				case 1:
					addProduct();
					break;
				case 2:
					deleteProduct();
					break;
				case 6:
					handlerPlatform.menu();
					break;
				case 0:
					break;

				default:
					System.out.print("Opcion incorrecta! Presione ENTER para continuar");
					keyboard.nextLine();
			}
    	}
	}

	public void addProduct() {
		ar.com.playmedia.model.Product product = new ar.com.playmedia.model.Product();

		Integer other = -1;

		while(other != 0) {
			clearScreen();
			System.out.println("Alta de Producto: ");
			System.out.println("==== == =========");
			System.out.println();
			System.out.println("Ingrese una breve descripcion del producto: ");
			product.setDescription(keyboard.nextLine());

			System.out.println("	Ingrese precio del producto: ");
			product.setPrice(Float.parseFloat(keyboard.nextLine()));

			System.out.println("	Ingrese la cantidad del producto: ");
			product.setQuantity(Integer.parseInt(keyboard.nextLine()));

			System.out.println("	Ingrese categoria del producto: ");
			System.out.println("	1) Consola");
			System.out.println("	2) Accesorios");
			System.out.println("	3) Juegos");
			product.setCategory(Integer.parseInt(keyboard.nextLine()));

			System.out.println("	Ingrese categoria del producto: ");
			System.out.println("	1) Nintendo");
			System.out.println("	2) Playstation");
			System.out.println("	3) Xbox");
			product.setPlatform(Integer.parseInt(keyboard.nextLine()));

			handler.connect();
			handler.insert(product);
			handler.disconnect();

			System.out.println();
			System.out.println("Agregar otro contacto? (0 no / 1 si): ");
			other = Integer.parseInt(keyboard.nextLine());
		}
	}

	public void deleteProduct() {
		Integer other = -1;

		while(other != 0) {
			clearScreen();
			System.out.println("Baja de producto:");
			System.out.println("==== == =========");
			System.out.println();
			System.out.println("	Ingrese Id de producto: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			handler.connect();
			handler.delete(id);
			handler.disconnect();

			System.out.println();
			System.out.println("Eliminar otro producto? (0 no / 1 si): ");
			other = Integer.parseInt(keyboard.nextLine());
		}
	}

}