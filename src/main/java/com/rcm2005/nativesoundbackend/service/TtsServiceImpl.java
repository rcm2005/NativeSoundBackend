package com.rcm2005.nativesoundbackend.service;

import com.rcm2005.nativesoundbackend.client.TtsProviderClient;
import com.rcm2005.nativesoundbackend.config.TtsConfigProperties;
import org.springframework.stereotype.Service;

@Service
public class TtsServiceImpl implements TtsService {

    private final TtsConfigProperties config;
    private final TtsProviderClient ttsProviderClient;

    public TtsServiceImpl(TtsConfigProperties config, TtsProviderClient ttsProviderClient) {
        this.config = config;
        this.ttsProviderClient = ttsProviderClient;
    }

    @Override
    public byte[] synthesize(String text, String lang) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Texto não pode ser vazio");
        }

        int max = config.safeMaxTextChars();
        if (text.length() > max) {
            throw new IllegalArgumentException("Texto excede limite permitido: " + max + " caracteres");
        }

        // Aqui você pode adicionar mais tratamentos:
        // - trim, sanitize (remover chars estranhos)
        // - checar lang suportado (ex: config.getSupportedLanguages().contains(lang))
        // - aplicar defaults: if (lang == null) lang = "pt-BR";

        // Chama o provider real
        return ttsProviderClient.synthesize(text, lang);
    }
}