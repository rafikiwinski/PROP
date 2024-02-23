 # Directorio Source
> Path absoluto: /src

## Descripción del directorio
Este directorio contiene todo el código del proyecto organizado por packages.

## Elementos del directorio

- **Directorio main:**
    Contiene todos los códigos del modelo, las hemos organizado en tres packages, uno para cada capa del sistema.
- **Directorio test:**
    Todos los test unitarios del JUnit se encuentran en este directorio. Los usamos para probar casos básicos de las clases 
    del modelo y también casos extremos.
- **Directorio drivers:**
    Los drivers que usamos para probar los controladores del sistema y comprobar todas las funciones de las principales
    clases del modelo. 


## Instrucciones Makefike
    Primero tienes que hacer un make all
- `make all`
    Compila todos los ficheros .java que se encuentran en el directorio **/src** y los deja en el directorio **/src**.
- `make jar(nomDriver)`
    Compila el driver **nomDriver** y genera el fichero **nomDriver**.jar en el directorio **/jar**.
    Además se ejecuta ese driver.

- `make executaDriver(nomDriver)`
    Ejecuta el driver **nomDriver**.jar que se encuentra en el directorio **/jar**.

- `make buildTests`
    Compila todos los test unitarios y los drivers de test y los deja en el directorio **/jar**.

- `make test(NombreTest)`
    Ejecuta todos los test unitarios y los drivers de test que se encuentran en el directorio **/jar**.

- `make clean`
    Elimina todos los ficheros .class generados en el directorio **/src** y **/jar**.

- `make distclean`
    Elimina todos los ficheros .class generados en el directorio **/src**, **/jar** y **/doc**.