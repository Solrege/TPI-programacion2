package trabajo_integrador.main;

import java.util.Scanner;

import trabajo_integrador.dao.FichaBibliograficaDAO;
import trabajo_integrador.dao.LibroDAO;

import trabajo_integrador.service.FichaBibliograficaService;
import trabajo_integrador.service.LibroService;

public class AppMenu {

    private final Scanner scanner;
    private final MenuHandler menuHandler;
    private boolean running;

    private final LibroDAO libroDAO = new LibroDAO();
    private final FichaBibliograficaDAO fichaDAO = new FichaBibliograficaDAO();

    private final LibroService libroService = new LibroService(libroDAO, fichaDAO);
    private final FichaBibliograficaService fichaService = new FichaBibliograficaService(fichaDAO);

    public AppMenu() {
        this.scanner = new Scanner(System.in);

        this.menuHandler = new MenuHandler(scanner, libroService, fichaService);

        this.running = true;
    }



    public void run() {
        while (running) {
            try {
                MenuDisplay.mostrarMenuPrincipal();
                int opcion = Integer.parseInt(scanner.nextLine());
                processOption(opcion);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            }
        }

        scanner.close();
    }

    private void processOption(int opcion) {
        switch (opcion) {
            case 1 -> menuHandler.crearLibro();
            case 2 -> menuHandler.listarLibros();
            case 3 -> menuHandler.actualizarLibro();
            case 4 -> menuHandler.eliminarLibro();

            case 5 -> menuHandler.crearFichaBibliografica();
            case 6 -> menuHandler.listarFichasBibliograficas();
            case 7 -> menuHandler.actualizarFichaBibliografica();
            case 8 -> menuHandler.eliminarFichaBibliografica();

            case 9 -> menuHandler.leerLibroPorId();
            case 10 -> menuHandler.leerFichaBibliograficaPorId();

            case 0 -> {
                System.out.println("Saliendo...");
                running = false;
            }

            default -> System.out.println("Opción no válida.");
        }
    }
}
