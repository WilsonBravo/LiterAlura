package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("subjects") List<String> generos,
        @JsonAlias("bookshelves") List<String> seccion,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("media_type") String formato,
        @JsonAlias("download_count") Integer numeroDescargas
        ) {
}
