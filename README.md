# PP_TP2_53402

## Paradigmas de Programación - Trabajo Práctico N° 2

Proyecto desarrollado en **Java** para la materia **Paradigmas de Programación** de la **UTN - Facultad Regional Mendoza**.

El trabajo implementa un sistema de gestión de eventos universitarios y amplía progresivamente el modelo desarrollado en el TP1, incorporando excepciones, persistencia, interfaces, generics, clases anidadas e hilos.

---

## Datos del proyecto

- **Materia:** Paradigmas de Programación
- **Trabajo Práctico:** TP2
- **Legajo:** 53402
- **IDE:** IntelliJ IDEA
- **JDK:** Java 21

---

## Descripción general

El sistema permite gestionar eventos universitarios con salas, actividades y estudiantes inscriptos.

Las actividades pueden ser:

- `Charla`
- `Taller`
- `Curso`

Además, el sistema permite:

- controlar el cupo máximo de las actividades;
- manejar excepciones propias;
- persistir y recuperar eventos mediante serialización;
- emitir certificados para actividades certificables;
- filtrar actividades mediante generics;
- calcular costos utilizando wildcards;
- generar tickets de acceso para inscripciones confirmadas;
- enviar tickets en un hilo de ejecución independiente.

---

## Estructura del proyecto

```text
src
├── excepciones
│   └── CupoExcedidoException.java
│
├── hilos
│   └── EnvioTicketsThread.java
│
├── modelo
│   ├── actividades
│   │   ├── Actividad.java
│   │   ├── Charla.java
│   │   ├── Taller.java
│   │   └── Curso.java
│   │
│   ├── certificacion
│   │   └── Certificable.java
│   │
│   ├── Estudiante.java
│   ├── EventoUniversitario.java
│   ├── Inscripcion.java
│   └── Sala.java
│
└── App.java
```

---

## Ejercicio 1 - Excepciones y persistencia

Se reorganizó el proyecto utilizando paquetes y se incorporó la excepción propia:

```java
CupoExcedidoException
```

El método de inscripción verifica el cupo disponible y lanza la excepción cuando la actividad se encuentra completa.

También se implementó persistencia mediante serialización y deserialización de objetos.

Métodos principales:

```java
persistirEvento(...)
recuperarEvento(...)
```

El programa prueba:

- una inscripción exitosa;
- una inscripción fallida por cupo completo;
- guardado del evento en archivo;
- recuperación del evento;
- manejo de excepciones con `try-catch-finally`.

---

## Ejercicio 2 - Interfaces y certificados

Se incorporó la interfaz:

```java
Certificable
```

Las actividades certificables son:

- `Taller`
- `Curso`

La clase `Charla` no implementa esta interfaz.

El sistema utiliza polimorfismo de interfaz para generar certificados únicamente cuando una actividad implementa `Certificable`.

Ejemplo:

```java
if (actividad instanceof Certificable certificable) {
    // generar certificado
}
```

También se agregó la clase `Curso`, que hereda de `Actividad` y posee un atributo `nivel`.

---

## Ejercicio 3 - Generics y wildcards

Se implementaron métodos genéricos dentro de `EventoUniversitario`.

### Filtrado por tipo

```java
public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo)
```

Permite obtener listas correctamente tipadas:

```java
List<Charla>
List<Taller>
List<Curso>
```

### Cálculo de costos con wildcard

```java
public double calcularCostoMateriales(
        List<? extends Actividad> actividades
)
```

El mismo método puede recibir listas de `Actividad` o de cualquiera de sus subclases.

---

## Ejercicio 4 - Clases anidadas e hilos

Dentro de `Inscripcion` se implementó la clase anidada:

```java
TicketDeAcceso
```

El ticket se genera únicamente cuando una inscripción es confirmada.

```java
public void confirmar() {
    this.estado = "CONFIRMADA";
    this.ticket = new TicketDeAcceso();
}
```

También se creó:

```java
EnvioTicketsThread
```

que extiende `Thread` y se encarga de enviar los tickets correspondientes a las inscripciones confirmadas.

El hilo se inicia mediante:

```java
envioTicketsThread.start();
```

De esta forma, el envío de tickets se ejecuta concurrentemente mientras el hilo principal continúa mostrando información del evento.

---

## Conceptos aplicados

Durante el desarrollo se utilizaron los siguientes conceptos de Programación Orientada a Objetos y Java:

- encapsulamiento;
- constructores;
- constructor de copia;
- atributos y métodos `static`;
- constantes `final`;
- agregación;
- composición;
- herencia;
- clases y métodos abstractos;
- polimorfismo;
- interfaces;
- excepciones propias;
- `throw` y `throws`;
- `try-catch-finally`;
- serialización y deserialización;
- colecciones `List` y `ArrayList`;
- generics;
- métodos parametrizados acotados;
- `Class<T>`;
- wildcards `? extends`;
- clases anidadas;
- hilos con `Thread`;
- concurrencia básica;
- `start()` y `run()`.

---

## Ejecución

1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar Java 21 o una versión compatible.
4. Ejecutar la clase:

```text
App.java
```

El programa solicitará algunos datos por consola, como:

- nombre del disertante;
- si un taller requiere notebook;
- nivel de un curso.

---

## Resultado esperado

Durante la ejecución se muestran, entre otros, los siguientes resultados:

```text
PRUEBA DE EXCEPCION POR CUPO
Caso fallido controlado: Cupo máximo alcanzado.

PRUEBA DE PERSISTENCIA
Evento guardado correctamente.
Evento recuperado correctamente.

TP2 - EJERCICIO 2
INTERFACES Y CERTIFICADOS

TP2 - EJERCICIO 3
GENERICS Y WILDCARDS

TP2 - EJERCICIO 4
CLASES ANIDADAS E HILOS

[MAIN] El programa principal continúa ejecutándose.
[Hilo-Envio-Tickets] Inicio del envío de tickets.
...
[Hilo-Envio-Tickets] Fin del envío de tickets.
```

---

## Archivo de persistencia

Durante la ejecución se genera el archivo:

```text
evento.dat
```

Este archivo contiene el evento serializado para luego poder ser recuperado mediante deserialización.

---

## Autor

- **Estudiante:**VICTORIA JOFRE
- **Legajo:** 53402
- **UTN - Facultad Regional Mendoza**
