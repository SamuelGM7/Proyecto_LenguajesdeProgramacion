% Estructura del hecho:
% pelicula(Titulo, Generos, Idioma, DuracionMin, Clasificacion, Plataforma).

pelicula('Inception', ['ciencia ficcion', 'suspenso'], 'ingles', 148, 'mayores_13', 'netflix').
pelicula('Titanic', ['drama', 'romance'], 'ingles', 195, 'mayores_13', 'prime video').
pelicula('Coco', ['animacion', 'musical', 'fantasia'], 'espanol', 105, 'apta_todo_publico', 'disney+').
pelicula('Parasite', ['drama', 'suspenso'], 'coreano', 132, 'mayores_13', 'hbo max').
pelicula('The Avengers', ['accion', 'ciencia ficcion'], 'ingles', 143, 'mayores_13', 'disney+').
pelicula('Your Name', ['animacion', 'romance', 'fantasia'], 'japones', 112, 'mayores_13', 'netflix').
pelicula('El laberinto del fauno', ['fantasia', 'drama'], 'espanol', 118, 'mayores_13', 'prime video').
pelicula('Joker', ['drama', 'crimen'], 'ingles', 122, 'mayores_13', 'hbo max').
pelicula('Toy Story', ['animacion', 'comedia'], 'ingles', 81, 'apta_todo_publico', 'disney+').
pelicula('The Godfather', ['crimen', 'drama'], 'ingles', 175, 'mayores_18', 'netflix').
pelicula('Interstellar', ['ciencia ficcion', 'drama'], 'ingles', 169, 'mayores_13', 'hbo max').
pelicula('Amelie', ['comedia', 'romance'], 'frances', 122, 'mayores_13', 'netflix').
pelicula('Train to Busan', ['accion', 'terror'], 'coreano', 118, 'mayores_13', 'prime video').
pelicula('Frozen', ['animacion', 'musical', 'fantasia'], 'ingles', 102, 'apta_todo_publico', 'disney+').
pelicula('Pulp Fiction', ['crimen', 'drama'], 'ingles', 154, 'mayores_18', 'hbo max').
pelicula('La La Land', ['musical', 'romance', 'drama'], 'ingles', 128, 'mayores_13', 'prime video').
pelicula('Intensamente', ['animacion', 'comedia'], 'espanol', 95, 'apta_todo_publico', 'disney+').
pelicula('Her', ['drama', 'romance', 'ciencia ficcion'], 'ingles', 126, 'mayores_13', 'netflix').
pelicula('La vida es bella', ['drama', 'comedia'], 'italiano', 116, 'mayores_13', 'prime video').
pelicula('Black Panther', ['accion', 'aventura'], 'ingles', 134, 'mayores_13', 'disney+').
pelicula('El hoyo', ['ciencia ficcion', 'suspenso'], 'espanol', 94, 'mayores_18', 'netflix').
pelicula('Soul', ['animacion', 'fantasia', 'musical'], 'ingles', 100, 'apta_todo_publico', 'disney+').
pelicula('Bird Box', ['suspenso', 'drama'], 'ingles', 124, 'mayores_13', 'netflix').
pelicula('Bajocero', ['accion', 'suspenso'], 'espanol', 106, 'mayores_13', 'netflix').
pelicula('Shrek', ['animacion', 'comedia', 'fantasia'], 'ingles', 90, 'apta_todo_publico', 'prime video').
pelicula('The Conjuring', ['terror', 'suspenso'], 'ingles', 112, 'mayores_13', 'hbo max').
pelicula('Roma', ['drama'], 'espanol', 135, 'mayores_13', 'netflix').
pelicula('The Irishman', ['crimen', 'drama'], 'ingles', 209, 'mayores_18', 'netflix').
pelicula('Encanto', ['animacion', 'musical'], 'espanol', 99, 'apta_todo_publico', 'disney+').
pelicula('Fight Club', ['drama', 'suspenso'], 'ingles', 139, 'mayores_18', 'prime video').
pelicula('Knives Out', ['misterio', 'comedia', 'crimen'], 'ingles', 130, 'mayores_13', 'prime video').
pelicula('Luca', ['animacion', 'fantasia'], 'italiano', 95, 'apta_todo_publico', 'disney+').
pelicula('Oldboy', ['accion', 'suspenso'], 'coreano', 120, 'mayores_18', 'prime video').
pelicula('Spirited Away', ['animacion', 'fantasia', 'aventura'], 'japones', 125, 'mayores_13', 'hbo max').
pelicula('El secreto de sus ojos', ['drama', 'suspenso', 'crimen'], 'espanol', 129, 'mayores_13', 'netflix').

% Estructura para filtrar una pelicula
% recomendar_pelicula(Titulo, Generos, Idiomas, DuracionRango, Clasificacion, Plataforma).

% 1° Mini-funcion axiliar para definir los rangos
duracionrango(D, 'menos de 45 min') :- D < 45.
duracionrango(D, '45 - 60 min') :- D >= 45, D =< 60.
duracionrango(D, '60 - 90 min') :- D > 60, D =< 90.
duracionrango(D, '90 - 120 min') :- D > 90, D =< 120.
duracionrango(D, 'mas de 120 min') :- D > 120.


% 2° Mini-funcion para filtrar las elementos en común de una lista
% Recorre toda la lista en busca de elementos en comun en común. El ! permite dar un salto en la busqueda, parecido con el break, solo funciona cuando encuentra el elemento en común.

% Verifica si hay al menos un elemento en común entre dos listas
elementosencomun([X|_], Lista2) :-
    member(X, Lista2), !.
elementosencomun([_|Y], Lista2) :-
    elementosencomun(Y, Lista2).

% Regla principal de recomendación
recomendar_pelicula(Titulo, GenerosFiltro, IdiomasFiltro, DuracionFiltro, ClasificacionFiltro, PlataformaFiltro) :-
    pelicula(Titulo, Generos, Idioma, Duracion, Clasificacion, Plataforma),
    elementosencomun(Generos, GenerosFiltro),           % Géneros en común
    member(Idioma, IdiomasFiltro),                      % Idioma dentro de los aceptados
    duracionrango(Duracion, DuracionFiltro),            % Duración correcta
    Clasificacion = ClasificacionFiltro,                % Clasificación exacta
    Plataforma = PlataformaFiltro.                      % Plataforma exacta

