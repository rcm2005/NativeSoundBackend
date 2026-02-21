package com.rcm2005.nativesoundbackend.service;

import com.rcm2005.nativesoundbackend.config.TtsConfigProperties;
import org.springframework.stereotype.Service;


@Service
public class TtsServiceImpl implements TtsService {

    private final TtsConfigProperties config;

    public TtsServiceImpl(TtsConfigProperties config) {
        this.config = config;
    }


    @Override
    public byte[] synthesize(String text, String lang) {

        int max = config.safeMaxTextChars();

        if (text.length() > max) {
            throw new IllegalArgumentException("Texto excede limite permitido");
        }

        // aqui depois virá a chamada HTTP para o provider TT
        return new byte[0];
    }
}



