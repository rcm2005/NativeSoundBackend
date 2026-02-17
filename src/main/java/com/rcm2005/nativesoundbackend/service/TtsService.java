package com.rcm2005.nativesoundbackend.service;

import com.rcm2005.nativesoundbackend.config.TtsConfigProperties;

public interface TtsService {

    byte[] synthesize(String text, String lang);

}
