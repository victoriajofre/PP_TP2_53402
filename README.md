# PP_TP2_53402

## Paradigmas de Programación - Trabajo Práctico N° 2

Proyecto desarrollado en **Java** para la materia **Paradigmas de Programación** de la **UTN - Facultad Regional Mendoza**.

El trabajo continúa el sistema de gestión de eventos universitarios desarrollado en el TP1 e incorpora los contenidos correspondientes a los **Ejercicios 1, 2 y 3 del TP2**.

---

## Datos del proyecto

- **Materia:** Paradigmas de Programación
- **Trabajo Práctico:** TP2
- **Legajo:** 53402
- **IDE:** IntelliJ IDEA
- **JDK:** Java 21
- **Estado de entrega:** desarrollado hasta el Ejercicio 3

---

## Descripción general

El sistema permite gestionar eventos universitarios con salas, actividades y estudiantes inscriptos.

Las actividades implementadas son:

- `Charla`
- `Taller`
- `Curso`

A lo largo de los ejercicios se incorporan paquetes, excepciones propias, persistencia mediante serialización, interfaces, certificados, generics, métodos parametrizados acotados y wildcards.

---

## Estructura del proyecto

```text
src
├── excepciones
│   └── CupoExcedidoException.java
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

El repositorio también incluye una carpeta con capturas de la salida por consola.

---

# Ejercicio 1 - Paquetes, excepciones y persistencia

En este ejercicio se reorganizó el proyecto utilizando paquetes y se incorporó una excepción propia:

```java
CupoExcedidoException
```

El método `inscribir()` controla el cupo máximo de cada actividad y lanza la excepción cuando no quedan lugares disponibles.

También se implementó persistencia mediante serialización y deserialización de objetos.

Métodos principales:

```java
persistirEvento(...)
recuperarEvento(...)
```

La ejecución demuestra una inscripción exitosa, una inscripción fallida por cupo completo, manejo de `CupoExcedidoException`, guardado y recuperación del evento y uso de `try-catch-finally`.

---

# Ejercicio 2 - Interfaces y certificados

Se incorporó la interfaz:

```java
Certificable
```

La interfaz representa la capacidad de generar certificados.

Las actividades certificables son `Taller` y `Curso`. La clase `Charla` no implementa `Certificable`.

Ejemplo:

```java
if (actividad instanceof Certificable certificable) {

    String certificado =
            certificable.generarCertificado(
                    inscripcion.getEstudiante()
            );

    System.out.println(certificado);
}
```

También se incorporó la clase `Curso`, que hereda de `Actividad`, implementa `Certificable` y posee un atributo `nivel`.

El costo de materiales del curso depende del nivel:

```text
Nivel 1 → $1000
Nivel 2 → $2000
Nivel 3 → $3000
```

---

# Ejercicio 3 - Generics y wildcards

En este ejercicio se incorporaron métodos genéricos dentro de `EventoUniversitario`.

## Filtrado de actividades por tipo

```java
public <T extends Actividad> List<T>
filtrarActividadesPorTipo(Class<T> tipo)
```

Permite obtener listas correctamente tipadas:

```java
List<Charla> charlas =
        eventoPrincipal.filtrarActividadesPorTipo(Charla.class);

List<Taller> talleres =
        eventoPrincipal.filtrarActividadesPorTipo(Taller.class);

List<Curso> cursos =
        eventoPrincipal.filtrarActividadesPorTipo(Curso.class);
```

El uso de:

```java
<T extends Actividad>
```

limita el tipo genérico a `Actividad` o a cualquiera de sus subclases.

## Cálculo de costos con wildcard

```java
public double calcularCostoMateriales(
        List<? extends Actividad> actividades
)
```

Gracias a:

```java
? extends Actividad
```

el mismo método puede trabajar con `List<Actividad>`, `List<Charla>`, `List<Taller>` y `List<Curso>`.

---

## Ejemplo de salida del Ejercicio 3

```text
======================================
       TP2 - EJERCICIO 3
       GENERICS Y WILDCARDS
======================================
Charlas encontradas: 1
Talleres encontrados: 1
Cursos encontrados: 1

Costo de materiales de talleres: $2000.0
Costo de materiales de cursos: $2000.0
Costo de materiales de todas las actividades: $4000.0
```

---

## Conceptos aplicados

- clases y objetos;
- encapsulamiento;
- constructores;
- constructor de copia;
- miembros `static`;
- constantes `final`;
- agregación;
- composición;
- herencia;
- clases y métodos abstractos;
- polimorfismo;
- colecciones `List` y `ArrayList`;
- paquetes;
- excepciones propias;
- `throw` y `throws`;
- `try-catch-finally`;
- serialización y deserialización;
- interfaces;
- `instanceof`;
- generics;
- métodos parametrizados acotados;
- `Class<T>`;
- wildcards `? extends Actividad`.

---

## Ejecución

1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar Java 21.
4. Ejecutar `App.java`.

Durante la ejecución se solicitan algunos datos por consola, por ejemplo:

- nombre del disertante;
- si el taller requiere notebook;
- nivel del curso.

---

## Resultado esperado

La ejecución debe mostrar correctamente:

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
```

y finalizar con:

```text
Process finished with exit code 0
```

---

## Archivo de persistencia

Durante la ejecución se genera:

```text
evento.dat
```

Este archivo se utiliza para guardar y recuperar un objeto `EventoUniversitario` mediante serialización.

El archivo está excluido del repositorio mediante `.gitignore` porque se genera automáticamente durante la ejecución.

---

## Repositorio

El repositorio contiene:

- código fuente completo hasta el Ejercicio 3;
- archivo `README.md`;
- archivo `.gitignore`;
- capturas de la salida por consola.

---

## Autor

- **Legajo:** 53402
- **Nombre y Apellido:** Victoria Jofre
- **UTN - Facultad Regional Mendoza**
  Versión final de entrega.