package in.co.foodamigo.foodapp.service.rest;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.domain.product.Product;
import in.co.foodamigo.foodapp.domain.product.ProductCategory;
import in.co.foodamigo.foodapp.events.Event;
import in.co.foodamigo.foodapp.service.json.JsonConverter;
import in.co.foodamigo.foodapp.service.repository.RealmManager;

public class ProductClient {


    private static final String TAG = ProductClient.class.getName();

    public static List<Product> getProducts() {
        return null;
    }

    public static void fetchAndSaveProductCatalogue(final Context context) {
        FoodAmigoClient.get("/catalogue", null,
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable) {
                        Log.i(TAG, "Error : " + throwable);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          String responseString) {
                        ProductCategory food =
                                JsonConverter.serialize(responseString, ProductCategory.class);
                        RealmManager.persist(context, food);
                        Log.i(TAG, "Got catalogue");
                        EventBus.getDefault().post(new Event.CatalogueRefreshed());
                    }
                });
    }
}

