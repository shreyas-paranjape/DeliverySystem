package in.co.foodamigo.foodapp.catalogue.infra;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import common.infra.json.JsonConverter;
import common.infra.persist.RealmManager;
import common.module.common.infra.NetworkErrorEvent;
import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.app.ServiceClient;
import in.co.foodamigo.foodapp.catalogue.domain.ProductCategory;

public class ProductClient extends ServiceClient {

    private static final String relURL = "/catalogue";
    private final Context context;

    public ProductClient(Context context) {
        this.context = context;
    }

    public void fetchAndSaveProductCatalogue() {
        RequestParams params = new RequestParams();
        params.add("name", "food");
        get(relURL, params,
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable) {
                        EventBus.getDefault().post(new NetworkErrorEvent());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          String responseString) {
                        RealmManager.persist(context,
                                JsonConverter.unmarshal(responseString, ProductCategory.class));
                        EventBus.getDefault().post(new CatalogueRefreshedEvent());
                    }
                });
    }
}

