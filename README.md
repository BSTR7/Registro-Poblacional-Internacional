# Registro Poblacional Internacional

Este proyecto forma parte de una **Evaluación de Programación Orientada a Objetos (POO)** de la **IP Santo Tomás**. Su propósito es gestionar datos de países, sus ciudades y los idiomas que se hablan en cada país. La aplicación se desarrolla en Java utilizando la interfaz gráfica Swing y una base de datos MySQL para almacenar la información.

## Funcionalidades

### Pestañas
1. **Paises**:
   - Permite registrar, consultar, modificar o eliminar información sobre países.
   - Campos:
     - **Código**: Código único del país (ej. "ARG").
     - **Nombre**: Nombre del país.
     - **Continente**: El continente al que pertenece el país.
     - **Población**: La población del país.
     - **Tipo de gobierno**: Se puede seleccionar entre "Democracia" o "Otro".

   [![Registro-Poblacional-Internacional.png](https://i.postimg.cc/4NPG7gSq/Registro-Poblacional-Internacional.png)](https://postimg.cc/KR1wwdFD)

2. **Ciudades por País**:
   - Permite consultar las ciudades y su población para un país seleccionado.
   - Se visualiza la ciudad y su población en una tabla.

   ![Registro Ciudades por País](https://i.postimg.cc/tCpy4T2F/Registro-ciudades-por-pais.png)

3. **Idiomas por País**:
   - Permite consultar los idiomas hablados en un país y si son oficiales o no.
   - Se visualiza el idioma y su estado de oficialidad (Sí o No).

   ![Registro Idiomas por País](https://i.postimg.cc/59wbJvqx/Registro-idioma-por-pais.png)

### Funciones Implementadas
- **Registrar País**: Ingresar un nuevo país en la base de datos con los datos correspondientes.
- **Consultar Países**: Ver los países registrados en una tabla con los detalles (Código, Nombre, Continente, Población, Tipo de Gobierno).
- **Modificar País**: Cambiar los datos de un país ya registrado.
- **Eliminar País**: Eliminar un país de la base de datos.
  
### Consultar Ciudades e Idiomas
- **Consultar Ciudades**: Al seleccionar un país, se muestran sus ciudades y las respectivas poblaciones.
- **Consultar Idiomas**: Al seleccionar un país, se muestran los idiomas que se hablan en él, indicando si son oficiales o no.
