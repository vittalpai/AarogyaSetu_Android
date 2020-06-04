package com.healthtrack.listener;

/**
 * @author Niharika.Arora
 */
public interface QrCodeListener {
    void onQrCodeFetched(String text);

    void onFailure();
}
