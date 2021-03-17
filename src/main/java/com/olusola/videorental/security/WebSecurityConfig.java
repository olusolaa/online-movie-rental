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
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.olusola.videorental.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserDetailsServiceImpl userServiceDetails;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public WebSecurityConfig(UserDetailsServiceImpl userServiceDetails,
                                 PasswordEncoder passwordEncoder){
            this.userServiceDetails = userServiceDetails;
            this.passwordEncoder = passwordEncoder;
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif",
                                "/**/*.svg",  "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js")
                    .permitAll()
                    //.antMatchers("/api/**").hasRole(USER.name())
                    .anyRequest()
                    .authenticated()
                    .and().httpBasic();
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userServiceDetails);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
