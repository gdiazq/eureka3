# Proyecto Eureka Semana 3

- Crear un proyecto Angular desde cero
- Agregar componentes al proyecto
- Configurar las rutas del sitio
- Acceder a una API Rest por medio de un servicio

## Que se evaluara

- Crea un proyecto Angular en tu equipo local. Se recomienda el uso de la última versión.
- Este proyecto se debe relacionar con el aplicativo Spring Boot creado en la semana 2.
- Debe agregar al sitio tres componentes:
    - Página de inicio: permite desplegar información relacionada con el proyecto. En esta sección debe indicar de qué trata su experiencia, adjuntando alguna imagen y/o video alusivo.
    - Listado de registros: en esta sección se debe desplegar un listado de registros de la entidad principal.
    - Acerca de: en esta sección se deben indicar datos sobre el autor del proyecto. Queda en libertad de acción sobre el contenido; sin embargo, es importante que considere al menos una imagen o bien un video.
- Debe crear un menú principal en cada página del sitio, que permita navegar entre componentes
    - Es necesario que configure el enrutamiento en Angular para lograr este efecto
- Se pide usar una librería de diseño como Bootstrap o similar
- Todos los elementos del sitio, ya sean componentes, servicios, hojas de estilo globales o variables de entorno deben estar correctamente organizadas en el proyecto usando carpetas.

## Alcances

- Debes publicar el ejercicio en un repositorio GitHub
- Se recomienda partir desde un repositorio vacío
- El repositorio debe tener un README que señale los pasos para descargar, compilar y ejecutar la solución
- Se recomienda indicar en el README las URL que permitan acceder a los endpoints, junto a datos de ejemplos que permitan ejecutarlos.

## Requisitos

- Java 21 o superior
- Maven 3.x
- Spring Boot 3.x
- Mockito
- Junit5
- Docker
- Angular CLI 17++

## Instalacion 

- Clonar repositorio:
```bash
git clone https://github.com/gdiazq/eureka1.git 
```

### Para el backend:

- Instalar Docker Desktop [https://docs.docker.com/desktop/install/windows-install/]

- Ingresar a la carpeta del proyecto

- Ejecutar el contenedor docker mediante comando con  
```bash
docker-compose up -d
```

- Compilar el proyecto
```bash
mvn clean install 
```

- Ejecutar la aplicacion
```bash
mvn spring-boot:run
```
### Para el frontend

- Instalar dependencias
```bash
npm install
```

- Ejecutar el proyecto
```bash
ng serve
```

## Ejecucion de los endpoints

Se recomienda instalar postman, thunderbird como extension para vscode o utilizar el comand curl para linux

- Listar todos los customer

```bash
GET localhost:8080/customer
```

- Listar todos los project

```bash
GET localhost:8080/project
```

- Buscar cliente por id

```bash
GET localhost:8080/customer/{id}
```
Ejemplo: localhost:8080/customer/1

- Crear nuevo cliente
```bash
POST localhost:8080/customer
```
Ejemplo: localhost:8080/customer
```json
{
    "nombre": "Cliente F",
    "casa_matriz": "Casa Matriz F"
}
```

- Eliminar cliente por id
```bash
DELETE localhost:8080/customer/{id}
```
Ejemplo: localhost:8080/customer/6

## CORS

Se habilito CORS en el backend esta es la configuracion

- WebConfig
```java
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");

            }
        };
    }
}
```

En el controller

- AlgunArchivoController
```java
@CrossOrigin(origins = "http://localhost:4200")
```

## Para ejecutar las pruebas

Para ejecutar las pruebas uno debe iniciar el contenedor del docker y despues iniciar el proyecto, despues ir carpeta a carpeta de los test para poder ejecutar haciando click derecho e ir a la parte de Run Test

## Sobre el frontend

El frontend esta realizo en angular con la CLI 18, se ocupo 2 carpetas: 
- semana3: con carpetas como interfaces, pages y services
- shared: components con carpetas footer, main-layout y navbar

### Sobre las rutas

Se ocupo lazy en la ruta principal y despues rutas hijas para cada ruta

```javascript
const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./semana3/semana3.module').then(m => m.Semana3Module)
  },
  {
    path: '**',
    redirectTo: ''
  }
];
```

```javascript
const routes: Routes = [
    {
        path: '',
        component: MainLayoutComponent,
        children: [
            {
                path: '',
                component: HomeComponent
            },
            {
                path: 'about',
                component: AboutComponent
            },
            {
                path: 'records',
                component: RecordsComponent
            }
        ]
    }
]
```
 
## Datos de ejemplo

No son necesarios ya que si se genera el docker con docker-compose la base de datos ya tendra datos en BBDD, aunque se usaron estos datos
```sql
INSERT INTO clientes (nombre, casa_matriz)
VALUES
    ('Cliente A', 'Casa Matriz A'),
    ('Cliente B', 'Casa Matriz B'),
    ('Cliente C', 'Casa Matriz C'),
    ('Cliente D', 'Casa Matriz D'),
    ('Cliente E', 'Casa Matriz E');

INSERT INTO proyectos (cliente_id, nombre)
VALUES
    (1, 'Proyecto Alpha'),
    (2, 'Proyecto Beta'),
    (3, 'Proyecto Gamma'),
    (4, 'Proyecto Delta'),
    (5, 'Proyecto Epsilon');
```