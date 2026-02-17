package com.rcm2005.nativesoundbackend.controller;

/*
controller/

TtsController.java
Endpoint tipo POST /api/tts/synthesize

(opcional) HealthController.java
GET /health (pra você saber se tá vivo)

Pecado mortal: controller fazendo HttpClient na mão.
 */

import com.rcm2005.nativesoundbackend.service.TtsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tts")
@RestController
public class TtsController {

    private final TtsService ttsService;

    public TtsController(TtsService ttsService){
        this.ttsService = ttsService;
    }




}
