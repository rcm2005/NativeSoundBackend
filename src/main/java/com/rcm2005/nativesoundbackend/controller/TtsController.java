package com.rcm2005.nativesoundbackend.controller;

import com.rcm2005.nativesoundbackend.service.TtsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * Controller responsável por expor endpoints de Text-to-Speech.
 */
@RestController
@RequestMapping("/api/tts")  // Melhor prefixo: /api/tts (mais RESTful e versionável)
public class TtsController {

    private final TtsService ttsService;

    public TtsController(TtsService ttsService) {
        this.ttsService = ttsService;
    }

    /**
     * Endpoint principal: Gera áudio a partir de texto.
     *
     * @param request Payload com texto e configurações
     * @return Áudio binário (wav/mp3) com headers apropriados
     */
    @PostMapping("/synthesize")
    public ResponseEntity<byte[]> synthesize(
            @RequestBody @Valid TtsSynthesisRequest request) {

        byte[] audioBytes = ttsService.synthesize(
                request.text(),
                request.language()
        );

        // Headers pra download ou playback direto no browser/mobile
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/wav")); // ajuste pra "audio/mpeg" se for MP3
        headers.setContentLength(audioBytes.length);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"synthesized_audio.wav\""); // ou "attachment" pra forçar download

        return ResponseEntity.ok()
                .headers(headers)
                .body(audioBytes);
    }

    /**
     * Health check simples (útil pra Kubernetes, Docker, monitoring).
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("TTS Backend is alive! 🚀");
    }

    // DTO de entrada (use record pra simplicidade e imutabilidade)
    public record TtsSynthesisRequest(

            @NotBlank(message = "Texto é obrigatório")
            @Size(max = 5000, message = "Texto excede limite de caracteres") // backup, mas o service já valida
            String text,

            @NotBlank(message = "Idioma é obrigatório")
            @Size(min = 2, max = 10, message = "Código de idioma inválido (ex: pt-BR)")
            String language

            // Adicione depois: voice, speed, pitch, etc.
    ) {}
}