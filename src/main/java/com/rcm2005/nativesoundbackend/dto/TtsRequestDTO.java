package com.rcm2005.nativesoundbackend.dto;


import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
dto/

TtsRequestDTO.java
text, lang, voice, speed, format... + validações

TtsMetaResponseDTO.java (se você optar por URL)
requestId, audioUrl, expiresAt

Se áudio vier direto: nem precisa ResponseDTO, você retorna byte[]

Pecado mortal: não limitar tamanho de text.
 */
@SpringBootApplication
public class TtsRequestDTO {

    private String text;
    private String lang;

}
