package com.levi.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          
          .authorizeRequests()
          .antMatchers("/","/home").permitAll()
          .antMatchers("/admin").hasRole("ADMIN")
          .antMatchers("/user").hasRole("USER")
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login")
          .permitAll()
          .and()
          .logout()
          .permitAll();
        http.csrf().disable();
        http.sessionManagement().maximumSessions(2);
       
    }
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
     .usersByUsernameQuery(
      "select name, password, enabled from usuario where name=?")
     .authoritiesByUsernameQuery(
     "select name, role from client_roles where name=?");
    } 
}