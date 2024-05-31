package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a JOIN a.libros l WHERE a.fechaNacimiento < :fechaDeterminada AND a.fechaFallecimiento > :fechaDeterminada")
    Optional<List<Autor>> buscarAutoresPorFecha(int fechaDeterminada);
}
