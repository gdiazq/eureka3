# Proyecto Eureka Semana 4

- Modificar un proyecto Angular
- Agregar módulos al proyecto
- Agregar nuevas rutas al sitio
- Uso de formularios para creación de registros

## Que se evaluara

- Trabaja en el mismo proyecto que creaste en la semana anterior.
- Debes agregar al sitio un componente:
    - Creación de registros: se debe incluir una ventana que permita crear un registro de la entidad principal en la base de datos.
    - Los atributos deben estar correctamente validados (datos únicos, datos obligatorios, formato de valores, valores mínimos y máximos, etc.). Se piden al menos dos validaciones de datos, y puede implementarlas a nivel de backend y/o a nivel de frontend, pero el resultado se debe apreciar en pantalla.
    - Además, considera que la entidad principal se relaciona con la entidad secundaria. Por lo tanto, esto se debe ver reflejado al momento de la creación.
- En el proyecto debe existir al menos un módulo además del módulo principal.
- Debes crear un menú principal en cada página del sitio, que permita navegar entre componentes
    - Es necesario que configure el enrutamiento en Angular para lograr este efecto.
- La aplicación debe permitir editar y/o eliminar un registro de la entidad principal.
- Se pide usar una librería de diseño como Bootstrap o similar.
- Todos los elementos del sitio ya sean componentes, servicios, hojas de estilo globales o variables de entorno deben estar correctamente organizadas en el proyecto usando carpetas.

## Alcances

- Debes publicar el ejercicio en un repositorio GitHub
    - Se recomienda usar el mismo repositorio de la semana anterior.
- El repositorio debe tener un README que señale los pasos para descargar, compilar y ejecutar la solución.
- Se recomienda indicar en el README las URL que permitan acceder al menos al sitio de inicio, y de ahí en más poder acceder a las demás secciones.

## Requisitos

- Java 21 o superior
- Maven 3.x
- Spring Boot 3.x
- Mockito
- JUnit5
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
 - En pages/records estan los metodos principales que se piden CREATE, READ, UPDATE y DELETE 
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
                children: [
                    {
                        path: 'add',
                        component: AddRecordsComponent
                    },
                    {
                        path: 'get',
                        component: GetRecordsComponent
                    },
                    {
                        path: 'update',
                        component: UpdateRecordsComponent
                    },
                    {
                        path: 'delete',
                        component: DeleteRecordsComponent
                    }
                ]
            }
        ]
    }
]
```
 
## Datos de ejemplo

No son necesarios ya que si se genera el docker con docker-compose la base de datos ya tendra datos en BBDD, aunque se usaron estos datos
```sql
INSERT INTO proyectos (nombre)
VALUES
    ('Proyecto Alpha'),
    ('Proyecto Beta'),
    ('Proyecto Gamma'),
    ('Proyecto Delta'),
    ('Proyecto Epsilon');

INSERT INTO clientes (proyecto_id, nombre, casa_matriz)
VALUES
    (1, 'GlobalTech Solutions', 'Tecnologia Innovadora US'),
    (1, 'HyperByte Enterprises', 'Byte Corporation US'),
    (1, 'QuantumData Systems', 'Quantum Tech Hub'),
    (2, 'RedNet Consulting', 'RedNex Global US'),
    (2, 'Futurity Innovations', 'Futuris Co'),
    (2, 'TechSphere Group', 'Sphere Technologies'),
    (3, 'CyberSolutions Network', 'NetWorx Innovations'),
    (3, 'BlueCloud Technologies', 'CloudMasters WEB ES'),
    (3, 'NextGen Consulting', 'Next Generation Technologies'),
    (4, 'Stratos Enterprises', 'StratNet Consulting CDN'),
    (4, 'XenoTech Industries', 'Xenotech Systems Limited'),
    (4, 'Digix Technologies', 'DigitalX Ventures Amplified'),
    (5, 'InfoTronics Solutions', 'InfoTech Global Group'),
    (5, 'Maximus Technologies', 'MaxTech Solutions Inc'),
    (5, 'CloudStrive Consulting', 'StriveCloud Technologies Worldwide');
```