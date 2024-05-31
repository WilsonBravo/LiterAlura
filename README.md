Este es un proyecto para el programa ONE G6 junto con Alura LATAM para la especialización BackEnd con Java. 

El objetivo de este proyecto es desarrollar una aplicación de Spring Boot que se ejecute en consola. La aplicación funciona como una biblioteca de libros en la que se pueden realizar las siguientes funcionalidades:

    1 - Buscar libro por titulo
    2 - Listar libros registrados
    3 - Listar autores registrados
    4 - Listar autores de un determinado año
    5 - Listar libros por idioma
    6 - Top 10 libros más descargados

    0 - Salir

Con el fin de obtener datos con los que poder trabajar usé la API de [Gutendex](https://gutendex.com/). Gutendex es una API web autohospedada que simplifica el acceso a los datos del catálogo de libros de Project Gutenberg. Utiliza Django para descargar y servir información en formato JSON, permitiendo consultas más eficientes sobre los libros disponibles. 

En este caso se accede a la información de los libros a través del método **search** usando el siguiente enlace [https://gutendex.com/books/?search=titulo_libro](https://gutendex.com/books/?search=quijote), donde **titulo_libro** hace referencia al titulo o una parte del titulo del libro del que se requiere la información.

La API es totalmente gratuita y no se necesita ningún API Key para acceder a sus datos.

Para la persistencia de datos se trabaja con un base de datos PostgreSQL y JPA como ORM en Java.

A continuación se da una breve descripción de las funcionalidades de la aplicación y un ejemplo de su ejecución.

************************************************************
### 1. Buscar libro por titulo
En este caso se pide al usuario que ingrese el titulo del libro que busca, seguidamente se realiza la petción a la API de gutendex para obtener la información y por último se registran los datos en la base de datos de LiterAlura.

    Ingrese el nombre del libro que desea buscar:
    Cuentos de Amor

    ----- LIBRO -----
    Titulo: Cuentos de Amor de Locura y de Muerte
    Autor: Quiroga, Horacio
    Idioma: es
    Numero de descargas: 1454

************************************************************
### 2. Listar libros registrados
En este caso se realiza una consulta a la base de datos para que devuelva la información de todos los libros registrados hasta el momento.

    ----- LIBRO -----
    Titulo: Pride and Prejudice
    Autor: Austen, Jane
    Idioma: en
    Numero de descargas: 62862
    -----------------


    ----- LIBRO -----
    Titulo: The writings of Origen, Vol. 1 (of 2)
    Autor: Origen
    Idioma: en
    Numero de descargas: 2018
    -----------------


    ----- LIBRO -----
    Titulo: Dracula
    Autor: Stoker, Bram
    Idioma: .
            .
            .
************************************************************
### 3. Listar autores registrados
En este caso se realiza una consulta a la base de datos para que devuelva la información de todos los autores registrados hasta el momento.

    Autor: Milton, John
    Fecha de nacimiento: 1608
    Fecha de fallecimiento: 1674
    Libros: [El paraíso perdido]


    Autor: Homer
    Fecha de nacimiento: -750
    Fecha de fallecimiento: -650
    Libros: [La Odisea]


    Autor: Rizal, José
    Fecha de nacimiento: 1861
    Fecha de fallecimiento: .
                            .
                            .
************************************************************
### 4. Listar autores de un determinado año
En este caso se realiza una consulta a la base de datos para que devuelva la información de todos los autores que estuvieron vivos en una fecha dada por el usuario.

    Ingrese el año en el que desea buscar:
    1900

    
    Autor: Stoker, Bram
    Fecha de nacimiento: 1847
    Fecha de fallecimiento: 1912
    Libros: [Dracula]


    Autor: Potter, Beatrix
    Fecha de nacimiento: 1866
    Fecha de fallecimiento: 1943
    Libros: [The Tale of Peter Rabbit]


    Autor: Figueiredo, Cândido de
    Fecha de nacimiento:.
                        .
                        .
************************************************************
### 5. Listar libros por idioma
En este caso se realiza una consulta a la base de datos para que devuelva información de todos los libros de un idioma especificado por el usuario.

    Ingrese el idioma:

    es - Español
    en - Inglés
    fr - Francés
    pt - Portugués

    es

    ----- LIBRO -----
    Titulo: El paraíso perdido
    Autor: Milton, John
    Idioma: es
    Numero de descargas: 4421
    -----------------


    ----- LIBRO -----
    Titulo: La Odisea
    Autor: Homer
    Idioma: es
    Numero de descargas: 4322
    -----------------


    ----- LIBRO -----
    Titulo: Mi Ultimo Adiós
    Autor:  .
            .
            .
************************************************************
### 6. Top 10 libros más descargados
En este caso se realiza una consulta a la base de datos para que devuelva 10 libros ordenados de acuerdo a la columna de número de descargas de mayor a menor.

    ----- LIBRO -----
    Titulo: Frankenstein; Or, The Modern Prometheus
    Autor: Shelley, Mary Wollstonecraft
    Idioma: en
    Numero de descargas: 73638
    -----------------


    ----- LIBRO -----
    Titulo: Pride and Prejudice
    Autor: Austen, Jane
    Idioma: en
    Numero de descargas: 62862
    -----------------


    ----- LIBRO -----
    Titulo: Dracula
    Autor:  .
            .
            .