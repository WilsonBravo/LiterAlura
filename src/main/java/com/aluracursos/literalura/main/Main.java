package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosGutendex;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import com.aluracursos.literalura.service.LiterAluraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LiterAluraService literAluraService;
    private Scanner teclado = new Scanner(System.in);

    public Main(LiterAluraService literAluraService) {
        this.literAluraService = literAluraService;
    }

    public void muestraMenu() {
        var opcion = "-1";
        while (!(opcion.equals("0"))) {
            var menu = """
                                        
                    =========================================

                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores de un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros más descargados
                                        
                    0 - Salir
                                        
                    """;
            System.out.println(menu);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    try {
                        bucarLibroPorTitulo();
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("El libro ya se encuentrá registrado");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Libro no encontrado");
                    } catch (Exception e) {
                        System.out.println("Se produjo una excepción: " + e.getClass().getSimpleName());
                    }
                    break;
                case "2":
                    mostrarTodosLosLibrosRegistrados();
                    break;
                case "3":
                    mostrarTodosLosAutoresRegistrados();
                    break;
                case "4":
                    buscarAutoresPorFecha();
                    break;
                case "5":
                    buscarLibrosPorIdioma();
                    break;
                case "6":
                    top10Libros();
                    break;

                case "0":
                    System.out.println("Cerrando la aplicacion");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    }

    private void bucarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var nombreLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(("https://gutendex.com/books/?search=" + nombreLibro.replace(" ", "%20")));

        DatosGutendex resultados = conversor.obtenerDatos(json, DatosGutendex.class);
        if (!resultados.libros().get(0).autores().isEmpty()) {
            Libro libro = literAluraService.addLibro(new Libro(resultados.libros().get(0)));
            resultados.libros().get(0).autores().stream()
                    .forEach(a -> {
                        Autor autor = literAluraService.addAutor(new Autor(a));
                        literAluraService.addAutorLibro(libro.getId(), autor.getId());
                    });

            System.out.println("----- LIBRO -----");
            System.out.println(
                    "Titulo: " + resultados.libros().get(0).titulo() +
                            "\nAutor: " + resultados.libros().get(0).autores().get(0).nombre() +
                            "\nIdioma: " + resultados.libros().get(0).lenguajes().get(0) +
                            "\nNumero de descargas: " + resultados.libros().get(0).numeroDescargas()
            );
        } else {
            System.out.println("El libro no tiene un autor, no puede ser registrado");
        }
    }

    private void mostrarTodosLosLibrosRegistrados() {
        List<Libro> libros = literAluraService.mostrarTodosLosLibrosRegistrados();
        libros.forEach(l -> System.out.println("\n" + l));
    }

    private void mostrarTodosLosAutoresRegistrados() {
        List<Autor> autores = literAluraService.mostrarTodosLosAutoresRegistrados();
        autores.forEach(a -> System.out.println("\n" + a));
    }

    private void buscarAutoresPorFecha() {
        System.out.println("Ingrese el año en el que desea buscar:");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        Optional<List<Autor>> autores = literAluraService.buscarAutoresPorFecha(fecha);

        if (autores.isPresent() && !autores.get().isEmpty()){
            autores.get().forEach(a -> System.out.println("\n" + a));
        } else {
            System.out.println("=========================================");
            System.out.println("No se encontraron resultados para la fecha dada");
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma:
                
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """);
        var idioma = teclado.nextLine();

        Optional<List<Libro>> libros = literAluraService.buscarLibrosPorIdioma(idioma);

        if (libros.isPresent() && !libros.get().isEmpty()){
            libros.get().forEach(l -> System.out.println("\n" + l));
        } else {
            System.out.println("=========================================");
            System.out.println("No se encontraron resultados para el idioma dado");
        }
    }

    private void top10Libros() {
        List<Libro> libros = literAluraService.top10Libros();
        libros.forEach(l -> System.out.println("\n" + l));
    }

}
