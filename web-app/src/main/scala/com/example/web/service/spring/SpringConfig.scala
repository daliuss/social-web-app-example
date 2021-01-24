package com.example.web.service.spring

import com.example.web.controllers.HomeController
import com.example.web.service.Config
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.http.HttpStatus
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableAutoConfiguration
@ComponentScan
class SpringConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  var config: Config = _

  @Bean def config(env: ConfigurableEnvironment) = {
    Config(env)
  }

  @Bean
  def objectMapper(builder: Jackson2ObjectMapperBuilder) = {
    val om = new ObjectMapper
    om.registerModule(DefaultScalaModule)
    om
  }

  @Bean def home(config: Config) = new HomeController(config)

  @Override
  override def configure(http: HttpSecurity) {
    http
      .authorizeRequests {
        a =>
          if (config.systemUnderTest) {
            a.anyRequest().permitAll()
          } else {
            a.antMatchers("/", "/error", "/webjars/**").permitAll()
              .anyRequest().authenticated()
          }
      }
      .logout {
        l => l.logoutSuccessUrl("/").permitAll()
      }
      .exceptionHandling(e => e
        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      )
      .csrf(c => c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
      .oauth2Login()
  }

}
