package trabajo_integrador.main;

import trabajo_integrador.models.Libro;
import java.util.List;
import java.util.Scanner;
import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Idioma;
import trabajo_integrador.service.LibroService;
import trabajo_integrador.service.FichaBibliograficaService;

public class MenuHandler {
  
    private final Scanner scanner;
    private final LibroService libroService;
    private final FichaBibliograficaService fichaBibliograficaService;

    public MenuHandler(Scanner scanner, LibroService libroService, FichaBibliograficaService fichaBibliograficaService) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner no puede ser null");
        }
        if (libroService == null) {
            throw new IllegalArgumentException("LibroService no puede ser null");
        }
         if (fichaBibliograficaService == null) {
            throw new IllegalArgumentException("FichaBibliograficaService no puede ser null");
        }
        this.scanner = scanner;
        this.libroService = libroService;
        this.fichaBibliograficaService = fichaBibliograficaService;
    }


    // --- MÉTODOS DE VALIDACIÓN  ---
    public int leerAnioEdicion() {
        int anioEdicion;
        int anioActual = java.time.Year.now().getValue();

        while (true) {
            System.out.print("Año edición: ");
            String input = scanner.nextLine().trim();

            try {
                anioEdicion = Integer.parseInt(input);

                if (anioEdicion < 1500 || anioEdicion > anioActual) {
                    System.out.println("❌ El año de edición debe estar entre 1500 y " + anioActual);

                    continue;
                }

                return anioEdicion;

            } catch (NumberFormatException e) {
                System.out.println("⚠ Debe ingresar un número válido.");
            }
        }
    }


