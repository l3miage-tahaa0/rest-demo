package com.anthonytaha.rest.security;

import com.anthonytaha.rest.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTConfig jwtConfig;
    public SecurityConfig(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, JWTConfig jwtConfig) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authMB = http.getSharedObject(AuthenticationManagerBuilder.class);

        authMB.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authMB.build();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,userDetailsService,jwtConfig);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.SIGN_IN_URL);
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL).permitAll()
                    .anyRequest().authenticated()

            ).authenticationManager(authenticationManager)
            .addFilter(authenticationFilter)
            .addFilter(new AuthorizationFilter(authenticationManager,jwtConfig));
        return http.build();
    }

}
