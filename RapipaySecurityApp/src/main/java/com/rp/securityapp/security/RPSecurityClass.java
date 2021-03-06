package com.rp.securityapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RPSecurityClass extends WebSecurityConfigurerAdapter {

	// use for user creation
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication()
		.withUser("mike").password("mike123").roles("admin");
		
		auth.inMemoryAuthentication()
		.withUser("neha").password("neha123").roles("client");
		*/
		auth.inMemoryAuthentication()
		.withUser("mike").password(this.passwordEncoder().encode("mike123")).roles("admin");
		
		auth.inMemoryAuthentication()
		.withUser("neha").password(this.passwordEncoder().encode("mike123")).roles("client");
	}

	// use for Align roles and resources
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.
			authorizeRequests().

			antMatchers("/public/**").permitAll().
			antMatchers("/admin/**").hasRole("admin").
			antMatchers("/client/**").hasAnyRole("admin","client").
			anyRequest().authenticated().and().httpBasic();
			//and().formLogin().and().httpBasic();
		
	}//end of http Security
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}//end class


/*
 *  mike - admin
 *  neha - client
 *  
 * 
 * */



/*
 
  http.csrf().disable().cors().disable().

				authorizeRequests().

				antMatchers("/public/**").permitAll().antMatchers("/admin/**").hasRole("admin")
				.antMatchers("/employee/**").hasAnyRole("employee", "admin").

				and().formLogin().and().httpBasic();

  
  */
