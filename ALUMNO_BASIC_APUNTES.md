// OBJETIVO: Crear una API REST básica que permita guardar alumnos en una lista
// y recuperarlos por su id, usando Spring Boot.

    @RestController                             // Le dice a Spring que esta clase va a responder peticiones HTTP
    @RequestMapping("/alumnos")                 // Todas las URLs de esta clase empezarán por /alumnos
    public class AlumnoController {

                                                // Lista que actúa como "base de datos" temporal (se borra al reiniciar la app)
    private List<Alumno> listaAlumnos = new ArrayList<>();

    @GetMapping("/{id}")                                    // Responde a GET /alumnos/5 (el número es el id)
    public Alumno getAlumno(@PathVariable int id) {         // @PathVariable captura el {id} de la URL y lo mete en la variable id
        return listaAlumnos.get(id);                        // Devuelve el alumno que está en la posición id de la lista
    }


    // GET /alumnos/carrera/Teleco → busca por carrera
    @GetMapping("/carrera/{carrera}")
    public List<Alumno> getAlumnosPorCarrera(@PathVariable String carrera) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno a : listaAlumnos) {
            if (a.getCarrera().equals(carrera)) {
                resultado.add(a);
            }
        }
        return resultado;
    }



    @PostMapping                                                // Responde a POST /alumnos (para crear un alumno nuevo)
    public Alumno crearAlumno(@RequestBody Alumno alumno) {     // @RequestBody recibe los datos del alumno desde el cuerpo de la petición HTTP 
                                                                    y los convierte en un objeto Alumno. El nombre "crearAlumno" es solo para 
                                                                    que el programador entienda qué hace, Spring lo ignora.
        listaAlumnos.add(alumno);                               // Mete el alumno recibido en la lista
        return alumno;                                          // Devuelve el alumno que acabas de crear
    }
    }


---------------------------------


// OBJETIVO: Definir la estructura de un Alumno, indicando qué datos tiene
// y cómo leerlos o modificarlos.

    public class Alumno {
    private int id;        // Identificador único del alumno
    private String nombre; // Nombre del alumno
    private String email;  // Email del alumno

    
// Getters: métodos para leer los valores de cada campo
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

// Setters: métodos para modificar los valores de cada campo

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    }