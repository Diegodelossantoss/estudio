package com.example.estudio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;





@Configuration @EnableWebSecurity @EnableMethodSecurity @EnableScheduling
// @Configuration le dice a Spring que esta clase tiene configuración que debe leer al arrancar
// @EnableWebSecurity activa el sistema de seguridad de Spring.
// @EnableMethodSecurity activa la seguridad a nivel de métodos, controla quien puede y no puede ejecutar uno.
// @EnableScheduling sin esto, los @Scheduled se ignoran completamente

public class ConfiguracionSeguridad {


    @Bean public SecurityFilterChain configuracion(HttpSecurity http) throws Exception {
        // Dice como se entra
        // @Bean es simplemente una etiqueta que le dice a Spring: "esta cosa devuelve un objeto que quiero que tú gestiones".
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                // http presenta la configuración de seguridad de mi app
                // cada .algo es activar una regla
                // la regla anterior "Exige que cualquier petición esté autenticada"
                .formLogin(Customizer.withDefaults())
                // "Activa el formulario de login para el navegador."
                .httpBasic(Customizer.withDefaults())
                // "Acepta también usuario y contraseña mandados directamente en la cabecera."
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
                // "para rutas que empiecen por /api/ no exijas el token CSRF"
        return http.build();
        // Después de configurar todos los interruptores, esta línea construye y aplica toda la configuración.
    }



    @Bean public UserDetailsService usuarios() {
        // Dice quien puede entrar
        // Crea un usuario con los 3 atributos de abajo
        UserDetails user = User.withDefaultPasswordEncoder()
                // UserDetaisl viene de Spring
                .username("usuario")
                .password("clave")
                .roles("USER")
                // esto anterior rellena la ficha
                .build();
                // .build() construye la ficha

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("clave")
                .roles("ADMIN")
                .build();
        // Crea un usuario admin con los anteriores 3 parámetros

        return new InMemoryUserDetailsManager(user, admin);
        // Guarda esta ficha en la memoria
    }
}
