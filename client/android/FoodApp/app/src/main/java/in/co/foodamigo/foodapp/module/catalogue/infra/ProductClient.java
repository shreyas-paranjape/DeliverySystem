package in.co.foodamigo.foodapp.module.catalogue.infra;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.module.catalogue.domain.Product;
import in.co.foodamigo.foodapp.module.catalogue.domain.ProductCategory;
import in.co.foodamigo.foodapp.infra.json.JsonConverter;
import in.co.foodamigo.foodapp.infra.persist.RealmManager;
import in.co.foodamigo.foodapp.infra.rest.ServiceClient;

public class ProductClient {

    private static final String TAG = ProductClient.class.getName();

    public static List<Product> getProducts() {
        return null;
    }

    public static void fetchAndSaveProductCatalogue(final Context context) {
        RequestParams params = new RequestParams();
        params.add("name", "food");
        ServiceClient.get("/catalogue", params,
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable) {
                        Log.i(TAG, "Error : " + throwable);
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

