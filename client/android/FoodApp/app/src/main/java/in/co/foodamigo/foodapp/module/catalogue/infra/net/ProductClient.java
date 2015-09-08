package in.co.foodamigo.foodapp.module.catalogue.infra.net;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import in.co.foodamigo.foodapp.infra.json.JsonConverter;
import in.co.foodamigo.foodapp.infra.persist.RealmManager;
import in.co.foodamigo.foodapp.module.common.view.app.NetworkFragment;
import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.module.common.infra.net.ServiceClient;
import in.co.foodamigo.foodapp.module.catalogue.view.app.CatalogueActivity;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;

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
                        RealmManager.persist(context,
                                JsonConverter.unmarshal(responseString, ProductCategory.class));
                        EventBus.getDefault().post(new CatalogueActivity.CatalogueRefreshedEvent());
                    }
                });
    }
}

