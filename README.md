# MeliAppExamen

#### Nombre: YAPURA, JOSÉ DANIEL

##### DEFINICIÓN DEL PROYECTO
La idea general del sistema a desarrollar consiste en una aplicación móvil/cliente que permita a cualquier persona poder realizar búsquedas de productos de su interés sobre la plataforma de Mercado Libre, y poder obtener información más detallada sobre el mismo.
El objetivo general se basa principalmente en brindar una experiencia fluida y sencilla al
usuario, al realizar sus búsquedas.
Los usuarios de la aplicación son en general todas las personas interesadas en
potencialmente comprar un producto a través de Internet. Sin embargo, no se enfoca
especialmente en ellas. Simplemente, en toda persona que desea obtener información
sobre un producto, y que no requieren algún tipo de experiencia en el manejo de la
herramienta.
##### Límite del sistema
La aplicación solo se limita a permitir a los usuarios a realizar simples búsquedas de los
productos y poder ver información sobre ellos. Queda fuera del desarrollo brindar funciones
relacionadas con la venta y compra de productos, y demás funciones que puede facilitar la
plataforma de Mercado Libre, y/o que requieran un Token por Mercado Libre
Dependecias

![limite](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-09.png)

#### ANÁLISIS DE ACTORES
##### Actor principal:
- **Usuarios:** Las personas interesadas en realizar búsquedas de productos de
su interés para obtener información detallada sobre los mismos
(particularmente información de compra).
##### Actor secundario:
- **ApiREST Mercado Libre:** Sistema externo que permite a la aplicación a
desarrollar poder comunicarse con los servicios de MercadoLibre y poder
brindar las funcionalidades al usuario.

#### REQUISITOS FUNCIONALES
##### Diagrama de Casos de uso
![caso de uso](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-25.png)

#### CASOS DE USO
##### CASO DE USO: CS01-BUSCAR PRODUCTO
- **Actores:** Usuario.
- **Intereses:** Permitir al usuario realizar una simple búsqueda de algún producto de su
interés.
- **Precondición:** Ninguna.
- **Postcondición:** El sistema presenta los resultados de la búsqueda.
- **Flujo principal**

1. El usuario ingresa en la aplicación el nombre o una palabra alusiva al
producto a buscar.
2. El sistema valida la palabra y se comunica con el servicio externo.
3. El sistema presenta los resultados de la búsqueda.
- **Flujo alterno**:
- 2a. 
  1. El usuario ingresa una palabra inválida o incorrecta.
  2. El sistema presenta al usuario un mensaje.
  3. El usuario ingresa nuevamente el producto a buscar.
- 2b. 
1. El Sistema no se puede comunicar con el sistema externo.
2. El sistema presenta un mensaje al usuario.
3. El usuario puede volver a realizar la búsqueda.

- 3
1. No hay resultados para la búsqueda.
2. El sistema presenta un mensaje al usuario.
3. El usuario puede realizar otra búsqueda.


##### CASO DE USO: CS02-VER DETALLE DE PRODUCTO
- **Actor**: Usuario
- **Intereses**: Permitir al usuario que visualice la información relacionada al producto
buscado.
- **Precondición**: Realizar una búsqueda del producto (CS01-BUSCAR PRODUCTO)
- **Postcondición**: Ninguna.
- **Flujo principal:**
  1. Inicia cuando el usuario ha realizado una búsqueda del producto y el sistema
  presenta los resultados.
  2. El usuario elige un producto del resultado de la búsqueda.
  3. El Sistema se comunica con el sistema externo para obtener información.
  4. El sistema presenta la siguiente información:
      - Título de la publicación
      - Precio del producto
      - Imagen.
      - Condición del producto.
      - Cantidad de productos vendidos.
      - Valoración del producto.
      - Cantidad de opiniones relacionadas con el producto.
      - Porcentaje de descuento
      - Fecha de la publicación del producto.
      - Nombre del vendedor del producto.
      - Atributos relacionados con el producto.
      - Descripción del producto

- **Flujo Alterno:**
- 3a
  1. El sistema no se puede comunicar con el sistema externo.
  2. El sistema presenta un mensaje al usuario.
  3. El sistema presenta nuevamente el resultado de la búsqueda realizada.
- 4a
    1. Parte de la información no está disponible.
    2. El sistema presenta solo la información que está disponible.


#### ARQUITECTURA LÓGICA
La arquitectura elegida para la aplicación está basada en una arquitectura Clean. No es una
implementación estricta. Es una simple adaptación para poder tener las ventajas de la
misma y a su vez no complejizar el desarrollo con la adición de clases.
![dependencias](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-34.png)


#### DOMINIO
![dominio](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-41.png)

#### DIAGRAMA DE CLASE (PARCIAL)
![clase](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-51.png)

* **Package tools**: Contiene una sola clase “Parser”, que permite parsear los objetos json a
los objetos del dominio.

#### Librerias y dependencias
##### Volley 
Es un cliente Http que permite la comunicación de red fácilmente en aplicaciones android. Volley está enfocado en las peticiones, evitando la creación de código repetitivo para manejar tareas asíncronas en cada petición.
Entre sus ventajas:
- Procesamiento concurrente de peticiones.
- Priorización de las peticiones, lo que permite definir la preponderancia de cada petición.
- Cancelación de peticiones, evitando la presentación de resultados no deseados en el hilo principal.
- Gestión automática de trabajos en segundo plano, dejando de lado la implementación manual de un framework de hilos.
implementación de cachó en disco  y memoria.
- Capacidad de personalización de las peticiones.
- Provee información detallada del estado y flujo de trabajo de las peticiones en la consola de depuración.

##### Picasso
Es una librería para el desarrollo de aplicaciones android enfocada en el manejo automático de la carga de imágenes.
Características:
- Transforma las imágenes para que se ajusten y permite reducir la carga de memoria.
- Permite especificar una transformación personalizada para efectos más avanzados.
- Permite la carga de imagenes e imagenes de error.
- Soporta Resources, archivos, imágenes, assets y content providers como recursos de imagen.

##### Consideraciones del desarrollo
Para el desarrollo de esta aplicación se consideró un desarrollo nativo para la plataforma
Android, utilizando el lenguaje de programación Java.
**Herramientas de desarrollo**
- IDE: Android Studio 3.4.1
- Framework: Android Sdk - api nivel 28
- Librerías Android:
  - cardview-v7.28.0.0
  - recyclerview-v7.28.0.0
- Librerías Externas:
  - Volley 1.1.1
  - Picasso 2.5.2
- Librerías Test
  - JUnit
  - Mockito
  
Para la implementación de la aplicación se consideró al framework sdk android nivel 28, es
decir, dispositivos con versión del S.O. android 9.0.
Cómo target min, se contempló a dispositivos con sdk android nivel 23 (Android 6.0).
Repositorio del código: https://github.com/josdan23/MeliAppExamen

#### INTERFACES
##### Main activity
![main_activity](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-24-59.png)

##### PublicacionesActivity
**Aclaración**: para el paginado de los resultados, se utilizó un paginado Endless.
![publicacion_activity](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-25-06.png)

##### Detalle Activity
![detalle_activity](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-25-18.png)

![detalle_activity2](https://github.com/josdan23/MeliAppExamen/blob/master/Screenshot/Captura%20de%20pantalla%20de%202021-05-23%2018-25-25.png)

