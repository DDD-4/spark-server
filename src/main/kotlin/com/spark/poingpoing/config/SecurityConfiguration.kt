package com.spark.poingpoing.config

import com.spark.poingpoing.user.UserRepository
import com.spark.poingpoing.user.auth.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfiguration(val userRepository: UserRepository, val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf()
                .disable()
        http.formLogin()
                .disable()
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.cors()
                .configurationSource { CorsConfiguration().applyPermitDefaultValues() }
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(JwtRequestFilter(jwtTokenProvider, userRepository), UsernamePasswordAuthenticationFilter::class.java)
    }


    override fun configure(web: WebSecurity) {
        web.ignoring()
                .antMatchers("/v1/users/sign-up",
                        "/v1/users/login",
                        "/v1/photos/**",
                        "/v3/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**"
                )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}