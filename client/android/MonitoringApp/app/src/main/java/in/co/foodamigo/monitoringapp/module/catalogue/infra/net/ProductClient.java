package in.co.foodamigo.monitoringapp.module.catalogue.infra.net;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.monitoringapp.infra.json.JsonConverter;
import in.co.foodamigo.monitoringapp.infra.persist.RealmManager;
import in.co.foodamigo.monitoringapp.infra.rest.ServiceClient;
import in.co.foodamigo.monitoringapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.monitoringapp.module.common.view.app.NetworkFragment;

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
                        EventBus.getDefault().post(new NetworkFragment.NetworkErrorEvent());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          String responseString) {
                        RealmManager.persist(
                                JsonConverter.unmarshal(responseString, ProductCategory.class));
                       // EventBus.getDefault().post(new CatalogueActivity.CatalogueRefreshedEvent());
                    }
                });
    }
}

