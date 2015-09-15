package in.co.foodamigo.monitoringapp.util;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public abstract class HttpClient extends AsyncHttpClient {

    @Override
    public RequestHandle get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.get(getAbsoluteUrl(url), params, responseHandler);
    }

    @Override
    public RequestHandle post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.post(getAbsoluteUrl(url), params, responseHandler);
    }

    protected abstract String getAbsoluteUrl(String relativeUrl);

}
