package com.example.ReseptiLista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.ReseptiLista.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return http
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(mvcMatcherBuilder.pattern("/css/**"), mvcMatcherBuilder.pattern("/images/**"), mvcMatcherBuilder.pattern("/home"), mvcMatcherBuilder.pattern("/api/**"), mvcMatcherBuilder.pattern("/etusivu/**"), mvcMatcherBuilder.pattern("/resepti/**"), mvcMatcherBuilder.pattern("/addKommentti/**"), mvcMatcherBuilder.pattern("/saveKommentti/**")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/admin/**"), mvcMatcherBuilder.pattern("/deleteResepti/**"), mvcMatcherBuilder.pattern("/editResepti/**"), mvcMatcherBuilder.pattern("/addresepti/**"), mvcMatcherBuilder.pattern("/save/**")).hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/etusivu", true).permitAll())
            .logout(logout -> logout
                    .logoutSuccessUrl("/etusivu") // Redirect to the home page after logout
                    .permitAll())
                .build();
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
