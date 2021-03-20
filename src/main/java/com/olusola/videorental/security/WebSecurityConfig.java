package com.olusola.videorental.security;

import com.olusola.videorental.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserDetailsServiceImpl userServiceDetails;
        private final JwtRequestFilter jwtRequestFilter;

        @Autowired
        public WebSecurityConfig(UserDetailsServiceImpl userServiceDetails,
                                 JwtRequestFilter jwtRequestFilter){
            this.userServiceDetails = userServiceDetails;
            this.jwtRequestFilter = jwtRequestFilter;
        }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.httpBasic().and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif",
                                "/**/*.svg",  "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/api/v1/movies").hasAnyAuthority("ADMIN","USER")
                    .anyRequest().authenticated().and().
                    exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userServiceDetails);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
