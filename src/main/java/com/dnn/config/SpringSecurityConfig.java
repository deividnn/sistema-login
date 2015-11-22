/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 *
 * @author DeividnN
 */
public class SpringSecurityConfig extends AbstractSecurityWebApplicationInitializer {

    @Configuration
    @EnableWebSecurity
    public static class Configuracao extends WebSecurityConfigurerAdapter {

        @Autowired
        private Sucesso sucesso;

        @Autowired
        public void usuarioPadrao(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
            auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
            auth.inMemoryAuthentication().withUser("teste").password("teste").roles("USER").disabled(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/inicio").permitAll()
                    .antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/usuario/**").access("hasRole('USER') or hasRole('ADMIN')")
                    .and().formLogin().loginPage("/acesso")
                    .usernameParameter("usuario").passwordParameter("senha")
                    .successHandler(sucesso)
                    .and().csrf()
                    .and().exceptionHandling().accessDeniedPage("/acessoNegado");
        }
    }

    @Component
    private static class Sucesso extends SimpleUrlAuthenticationSuccessHandler {

        private RedirectStrategy redirect = new DefaultRedirectStrategy();

        @Override
        public void handle(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
            String urlAlvo = determinaURL(auth);

            if (res.isCommitted()) {
                return;
            }

            redirect.sendRedirect(req, res, urlAlvo);
        }

        public Sucesso() {
        }

        private String determinaURL(Authentication auth) {
            String url = "";

            Collection<? extends GrantedAuthority> autorizacoes = auth.getAuthorities();
            List<String> permissoes = new ArrayList<>();

            for (GrantedAuthority g : autorizacoes) {
                permissoes.add(g.getAuthority());
            }
            System.out.println(permissoes);
            if (permissoes.contains("ROLE_ADMIN")) {
                url = "/admin";
            } else if (permissoes.contains("ROLE_USER") || permissoes.contains("ROLE_ADMIN")) {
                url = "/user";
            } else {
                url = "/acessoNegado";
            }

            return url;
        }

        public RedirectStrategy getRedirect() {
            return redirect;
        }

        public void setRedirect(RedirectStrategy redirect) {
            this.redirect = redirect;
        }
        
        
    }
}
