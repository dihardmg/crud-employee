package com.dihardmg.employee.crudemployee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author : Otorus
 * @since : 12/23/17
 */
@Configuration
@EnableWebSecurity
public class KondigurasiSecurity extends WebSecurityConfigurerAdapter{
    public static final String SQL_LOGIN
            ="select username, password, active as enabled \n" +
            "from s_users where username = ?";

    public static final String SQL_PERMISSION
            = "select u.username, r.nama as authority\n" +
            "from s_users u join s_user_role ur on u.id = ur.id_user\n" +
            "join s_roles r on ur.id_role = r.id\n" +
            "where u.username = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configurGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION)
                .passwordEncoder(passwordEncoder()); // bcrypt

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/employee/list", true)
                .and()
                .logout();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
