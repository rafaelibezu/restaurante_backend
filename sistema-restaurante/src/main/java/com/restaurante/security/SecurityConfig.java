package com.restaurante.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/ws-notificaciones/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .requestMatchers("/api/roles/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/usuarios/**").hasRole("ADMINISTRADOR")

                        .requestMatchers("/api/productos/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")
                        .requestMatchers("/api/categorias/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")
                        .requestMatchers("/api/inventario/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")

                        .requestMatchers("/api/mesas/**").hasAnyRole("ADMINISTRADOR", "MESERO", "SUPERVISOR")
                        .requestMatchers("/api/pedidos/**").hasAnyRole("ADMINISTRADOR", "MESERO", "COCINERO", "SUPERVISOR")
                        .requestMatchers("/api/ventas/**").hasAnyRole("ADMINISTRADOR", "CAJERO", "SUPERVISOR")
                        .requestMatchers("/api/dashboard/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")
                        .requestMatchers("/api/clientes/**").hasAnyRole("ADMINISTRADOR", "MESERO", "SUPERVISOR")
                        .requestMatchers("/api/reservas/**").hasAnyRole("ADMINISTRADOR", "MESERO", "SUPERVISOR")
                        .requestMatchers("/api/fidelizacion/**").hasAnyRole("ADMINISTRADOR", "MESERO", "SUPERVISOR")
                        .requestMatchers("/api/promociones/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")
                        .requestMatchers("/api/notificaciones/**").hasAnyRole("ADMINISTRADOR", "MESERO", "SUPERVISOR")
                        .requestMatchers("/api/dashboard/crm/**").hasAnyRole("ADMINISTRADOR", "SUPERVISOR")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}