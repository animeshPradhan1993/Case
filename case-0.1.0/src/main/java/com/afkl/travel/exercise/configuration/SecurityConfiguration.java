
package com.afkl.travel.exercise.configuration;

import org.springframework.beans.factory.annotation.Value;
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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${case.default.username}")
	private String username;
	@Value("${case.default.password}")
	private String password;
	@Value("${case.default.adminUser}")
	private String adminUser;
	@Value("${case.default.adminPassword}")
	private String adminUserPassword;

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication().withUser(username)
				.password(passwordEncoder().encode(password)).roles("User").and().withUser(adminUser)
				.password(passwordEncoder().encode(adminUserPassword)).roles("Admin");
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll().and().csrf()
				.ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin().and()
				// .authorizeRequests().antMatchers("/locations/**").permitAll().and().csrf()
				// .ignoringAntMatchers("/locations/**").and().headers().frameOptions().sameOrigin().and()
				.csrf().disable()

				.authorizeRequests().antMatchers("/locations/**").hasAnyRole("User", "Admin").and().csrf().disable()
				.authorizeRequests().antMatchers("/actuator/**").hasRole("Admin").and().httpBasic();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * protected void configure2(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests().antMatchers("/h2-console/**").permitAll().and().csrf
	 * () .ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().
	 * sameOrigin(); }
	 */
}
