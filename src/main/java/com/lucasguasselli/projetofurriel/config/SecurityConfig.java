package com.lucasguasselli.projetofurriel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// definindo os end-pois que sao publicos (podem ser acessados por todos usuarios)
	private static final String[] PUBLIC_MATCHES = {
			"/dev-postgre/**",
			"/militares/**",
			"/auxiliosTransporte/*"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//ativando Bean e desabilitando CSRF
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	// acesso basico de multiplas fontes para todos caminhos
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
			return source;
	}
}
