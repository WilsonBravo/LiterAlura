package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE l.idioma = %:idioma%")
    Optional<List<Libro>> buscarLibrosPorIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC LIMIT 10")
    List<Libro> top10Libros();
}
