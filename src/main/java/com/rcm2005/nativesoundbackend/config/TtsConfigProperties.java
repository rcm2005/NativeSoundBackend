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
@Validated  //ativa a validação automatica do jakarta validation
public record TtsConfigProperties(
        @NotBlank(message = "Provider deve ser especificado (Ex: openai, azure, local)")
        String provider,

        @NotBlank(message = "Base do URL do TTS provider é obrigatória")
        String baseUrl, //camelCase padrão Spring (base-url no yml vira baseURL


)