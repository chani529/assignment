package com.kakao.son

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SonApplication

fun main(args: Array<String>) {
	runApplication<SonApplication>(*args)
}
