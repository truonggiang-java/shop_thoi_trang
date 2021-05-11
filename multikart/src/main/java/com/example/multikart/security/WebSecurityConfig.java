package com.example.multikart.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.multikart.security.jwt.AuthEntryPointJwt;
import com.example.multikart.security.jwt.AuthTokenFilter;
import com.example.multikart.security.service.UserDetailServiceImpl;
import com.example.multikart.service.CustomerDetailsServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity
(prePostEnabled = true,
proxyTargetClass = true, 
securedEnabled = true, 
jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Order(1)
    @Configuration
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
    	@Autowired
    	private UserDetailServiceImpl userDetailServiceImpl;
    	
    	@Autowired
    	private AuthEntryPointJwt authEntryPointJwt;
    	
    	@Autowired
    	private PasswordEncoder encoder;
    	
    	@Bean
    	public AuthTokenFilter authenticationTokenFilter(){
    		return new AuthTokenFilter();
    	}
    	
    	@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManagerBean();
		}


		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailServiceImpl).passwordEncoder(encoder);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/v1/signin").permitAll().and()
			.antMatcher("/api/v1/**").authorizeRequests().anyRequest().authenticated();
			
			//thêm một lớp filter để kiểm tra jwt
			http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		}
    	
    	
    }
    
    @Configuration
    @Order(2)
    public static class CustomerSecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        private CustomerDetailsServiceImpl customerDetailsService;
        @Autowired
        private PasswordEncoder encoder;
        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(customerDetailsService).passwordEncoder(encoder);
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/thanh-toan").authenticated()
                    .antMatchers("/*").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/dang-nhap")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/danh-sach-san-pham")
                    .permitAll();

        }
    }

}
