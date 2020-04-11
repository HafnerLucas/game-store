package ar.com.playmedia.view;

import java.util.Scanner;
import ar.com.playmedia.helper.*;

public class Category {

	static ar.com.playmedia.controller.Category handler;

	public Category(){		
		handler  = new ar.com.playmedia.controller.Category();
	}

	public void menu(){
		Scanner keyboard = new Scanner(System.in);
		Integer option = -1;

        while(option != 0) {
			Console.clean();

			System.out.println("Categorias:");
			Console.underline();
			System.out.println("	1) Agregar Categoría");
			System.out.println("	2) Eliminar Categoría");
			System.out.println("	3) Modificar Categoría");
			System.out.println("	4) Listar Categorías");
			System.out.println();
			System.out.println("	0) Salir");
			System.out.println();
			Console.underline();
			System.out.print("Opcion: ");

			option = Integer.parseInt(keyboard.nextLine());

            switch(option) {
				case 1:
					addCategory();
					break;
				case 2:
					deleteCategory();
					break;
				case 3:
					modifyCategory();
					break;
				case 4:
					listAll();
					break;
				case 0:
					break;

				default:
					System.out.print("Opcion incorrecta! Presione ENTER para continuar");
					keyboard.nextLine();
			}
    	}

	}

	public void addCategory () {
		Scanner keyboard = new Scanner(System.in);
		Integer other = -1;

		while(other != 0) {
			Console.clean();
			System.out.println("Nueva Categoría:");
			Console.underline();
			System.out.println("	Ingrese descripción de la categoría: ");
			
			handler.insert(keyboard.nextLine());

			System.out.println();
			System.out.println("Agregar otra categoria? (0 no / 1 si): ");

			try{
				other = Integer.parseInt(keyboard.nextLine());
			}
			catch(java.lang.NumberFormatException ex){
				Console.showMessage("Debe ingresar un número. Volviendo al menu categorias");
				other = 0;
			}
			
		}

		}
	
	public void deleteCategory (){
		Console.clean();
		System.out.println("Elimine categoría");
		Console.underline();

		Scanner keyboard = new Scanner (System.in);

		try{
			System.out.println("Ingrese id de la categoría a eliminar: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			if(handler.exist(id)){
				handler.delete(id);
				Console.showMessage("Categoría eliminada exitosamente!");
			}
			else{
				Console.showMessage("No se encontro una categoria con el id ingresado.\n Ingrese al listado y verifique");
			}
		}
		catch(java.lang.NumberFormatException ex){
			Console.showMessage("Debe ingresar un número. Volviendo al menu categorias");			
		}
		catch(Exception ex){
			Console.showMessage(ex.getMessage());
		}

	}

	public void modifyCategory(){
		Console.clean();
		System.out.println("Modifica categoría");
		Console.underline();

		Scanner keyboard = new Scanner (System.in);

		try{
			System.out.println("Ingrese id de la categoría a modificar: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			if(handler.exist(id)){
				System.out.println("Ingrese la nueva descripción de la categoría: ");
				handler.modify(id,keyboard.nextLine());
				Console.showMessage("Categoría modificada exitosamente!");
			}

		}
		catch(java.lang.NumberFormatException ex){
			Console.showMessage("Debe ingresar un número. Volviendo al menu categorias");			
		}
		catch(Exception ex){
			Console.showMessage(ex.getMessage());
		}

	}
	
	public void listAll(){		
		Console.clean();
		System.out.println("Listado categorías");
		Console.underline();

		handler.search("");

		Console.underline();
		System.out.println("Presione enter para continuar");

		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();		
	}
	}

	


