package in.co.foodamigo.foodapp.service.rest;


import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

public class PingClient {

    private static final String TAG = PingClient.class.getName();

    public static void ping() {
        RequestParams params = new RequestParams();
        params.add("key", "value");
        FoodAmigoClient.get("/customer", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i(TAG, "Object : " + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG, "Error : " + throwable.getMessage());
            }
        });
    }
}
