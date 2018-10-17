package com.passing_parcel.api.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Класс отвечающий за авторизацию и аутентификацию пользователей.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM="MY_TEST_REALM";

	@Autowired
	private DataSource dataSource;

//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
//	}


	/**
	 * Метод отвечает за получение из базы данных пользователей логина и пороля.
	 * Проверяет проверяет данные пользователя на наличие в БД.
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled"
						+ " from users where username=?")
				.authoritiesByUsernameQuery("select username, authority "
						+ "from authorities where username=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * Метод определяет уровень доступа пользовтеля к для получения дынны с сервера.
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.authorizeRequests().antMatchers("/**").authenticated();

		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/categories/**").hasAnyRole("ADMIN","USER")
				.and()
				.httpBasic()
				.realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint());


	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}

	/**
	 * Метод задаёт уровень доступа по ролям.
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/header/**");
//		web.ignoring().antMatchers(HttpMethod.GET, "/categories/**");
//		web.ignoring().antMatchers(HttpMethod.POST, "/reservation/**");
	}
}
