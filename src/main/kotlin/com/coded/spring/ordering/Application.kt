package com.coded.spring.ordering

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
    usersConfig.getMapConfig("users-cache").timeToLiveSeconds = 5
}

val usersConfig = Config("users-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(usersConfig)
