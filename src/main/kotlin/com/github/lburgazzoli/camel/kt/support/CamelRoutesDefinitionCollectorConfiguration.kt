package com.github.lburgazzoli.camel.kt.support

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "com.github.lburgazzoli.camel.kt")
class CamelRoutesDefinitionCollectorConfiguration {
    var enabled: Boolean = true
}