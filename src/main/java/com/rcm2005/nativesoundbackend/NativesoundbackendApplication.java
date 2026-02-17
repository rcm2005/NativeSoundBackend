package com.rcm2005.nativesoundbackend;

import com.rcm2005.nativesoundbackend.config.TtsConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(TtsConfigProperties.class)
public class NativesoundbackendApplication {

	public static void main(String[] args) {


		SpringApplication.run(NativesoundbackendApplication.class, args);



    }





}
