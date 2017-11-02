/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.camel.kt.support

import org.apache.camel.CamelContext
import org.apache.camel.model.RouteDefinition
import org.apache.camel.model.RoutesDefinition
import org.apache.camel.spring.boot.CamelAutoConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@AutoConfigureAfter(CamelAutoConfiguration::class)
@ConditionalOnBean(CamelContext::class)
@ConditionalOnProperty("com.github.lburgazzoli.camel.kt.enabled", matchIfMissing = true)
@EnableConfigurationProperties(CamelRoutesDefinitionCollectorConfiguration::class)
open class CamelRoutesDefinitionCollector {
    @Autowired
    lateinit var context: CamelContext
    @Autowired(required = false)
    lateinit var routesDefinitionList: List<RoutesDefinition>
    @Autowired(required = false)
    lateinit var routeDefinitionList: List<RouteDefinition>

    @PostConstruct
    fun postConstruct() {
        routeDefinitionList?.forEach {
            context.addRouteDefinition(it)
        }
        routesDefinitionList?.forEach {
            context.addRouteDefinitions(it.routes)
        }
    }
}