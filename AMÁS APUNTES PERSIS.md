En tu proyecto estudio hasta ahora, el contador vivía así:

// ModeloContador.java
    public class ModeloContador {
        private String nombre;
        private int valor; }

Y en el controlador tenías algo como:

// ControladorRest.java
    private Map<String, ModeloContador> contadores = new HashMap<>();

Ese HashMap es memoria RAM. Cuando Spring Boot arranca, el mapa está vacío. Cuando paras la app, todo desaparece.

Sin persistencia, cada vez que reinicias la app pierdes todos los datos.
La persistencia los guarda en una base de datos para que sobrevivan para siempre.