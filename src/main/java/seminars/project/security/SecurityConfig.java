package seminars.project.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource securityDataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public SecurityConfig(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }


    // Configure authentication using JDBC authentication with the provided DataSource.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll() // Allow public access to the home page
                .antMatchers("/search").permitAll() // Allow public access to the search page
                .antMatchers("/cart/**").permitAll() // Allow public access to cart-related endpoints
                .antMatchers("/seminar/**").hasAuthority("ADMIN") // Require ADMIN authority for /seminar/**
                .antMatchers("/orders/**").hasAuthority("ADMIN") // Require ADMIN authority for /orders/**
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");


        http.headers().frameOptions().disable();
    }

}
