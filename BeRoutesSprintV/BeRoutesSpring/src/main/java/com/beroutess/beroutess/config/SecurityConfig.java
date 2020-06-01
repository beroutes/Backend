package com.beroutess.beroutess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/usersProfiles").not().hasAnyAuthority("ROLE_USER","ROLE_TRAVELLER")
		//.antMatchers(HttpMethod.GET,"/*").hasAnyAuthority("ROLE_USER")
		.antMatchers(HttpMethod.GET,"/**").hasAnyAuthority("ROLE_ADMIN","ROLE_TRAVELLER")
		.antMatchers(HttpMethod.POST,"/userProfile").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
		.antMatchers(HttpMethod.POST,"/**").hasAnyAuthority("ROLE_ADMIN","ROLE_TRAVELLER")
		.anyRequest().hasAnyAuthority("ROLE_ADMIN")
		.and().httpBasic();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication )
			throws Exception{
		authentication.inMemoryAuthentication()
			.withUser("admin")
			.password(passwordEncoder().encode("admin"))
			.authorities("ROLE_ADMIN")
			.and()
			.withUser("user")
			.password(passwordEncoder().encode("user"))
			.authorities("ROLE_USER")
			.and()
			.withUser("traveller")
			.password(passwordEncoder().encode("traveller"))
			.authorities("ROLE_TRAVELLER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	
		return new BCryptPasswordEncoder();
	}
}
