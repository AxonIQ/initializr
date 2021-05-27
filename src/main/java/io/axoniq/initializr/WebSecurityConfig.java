package io.axoniq.initializr;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/actuator/health").permitAll()
                .antMatchers("/actuator/**").hasRole("METRICSADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }
}

