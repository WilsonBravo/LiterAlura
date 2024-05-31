package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiterAluraService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Libro addLibro(Libro libro){
        return libroRepository.save(libro);
    }
    @Transactional
    public Autor addAutor(Autor autor){
        // Evitar que se duplique los autores, primero verificar si ya existe
        Optional<Autor> autorOpt = autorRepository.findByNombre(autor.getNombre());
        if (autorOpt.isPresent()){
            return autorOpt.get();
        }
        return autorRepository.save(autor);
    }
    @Transactional
    public void addAutorLibro(Long bookId, Long authorId) {
        Optional<Libro> libroOpt = libroRepository.findById(bookId);
        Optional<Autor> autorOpt = autorRepository.findById(authorId);

        if (libroOpt.isPresent() && autorOpt.isPresent()) {
            Libro libro = libroOpt.get();
            Autor autor = autorOpt.get();
            libro.getAutores().add(autor);
            autor.getLibros().add(libro);
            libroRepository.save(libro);
            autorRepository.save(autor);
        }
    }

    public List<Libro> mostrarTodosLosLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        return libros;
    }

    public List<Autor> mostrarTodosLosAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        return autores;
    }

    public Optional<List<Autor>> buscarAutoresPorFecha(int fecha) {
        Optional<List<Autor>> autores = autorRepository.buscarAutoresPorFecha(fecha);
        return autores;
    }

    public Optional<List<Libro>> buscarLibrosPorIdioma(String idioma) {
        Optional<List<Libro>> libros = libroRepository.buscarLibrosPorIdioma(idioma);
        return libros;
    }

    public List<Libro> top10Libros() {
        List<Libro> libros = libroRepository.top10Libros();
        return libros;
    }
}
