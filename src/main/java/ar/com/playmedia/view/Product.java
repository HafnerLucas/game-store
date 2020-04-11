package ar.com.playmedia.view;

import java.util.Scanner;
import java.util.ArrayList;


public class Product {
	private Scanner keyboard;
	private ar.com.playmedia.controller.Product handler;
	private ar.com.playmedia.view.Platform handlerPlatform;
	private ar.com.playmedia.view.Category handlerCategory;

	public Product() {
		keyboard = new Scanner(System.in);
		handler = new ar.com.playmedia.controller.Product();
		handlerPlatform = new ar.com.playmedia.view.Platform();
		handlerCategory = new ar.com.playmedia.view.Category();
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
			System.out.println("	5) Gestionar Categorias");
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
				case 3:
					updateProduct();
					break;
				case 4:
					searchProduct();
					break;
				case 5:
					handlerCategory.menu();
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

	public void searchProduct() {
		clearScreen();
		System.out.println("Listado de Productos:");
		System.out.println("======= == ==========");
		System.out.println();
		System.out.println("	Ingrese descripcion o parte de la descripcion del producto");
		String filter = keyboard.nextLine();

		ArrayList<ar.com.playmedia.model.Product> productList;
		handler.connect();

		if(filter.isEmpty())
			productList = handler.search();
		else
			productList = handler.search(filter);
		
		handler.disconnect();

		clearScreen();
		System.out.println("Listado de Productos:");
		System.out.println("======= == ==========");
		System.out.println();

		for(ar.com.playmedia.model.Product product : productList) 
			printProduct(product);

		System.out.println();
		System.out.println("Presione ENTER para continuar...");
		keyboard.nextLine();
	}


	public void printProduct(ar.com.playmedia.model.Product product) {
		System.out.println(String.format("ID: %s", product.getId()));
		System.out.println(String.format("Descripcion: %s", product.getDescription()));
		System.out.println(String.format("Precio: %s", product.getPrice()));
		System.out.println(String.format("Cantidad: %s", product.getQuantity()));
		System.out.println();
	}

	public void updateProduct() {
		Integer identificationOK = 0;

		ar.com.playmedia.model.Product product = null;

		while(identificationOK != 1) {
			clearScreen();
			System.out.println("Modificacion de Productos:");
			System.out.println("============ == ==========");
			System.out.println();
			System.out.println("	Ingrese ID del Producto: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			handler.connect();
			product = handler.identify(id);
			handler.disconnect();

			System.out.println();
			printProduct(product);

			System.out.println("Es este el contacto deseado? (0 NO / 1 SI): ");
			identificationOK = Integer.parseInt(keyboard.nextLine());
		}

		Integer option = -1;

		while(option != 0) {
			clearScreen();
			System.out.println("Modificando Producto:");
			System.out.println("=========== =========");
			System.out.println();
			printProduct(product);
			System.out.println();
			System.out.println("	1) Modificar Descripcion");
			System.out.println("	2) Modificar Precio");
			System.out.println("	3) Modificar Cantidad");
			System.out.println("	4) Modificar Categoria");
			System.out.println("	5) Modificar Plataforma");
			System.out.println();
			System.out.println("	0) Salir");
			System.out.println();
			System.out.println("Elija dato a modificar: ");
			option = Integer.parseInt(keyboard.nextLine());

			if(option == 0)
				break;

			System.out.println("Nuevo valor: ");
			String newValue = keyboard.nextLine();

			handler.connect();

			switch(option) {
				case 1:
					product = handler.setDescription(product, newValue);
					break;

				case 2:
					product = handler.setPrice(product, Float.parseFloat(newValue));
					break;

				case 3:
					product = handler.setQuantity(product, Integer.parseInt(newValue));
					break;

				case 4:
					product = handler.setCategory(product, Integer.parseInt(newValue));
					break;
				
				case 5:
				
					product = handler.setPlatform(product, Integer.parseInt(newValue));
					break;
				default:
					System.out.println("Opcion incorrecta! Presione ENTER para continuar...");
			}

			handler.disconnect();
		}
	}

}