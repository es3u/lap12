package com.example.lab12.Config;

import com.example.lab12.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/register" ).permitAll()
                .requestMatchers("/api/v1/user/getAllUsers" , "/api/v1/user/deleteUser" , "api/v1/user/updateUser").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/user/deleteUser" , "api/v1/user/updateUser").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/addBlog" ,
                                "/api/v1/blog/deleteBlog/" ,
                                "/api/v1/blog/getBlogById/" ,
                                "/api/v1/blog/getBlogByTitle/" ,
                                    "api/v1/blog/myBlog" ,
                                    "api/v1/blog/updateBlog/").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }


}
