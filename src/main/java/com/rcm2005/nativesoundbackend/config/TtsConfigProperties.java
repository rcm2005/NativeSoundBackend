package com.rcm2005.nativesoundbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;

/**
 * Configurações centralizadas para o TTS.
 * Prefixo: tts
 * Exemplo em application.yml:
 * tts:
 *   provider: openai          # ou azure, aws-polly, local, elevenlabs, etc.
 *   base-url: https://api.openai.com/v1
 *   api-key: sk-...
 *   connect-timeout: 5s
 *   read-timeout: 30s
 *   max-text-chars: 4096
 *   allowed-langs:
 *     - pt-BR
 *     - en-US
 *     - es
 */
@ConfigurationProperties(prefix = "tts")
@Validated  // Ativa validação automática (Jakarta Validation)
public record TtsConfigProperties(

        @NotBlank(message = "Provider deve ser especificado (ex: openai, azure, local)")
        String provider,

        @NotBlank(message = "Base URL do TTS provider é obrigatória")
        String baseUrl,  // camelCase padrão Spring (base-url no yml vira baseUrl)

        @NotNull
        Duration connectTimeout,  // Use Duration pra timeouts (mais type-safe que int ms)

        @NotNull
        Duration readTimeout,

        @Min(value = 100, message = "max-text-chars deve ser pelo menos 100")
        @Max(value = 10000, message = "max-text-chars não deve exceder 10000")
        int maxTextChars,

        List<@NotBlank String> allowedLangs,

        String apiKey  // Pode ser null se for local sem key

) {

        /**
         * Retorna o limite máximo de caracteres, com fallback seguro.
         */
        public int safeMaxTextChars() {
                return maxTextChars > 0 ? maxTextChars : 5000;
        }

        /**
         * Verifica se um idioma é permitido (case-insensitive).
         */
        public boolean isLanguageAllowed(String lang) {
                if (lang == null || allowedLangs == null || allowedLangs.isEmpty()) {
                        return true; // Se não configurado, aceita tudo (pra dev)
                }
                return allowedLangs.stream()
                        .anyMatch(allowed -> allowed.equalsIgnoreCase(lang));
        }

        // Se quiser timeouts em ms (pra compatibilidade com RestClient/WebClient)
        public int connectTimeoutMs() {
                return (int) connectTimeout.toMillis();
        }

        public int readTimeoutMs() {
                return (int) readTimeout.toMillis();
        }
}