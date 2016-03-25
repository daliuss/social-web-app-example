package com.example.web.service

import java.net.URL

import org.springframework.core.env.ConfigurableEnvironment

case class ServerConfig(url: URL)

case class Config(serverConfig: ServerConfig)

object Config {

  def apply(env: ConfigurableEnvironment): Config = {
    Config(
      serverConfig = ServerConfig(
        url = new URL(env.getProperty("server.url"))
      )
    )
  }
}
