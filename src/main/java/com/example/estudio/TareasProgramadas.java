package com.example.estudio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
// Spring la detecta al arrancar y la mantiene activa en memoria tdo el tiempo que la app esté corriendo.
public class TareasProgramadas {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // Crea una herramienta para escribir mensajes en la consola. Es como un System.out.println.


    @Scheduled(fixedRate = 300000)
    // 300000 ms = 300 segundos = 5 minutos
    public void ritmoFijo() {
        logger.info("Me ejecuto cada 5 minutos");
        // Escribe ese mensaje en la consola de IntelliJ
    }

    @Scheduled(cron = "0 * * * * *")
    // 0 * * * * * significa "cuando el segundo sea 0", o sea, al inicio de cada minuto.
    public void expresionCron() {
        logger.info("Me ejecuto cuando empieza un nuevo minuto");
    }
}


// ------ TIPOS DE LOGGERS ------

//logger.trace("mensaje");   // máximo detalle, solo para desarrollo
// logger.debug("mensaje");   // detalles para depurar problemas
// logger.info("mensaje");    // flujo normal, tdo va bien
// logger.warn("mensaje");    // algo raro pero la app sigue funcionando
// logger.error("mensaje");   // algo ha fallado gravemente