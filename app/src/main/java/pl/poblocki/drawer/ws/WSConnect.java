package pl.poblocki.drawer.ws;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONArray;

/**
 * Created by Iza on 13.03.2017.
 */

public class WSConnect {
    private String url = "ws://localhost:4567/chat";
    private String protocol = "";
    private AsyncHttpClient.WebSocketConnectCallback callback = new AsyncHttpClient.WebSocketConnectCallback() {
        @Override
        public void onCompleted(Exception ex, WebSocket webSocket) {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            webSocket.setStringCallback(new WebSocket.StringCallback() {
                public void onStringAvailable(String s) {
                    System.out.println("I got a string: " + s);
                }
            });
            webSocket.setDataCallback(new DataCallback() {
                public void onDataAvailable(DataEmitter emitter, ByteBufferList byteBufferList) {
                    System.out.println("I got some bytes!");
                    // note that this data has been read
                    byteBufferList.recycle();
                }
            });
        }
    };

    public void connect() {
        AsyncHttpClient.getDefaultInstance().websocket(url, protocol, callback);
    }
}
