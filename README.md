# Nombre de tu Proyecto

En este proyecto creamos una agrenda de contactos y sus grupos asociados los cuales podemos ver, modificar y eliminar.


## Índice

1. [Instalación](#instalación)
2. [Uso](#uso)
3. [Tecnologías](#tecnologías)
4. [Contribución](#contribución)
5. [Licencia](#licencia)

## Instalación

Instrucciones para clonar y configurar tu proyecto en el entorno local.

```bash
# Clonar el repositorio
git clone https://github.com/tu_usuario/nombre-del-repositorio.git

# Navegar a la carpeta del proyecto
cd nombre-del-repositorio

# Instalar dependencias (si usas Maven)
mvn install
```

## Uso

## Endpoints

### `GET /api/group/showGroups/byID/{idGroup}`
- **Descripción**: Ver un grupo por su ID.
- **Parámetros**:
    - `idGroup` (Long): El ID del grupo.
- **Ejemplo de respuesta exitosa (200 OK)**:
    ```json
    {
        "id": 1,
        "name": "Friends",
        "members": 10
    }
    ```
- **Ejemplo de respuesta si no se encuentra el grupo (404 Not Found)**:
    ```json
    {
        "error": "Grupo no encontrado con ID: 1"
    }
    ```

### `GET /api/group/showGroups/byName/{nameGroup}`
- **Descripción**: Vemos un grupo por su nombre.
- **Parámetros**:
    - `nameGroup` (String): El nombre del grupo.
- **Ejemplo de respuesta exitosa (200 OK)**:
    ```json
    {
        "id": 1,
        "name": "Friends",
        "members": 10
    }
    ```
- **Ejemplo de respuesta si no se encuentra el grupo (404 Not Found)**:
    ```json
    {
        "error": "Grupo no encontrado con nombre: Friends"
    }
    ```

### `POST /api/contact/addContacts`

- **Descripción**: Permite agregar múltiples contactos a la base de datos.
- **Parámetros**:
    - **Cuerpo de la solicitud (`RequestBody`)**: Una lista de objetos `Contact` en formato JSON. Cada objeto debe contener la información de un contacto que se desea agregar.

- **Ejemplo de solicitud**:

```bash
[
    {
        "name": "John Doe",
        "phone": "1234567890",
        "email": "johndoe@example.com"
    },
    {
        "name": "Jane Doe",
        "phone": "0987654321",
        "email": "janedoe@example.com"
    }
]

```
- **Ejemplo de respuesta si no se encuentra el grupo (404 Not Found)**:
```bash
{
    "error": "Bad request. Please check the input data."
}
```
### `POST /api/contact/deleteContact/{idContact}`

- **Descripción**: Permite eliminar un contacto por su id.
- **Parámetros**:
    - `idGroup` (Long): El ID del grupo.
- **Ejemplo de solicitud**:
```bash

```
## Tecnologías utilizadas

- Java
- Spring Boot
- MySQL
- Postman

## Contribuir

¡Gracias por tu interés en contribuir a este proyecto! Aquí te explicamos cómo puedes hacerlo.

### Pasos para contribuir:

1. **Haz un fork del repositorio**:
    - Haz clic en el botón "Fork" en la parte superior derecha de esta página del repositorio.
    - Esto creará una copia de este repositorio en tu cuenta de GitHub.

2. **Clona tu repositorio forked**:
    - Clona tu copia del repositorio en tu máquina local:
      ```bash
      git clone https://github.com/tu-usuario/tu-repositorio.git
      ```

3. **Crea una rama para tus cambios**:
    - Es recomendable crear una nueva rama para trabajar en nuevas características o correcciones de errores:
      ```bash
      git checkout -b nombre-de-tu-rama
      ```

4. **Realiza tus cambios**:
    - Realiza las modificaciones necesarias en tu rama. Puedes agregar nuevas características, corregir errores o mejorar la documentación.

5. **Haz commit de tus cambios**:
    - Una vez que hayas hecho los cambios, realiza un commit con un mensaje claro de lo que hiciste:
      ```bash
      git add .
      git commit -m "Descripción de lo que hiciste"
      ```

6. **Sube tus cambios a GitHub**:
    - Empuja tus cambios a tu repositorio en GitHub:
      ```bash
      git push origin nombre-de-tu-rama
      ```

7. **Crea un Pull Request (PR)**:
    - Ve a tu repositorio en GitHub y haz clic en "New Pull Request" para comparar los cambios que hiciste con la rama principal (`main` o `master`).
    - Deja una descripción clara de lo que estás haciendo en el Pull Request.

### Directrices para la contribución

- **Mantén el estilo de código**: Asegúrate de seguir las convenciones de estilo que ya están presentes en el código del proyecto.
- **Escribe pruebas**: Si es posible, incluye pruebas para cualquier nueva funcionalidad que agregues.
- **Documentación**: Si realizas cambios significativos, actualiza la documentación del proyecto, como este archivo `README.md`.

¡Gracias por contribuir!


## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](./LICENSE) para más detalles.
