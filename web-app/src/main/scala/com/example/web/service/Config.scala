package com.example.web.service

import java.net.URL

import org.springframework.core.env.ConfigurableEnvironment

case class ServerConfig(url: URL)

case class Config(serverConfig: ServerConfig, systemUnderTest: Boolean)

object Config {

  def apply(env: ConfigurableEnvironment): Config = {
    assertProperty("server.url", env)
    assertProperty("server.port", env)
    assertProperty("google.client.id", env)
    assertProperty("google.client.secret", env)

    Config(
      serverConfig = ServerConfig(
        url = new URL(env.getProperty("server.url"))
      ),
      systemUnderTest = env.getProperty("testing", classOf[Boolean], false)
    )
  }

  def assertProperty(property: String, env: ConfigurableEnvironment): Unit = {
    assert(env.getProperty(property) != null, s"Missing property $property")
  }
}
