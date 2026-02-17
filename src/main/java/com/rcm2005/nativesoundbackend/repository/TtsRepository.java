package com.rcm2005.nativesoundbackend.repository;

/*
No teu print, repository era banco.
No NativeSound backend, o “repository” pode ser:

cache de áudio

persistência de requests/logs

storage local/MinIO
Mas o cliente do TTS (HTTP / gRPC / local process) eu colocaria separado (tipo client/ ou provider/).

Se você chamar de repository, beleza… só não mistura “banco” com “chamada de rede”.
 */

/*
repository/

Se você quiser manter esse nome, eu usaria para persistência/caching:

TtsJobRepository.java (pode começar “fake”, em memória)

AudioCacheRepository.java (se você cachear resultado por hash)

Pecado mortal: achar que “repository” tem que existir desde o começo. Se não precisar, não inventa.
 */

public class TtsRepository {
}
