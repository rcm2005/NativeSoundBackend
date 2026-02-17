package com.rcm2005.nativesoundbackend.config;
/*
config/

TtsConfigProperties.java (@ConfigurationProperties("tts"))

CorsConfig.java (se necessário)

HttpClientConfig.java (timeouts, etc)

Pecado mortal: espalhar config pelo código (string de URL hardcoded).
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "tts")
public class TtsConfigProperties {


    //Nome do servidor ativo   (open ai, azure, aws, local)
    private String provider;

    //url base do motor TTS
    private String BaseURL;

    //timeouts HTTP
    private int connectTimeoutMs;
    private int readTimeoutsMs;


    //limite máximo de caracteres aceitos
    private int maxTextChars;

    //idiomas permitidos
    private List<String> allowedLangs;

    private String apiKey;

    public int safeMaxTextChars(){
        if (safeMaxTextChars() <= 0){
            return 5000;
        } return maxTextChars;
    }


}
