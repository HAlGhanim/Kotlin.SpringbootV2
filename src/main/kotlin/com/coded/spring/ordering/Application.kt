package com.coded.spring.ordering

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
	ordersConfig.getMapConfig("orders-cache").setTimeToLiveSeconds(5)
}

val ordersConfig = Config("orders-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(ordersConfig)
