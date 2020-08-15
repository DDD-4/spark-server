package com.spark.poingpoing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class PoingPoingApplication

fun main(args: Array<String>) {
    runApplication<PoingPoingApplication>(*args)
}
