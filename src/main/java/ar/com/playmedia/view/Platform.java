package ar.com.playmedia.view;

import java.util.Scanner;
import ar.com.playmedia.helper.*;

public class Platform {

	static ar.com.playmedia.controller.Platform handler;

	public Platform(){		
		handler  = new ar.com.playmedia.controller.Platform();
	}

	public void menu(){
		Scanner keyboard = new Scanner(System.in);
		Integer option = -1;

        while(option != 0) {
			Console.clean();

			System.out.println("Plataformas:");
			Console.underline();
			System.out.println("	1) Agregar Plataforma");
			System.out.println("	2) Eliminar Plataforma");
			System.out.println("	3) Modificar Plataforma");
			System.out.println("	4) Listar Plataformas");
			System.out.println();
			System.out.println("	0) Volver al menu principal");
			System.out.println();
			Console.underline();
			System.out.print("Opcion: ");

			option = Integer.parseInt(keyboard.nextLine());

            switch(option) {
				case 1:
					addPlatform();
					break;
				case 2:
					deletePlatform();
					break;
				case 3:
					modifyPlatform();
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

	public void addPlatform () {
		Scanner keyboard = new Scanner(System.in);
		Integer other = -1;

		while(other != 0) {
			Console.clean();
			System.out.println("Nueva Plataforma:");
			Console.underline();
			System.out.println("	Ingrese descripción de la Plataforma: ");
			
			handler.insert(keyboard.nextLine());

			System.out.println();
			System.out.println("Agregar otra plataforma? (0 no / 1 si): ");

			try{
				other = Integer.parseInt(keyboard.nextLine());
			}
			catch(java.lang.NumberFormatException ex){
				Console.showMessage("Debe ingresar un número. Volviendo al menu Plataformas");
				other = 0;
			}
			
		}

		}
	
	public void deletePlatform (){
		Console.clean();
		System.out.println("Elimine Plataforma");
		Console.underline();

		Scanner keyboard = new Scanner (System.in);

		try{
			System.out.println("Ingrese id de la Plataforma a eliminar: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			if(handler.exist(id)){
				handler.delete(id);
				Console.showMessage("Plataforma eliminada exitosamente!");
			}
			else{
				Console.showMessage("No se encontro una plataforma con el id ingresado.\n Ingrese al listado y verifique");
			}
		}
		catch(java.lang.NumberFormatException ex){
			Console.showMessage("Debe ingresar un número. Volviendo al menu Plataformas");			
		}
		catch(Exception ex){
			Console.showMessage(ex.getMessage());
		}

	}

	public void modifyPlatform(){
		Console.clean();
		System.out.println("Modifica Plataforma");
		Console.underline();

		Scanner keyboard = new Scanner (System.in);

		try{
			System.out.println("Ingrese id de la Plataforma a modificar: ");
			Integer id = Integer.parseInt(keyboard.nextLine());

			if(handler.exist(id)){
				System.out.println("Ingrese la nueva descripción de la Plataforma: ");
				handler.modify(id,keyboard.nextLine());
				Console.showMessage("Plataforma modificada exitosamente!");
			}

		}
		catch(java.lang.NumberFormatException ex){
			Console.showMessage("Debe ingresar un número. Volviendo al menu Plataformas");			
		}
		catch(Exception ex){
			Console.showMessage(ex.getMessage());
		}

	}
	
	public void listAll(){		
		Console.clean();
		System.out.println("Listado Plataformas");
		Console.underline();

		handler.search("");

		Console.underline();
		System.out.println("Presione enter para continuar");

		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();		
	}
	}

	


