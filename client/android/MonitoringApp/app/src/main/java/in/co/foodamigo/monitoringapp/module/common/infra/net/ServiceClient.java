package in.co.foodamigo.monitoringapp.module.common.infra.net;


import in.co.foodamigo.monitoringapp.util.HttpClient;

public class ServiceClient extends HttpClient {

    //private static final String BASE_URL = "https://api.foodamigo.co.in/1/";
    //private static final String BASE_URL = "http://128.199.86.218";
    private static final String BASE_URL = "http://192.168.10.111:3000";

    @Override
    protected String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}