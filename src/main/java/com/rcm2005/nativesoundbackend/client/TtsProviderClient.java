package com.rcm2005.nativesoundbackend.client;

import com.rcm2005.nativesoundbackend.config.TtsConfigProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class TtsProviderClient {

    private final RestClient restClient;

    public TtsProviderClient(TtsConfigProperties config) {
        this.restClient = RestClient.builder()
                .baseUrl(config.getTtsProviderUrl())  // ex: "http://localhost:8000" ou "https://seu-tts-python"
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Accept", MediaType.APPLICATION_OCTET_STREAM_VALUE) // pro áudio binário
                .build();
    }

    public byte[] synthesize(String text, String lang) {
        // Payload que seu Python espera (ajuste conforme seu endpoint Python)
        var requestBody = new TtsRequest(text, lang); // crie um record ou class simples

        try {
            return restClient.post()
                    .uri("/synthesize")  // ou "/v1/audio/speech", ajuste pro seu Python
                    .body(requestBody)
                    .retrieve()
                    .body(byte[].class);  // direto pra byte[] — simples e funciona pra áudios

        } catch (RestClientException e) {
            // Trate melhor depois: retry, fallback, log
            throw new RuntimeException("Falha ao chamar TTS provider: " + e.getMessage(), e);
        }
    }

    // Record simples pro payload (use Lombok @Value se quiser)
    private record TtsRequest(String text, String language) {}
}