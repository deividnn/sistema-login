/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author DeividnN
 */
@Controller
public class LoginController {

    public String pegaUsuario() {
        String usuarioLogado = null;
        Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usuario instanceof UserDetails) {
            usuarioLogado = ((UserDetails) usuario).getUsername();
        } else {
            usuarioLogado = usuario.toString();
        }

        return usuarioLogado;
    }

    @RequestMapping(value = {"/","/inicio"},method = RequestMethod.GET)
    public String inicio() {
        return "inicio";
    }
    
    @RequestMapping(value = {"/acesso"},method = RequestMethod.GET)
    public String acesso() {
        return "acesso";
    }
    
    @RequestMapping(value = {"/admin"},method = RequestMethod.GET)
    public String admin(ModelMap model) {
        model.addAttribute("usuario", pegaUsuario());
        return "admin";
    }
    
    @RequestMapping(value = {"/user"},method = RequestMethod.GET)
    public String user(ModelMap model) {
        model.addAttribute("usuario", pegaUsuario());
        return "user";
    }
    
    @RequestMapping(value = {"/acessoNegado"},method = RequestMethod.GET)
    public String acessoNegado(ModelMap model) {
        model.addAttribute("usuario", pegaUsuario());
        return "acessoNegado";
    }
    
    @RequestMapping(value = {"/sair"},method = RequestMethod.GET)
    public String admin(HttpServletRequest req,HttpServletResponse res) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
        new SecurityContextLogoutHandler().logout(req, res, auth);
        }
        return "redirect:/acesso?sair";
    }
}
