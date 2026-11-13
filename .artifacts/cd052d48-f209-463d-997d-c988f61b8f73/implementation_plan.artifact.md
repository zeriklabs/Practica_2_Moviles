# Desarrollo de un CRUD de Empleados en Android con Retrofit

Desarrollar una aplicación móvil para Android utilizando Java y Retrofit que se conecte a la API REST (Swagger `https://fw-ejercicio-08.onrender.com/swagger-ui/index.html#/`). La aplicación permitirá consultar (READ), registrar (CREATE), actualizar (UPDATE) y eliminar (DELETE) empleados/clientes en la plataforma, cumpliendo con los requerimientos solicitados.

## User Review Required

- **Nombre de los campos:** La API está orientada a "Clientes" (las rutas son `/api/v1/clientes`), pero el requerimiento solicita "Empleados". Las clases se nombrarán referenciando al término "Empleado" en el cliente, pero la integración HTTP conectará a la ruta `/api/v1/clientes` con el esquema requerido (id, nombre, correo, teléfono, dirección, registrado).
- **Validaciones:** Se implementarán validaciones básicas de campos vacíos y formato de correo electrónico. ¿Se requiere alguna otra validación estricta?

## Open Questions

Ninguna por el momento.

## Proposed Changes

### Archivo Manifest

- **AndroidManifest.xml:**
    - Agregar el permiso de Internet (`<uses-permission android:name="android.permission.INTERNET" />`).

### Modelos (DTOs)

Se crearán los modelos necesarios para serializar/deserializar el JSON recibido por la API con Gson.
- `Empleado.java`: Clase que representa un empleado (id, nombre, correo, telefono, direccion, registrado).
- `RespuestaLista.java`, `DatosLista.java`, `Embedded.java`: Clases envolventes para deserializar correctamente la respuesta que devuelve `GET /api/v1/clientes` con `_embedded.clienteDtoList`.
- `RespuestaEntidad.java`: Clase envolvente para las peticiones GET (por ID), POST y PUT.
- `RespuestaVacia.java`: Clase para manejar el resultado del DELETE.

### API y Retrofit

- `EmpleadoApi.java`: Interfaz con las anotaciones de Retrofit (`@GET`, `@POST`, `@PUT`, `@DELETE`).
- `RetrofitClient.java`: Singleton para configurar e instanciar Retrofit con Gson y un cliente OkHttp.

### Vistas y UI (Activities & Layouts)

- `MainActivity` y `activity_main.xml`:
  - Contendrá un `RecyclerView` para listar a los empleados y un `FloatingActionButton` (FAB) para agregar uno nuevo.
- `EmpleadoAdapter` y `item_empleado.xml`:
  - Adaptador y diseño para cada ítem de la lista. Mostrará ID, nombre, correo y teléfono. Al hacer clic se navegará al detalle del empleado.
- `FormularioActivity` y `activity_formulario.xml`:
  - Formulario que servirá tanto para **registrar (CREATE)** como para **actualizar (UPDATE)**. Se validarán los campos antes de enviarlos.
- `DetalleActivity` y `activity_detalle.xml`:
  - Pantalla de solo lectura que muestra toda la información del empleado. Tendrá dos botones: **Editar** y **Eliminar (DELETE)**. Al eliminar se mostrará un cuadro de diálogo de confirmación (AlertDialog).

## Verification Plan

### Automated Tests
- Se utilizarán pruebas visuales y de interacción del usuario.

### Manual Verification
1. **Consultar empleados:** Iniciar la app y validar que el `RecyclerView` cargue y se visualicen correctamente los elementos de la API.
2. **Registrar empleado:** Presionar el botón para agregar, ingresar un registro, guardarlo y verificar que aparezca en el listado tras el registro exitoso (comprobación de redirección y carga).
3. **Actualizar empleado:** Seleccionar un empleado de la lista, visualizar sus datos, modificar algún valor (ej. cambiar nombre), guardar y comprobar los cambios en la lista principal y en la API.
4. **Eliminar empleado:** Seleccionar un empleado de la lista, presionar "Eliminar", aceptar el cuadro de diálogo de advertencia y validar que desaparezca del listado.