//Metodo CRUD: Libro

    //Crear Libro
    public void crearLibro() {
        try {
            System.out.println("=== Crear Libro ===");

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Autor: ");
            String autor = scanner.nextLine().trim();

            System.out.print("Editorial: ");
            String editorial = scanner.nextLine().trim();

            int anioEdicion = leerAnioEdicion();

            // FICHA OBLIGATORIA
            System.out.println("Debe crear una Ficha Bibliográfica para este libro.");
            FichaBibliografica ficha = crearFicha();  // nunca debe retornar null

            if (ficha == null) {
                System.err.println("Error: No se pudo crear la ficha. Libro cancelado.");

                return;
            }

            Libro libro = new Libro(titulo, autor, editorial, anioEdicion, ficha);

            libroService.insertar(libro);
            System.out.println("Libro creado exitosamente con ID: " + libro.getId());

        } catch (Exception e) {
            System.err.println("Error al crear libro: " + e.getMessage());
        }
    }

    //Listar Libros
    public void listarLibros() {
        System.out.println("=== Listar Libros ===");
        
        try {
            List<Libro> libros= libroService.getAll();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros.");

                return;
            }

            for (Libro l : libros) {
                System.out.println("ID: " + l.getId() + ", Titulo: " + l.getTitulo() +
                        ", Autor: " + l.getAutor() + ", Editorial: " + l.getEditorial());
                if (l.getFicha() != null) {
                    System.out.println("   Ficha ID: " + l.getFicha().getId() + 
                       " | ISBN: " + l.getFicha().getIsbn());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar libros: " + e.getMessage());
        }
    }
    
    //Actualizar Libro
    public void actualizarLibro() {
        try {
            System.out.println("=== Actualizar Libro ===");

            System.out.print("ID del libro a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            Libro l = libroService.getById(id);

            if (l == null) {
                System.out.println("Libro no encontrado.");

                return;
            }

        System.out.print("Nuevo título (actual: " + l.getTitulo() + "): ");
        String titulo = scanner.nextLine().trim();
        if (!titulo.isEmpty()) l.setTitulo(titulo);

        System.out.print("Nuevo autor (actual: " + l.getAutor() + "): ");
        String autor = scanner.nextLine().trim();
        if (!autor.isEmpty()) l.setAutor(autor);

        System.out.print("Nueva editorial (actual: " + l.getEditorial() + "): ");
        String editorial = scanner.nextLine().trim();
        if (!editorial.isEmpty()) l.setEditorial(editorial);

        System.out.print("Nuevo año (actual: " + l.getAnioEdicion() + "): ");
        String anioStr = scanner.nextLine().trim();
        if (!anioStr.isEmpty()) {
            try {
                l.setAnioEdicion(Integer.parseInt(anioStr));
            } catch (Exception ignored) {}
        }

        System.out.println("¿Desea reemplazar la ficha bibliográfica? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {

            System.out.println("Debe elegir una nueva ficha para reemplazar:");
            listarFichasBibliograficas();

            System.out.print("ID nueva ficha: ");
            int idFicha = Integer.parseInt(scanner.nextLine());

            FichaBibliografica nueva = fichaBibliograficaService.getById(idFicha);

            if (nueva == null) {
                System.out.println("Ficha no encontrada. No se modificará.");
            } else {
                l.setFicha(nueva);
            }
        }

        libroService.actualizar(l);
        System.out.println("Libro actualizado exitosamente.");

        } catch (Exception e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }
}

    //Eliminar Libro
    public void eliminarLibro() {
        try {
            
            System.out.println("=== Eliminar Libro ===");
            
            System.out.print("ID del libro a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            libroService.eliminar(id);
            System.out.println("Libro eliminado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    
//Metodos CRUD: FichaBibliografica:
 
    //Crear Ficha
    public void crearFichaBibliografica() {
        try {
            System.out.println("=== Crear Ficha Bibliográfica ===");

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();

            System.out.print("Clasificado Dewey: ");
            String clasificadoDewey = scanner.nextLine().trim();

            System.out.print("Estantería: ");
            String estanteria = scanner.nextLine().trim();

            System.out.println("Seleccione el idioma:");
            int i = 1;
            for (Idioma idioma : Idioma.values()) {
                System.out.println(i++ + ". " + idioma);
            }
            System.out.print("Opción: ");
            int opcionIdioma = Integer.parseInt(scanner.nextLine());

            Idioma idiomaSeleccionado = Idioma.values()[opcionIdioma - 1];

            // Crear ficha
            FichaBibliografica ficha = new FichaBibliografica(
                isbn,
                clasificadoDewey,
                estanteria
            );
            ficha.setIdioma(idiomaSeleccionado);

            fichaBibliograficaService.insertar(ficha);

            System.out.println("Ficha creada con éxito. ID: " + ficha.getId());

        } catch (Exception e) {
            System.err.println("Error al crear ficha bibliográfica: " + e.getMessage());
        }
    }

//Crear ficha desde la creacion de Libro
    
    private FichaBibliografica crearFicha() {
        System.out.println("=== Crear Ficha Bibliográfica ===");

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Clasificado Dewey: ");
        String clasificadoDewey = scanner.nextLine().trim();

        System.out.print("Estantería: ");
        String estanteria = scanner.nextLine().trim();

        System.out.println("Idiomas disponibles:");
        System.out.println("1. ESPAÑOL");
        System.out.println("2. INGLES");
        System.out.println("3. FRANCES");
        System.out.println("4. PORTUGUES");
        System.out.println("5. OTRO");
        System.out.print("Seleccione idioma: ");

        int opcion = Integer.parseInt(scanner.nextLine());
        Idioma idioma = switch (opcion) {
            case 1 -> Idioma.ESPAÑOL;
            case 2 -> Idioma.INGLÉS;
            case 3 -> Idioma.FRANCÉS;
            case 4 -> Idioma.PORTUGUÉS;
            default -> Idioma.OTRO;
        };

        FichaBibliografica ficha = new FichaBibliografica();
        ficha.setIsbn(isbn);
        ficha.setClasificacionDewey(clasificadoDewey);
        ficha.setEstanteria(estanteria);
        ficha.setIdioma(idioma);

        try {
            fichaBibliograficaService.insertar(ficha);

            return ficha; // nunca null

        } catch (Exception e) {
            System.err.println("Error al crear ficha: " + e.getMessage());

            return null;
        }
    }

    //Listar fichas:
    public void listarFichasBibliograficas() {
        try {
            System.out.println("=== Listar Fichas Bibliográficas ===");

            List<FichaBibliografica> fichas = fichaBibliograficaService.getAll();

            if (fichas.isEmpty()) {
                System.out.println("No se encontraron fichas.");

                return;
            }

            for (FichaBibliografica f : fichas) {
             System.out.println("ID: " + f.getId() + " | ISBN: " + f.getIsbn() +" | Dewey: " + f.getClasificacionDewey() +" | Estantería: " + f.                         getEstanteria() + " | Idioma: " + f.getIdioma());
            }
        } catch (Exception e) {
            System.err.println("Error al listar fichas: " + e.getMessage());
        }
    }
    
    //Actualizar ficha:
    public void actualizarFichaBibliografica() {
        try {
            System.out.println("=== Actualizar Ficha Bibliográfica ===");

            System.out.print("Ingrese el ID de la ficha a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            FichaBibliografica ficha = fichaBibliograficaService.getById(id);
            if (ficha == null) {
                System.out.println("No se encontró una ficha con ese ID.");

                return;
            }

            System.out.println("Ficha encontrada:");
            ficha.mostrarDatos();

            // === Campos editables ===
            System.out.print("Nuevo ISBN (" + ficha.getIsbn() + "): ");
            String isbn = scanner.nextLine().trim();
            if (!isbn.isEmpty()) ficha.setIsbn(isbn);

            System.out.print("Nuevo Clasificado Dewey (" + ficha.getClasificacionDewey() + "): ");
            String dewey = scanner.nextLine().trim();
            if (!dewey.isEmpty()) ficha.setClasificacionDewey(dewey);

            System.out.print("Nueva Estantería (" + ficha.getEstanteria() + "): ");
            String estanteria = scanner.nextLine().trim();
            if (!estanteria.isEmpty()) ficha.setEstanteria(estanteria);

            // === Selección de idioma ===
            System.out.println("Seleccione nuevo idioma (ENTER para mantener):");
            int i = 1;
            for (Idioma idioma : Idioma.values()) {
                System.out.println(i++ + ". " + idioma);
            }
            System.out.print("Opción: ");
            String opcionIdioma = scanner.nextLine().trim();

            if (!opcionIdioma.isEmpty()) {
                int idx = Integer.parseInt(opcionIdioma);
                ficha.setIdioma(Idioma.values()[idx - 1]);
            }

            fichaBibliograficaService.actualizar(ficha);

            System.out.println("Ficha actualizada con éxito.");

        } catch (Exception e) {
            System.err.println("Error al actualizar ficha bibliográfica: " + e.getMessage());
        }
    }

    
    //Eliminar ficha:
    public void eliminarFichaBibliografica() {
        try {
            System.out.println("=== Eliminar Ficha Bibliográfica ===");

            System.out.print("Ingrese el ID de la ficha a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Verificar existencia
            FichaBibliografica ficha = fichaBibliograficaService.getById(id);
            if (ficha == null) {
                System.out.println("No existe una ficha con ese ID.");

                return;
            }

            System.out.println("Ficha encontrada:");
            ficha.mostrarDatos();

            // Confirmación
            System.out.print("¿Está seguro que desea eliminarla? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();

            if (!confirmacion.equals("S")) {
                System.out.println("Operación cancelada.");

                return;
            }

            fichaBibliograficaService.eliminar(id);

            System.out.println("Ficha eliminada correctamente.");

        } catch (Exception e) {
            System.err.println("Error al eliminar ficha bibliográfica: " + e.getMessage());
        }
    }

     //Leer libro por ID:
    public void leerLibroPorId() {
        try {
            System.out.println("=== Buscar Libro por ID ===");
            System.out.print("Ingrese el ID: ");

            int id = Integer.parseInt(scanner.nextLine());
            Libro libro = libroService.getById(id);

            if (libro == null) {
                System.out.println("No se encontró un libro con ese ID.");

                return;
            }

            System.out.println("--- Libro Encontrado ---");
            System.out.println("ID: " + libro.getId());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Editorial: " + libro.getEditorial());
            System.out.println("Año: " + libro.getAnioEdicion());

            if (libro.getFicha() != null) {
                System.out.println("Ficha Bibliográfica Asociada:");
                System.out.println(" - Ficha ID: " + libro.getFicha().getId());
                System.out.println(" - ISBN: " + libro.getFicha().getIsbn());
                System.out.println(" - Dewey: " + libro.getFicha().getClasificacionDewey());
                System.out.println(" - Estantería: " + libro.getFicha().getEstanteria());
                System.out.println(" - Idioma: " + libro.getFicha().getIdioma());
            } else {
                System.out.println("No tiene ficha bibliográfica asociada.");
            }

        } catch (Exception e) {
            System.err.println("Error al buscar libro: " + e.getMessage());
        }
    }

    //Leer ficha por ID:
    public void leerFichaBibliograficaPorId() {
        try {
            System.out.println("=== Buscar Ficha Bibliográfica por ID ===");
            System.out.print("Ingrese el ID: ");

            int id = Integer.parseInt(scanner.nextLine());
            FichaBibliografica ficha = fichaBibliograficaService.getById(id);

            if (ficha == null) {
                System.out.println("No se encontró una ficha con ese ID.");

                return;
            }

            System.out.println("--- Ficha Encontrada ---");
            System.out.println("ID: " + ficha.getId());
            System.out.println("ISBN: " + ficha.getIsbn());
            System.out.println("Clasificado Dewey: " + ficha.getClasificacionDewey());
            System.out.println("Estantería: " + ficha.getEstanteria());
            System.out.println("Idioma: " + ficha.getIdioma());

        } catch (Exception e) {
            System.err.println("Error al buscar ficha: " + e.getMessage());
        }
    }
}

