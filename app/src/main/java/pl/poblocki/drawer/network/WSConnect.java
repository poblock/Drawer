package pl.poblocki.drawer.network;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;

/**
 * Created by Iza on 13.03.2017.
 */

public class WSConnect {
    private String url = "ws://10.0.2.2:8080/chat";
    private String protocol = "";
    private AsyncHttpClient.WebSocketConnectCallback callback;

    public void connect(final WebSocket.StringCallback wscallback) {
        callback = new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                webSocket.setStringCallback(wscallback);
            }
        };
        AsyncHttpClient.getDefaultInstance().websocket(url, protocol, callback);
    }
}
