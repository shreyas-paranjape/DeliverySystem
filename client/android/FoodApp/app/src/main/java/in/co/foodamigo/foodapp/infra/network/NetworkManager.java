package in.co.foodamigo.foodapp.infra.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {

    private final Context context;
    private ConnectivityManager connectivityManager;
    private NetworkInfo activeNetwork;

    public NetworkManager(Context context) {
        this.context = context;
    }

    private void ensureConnectivityManager() {
        connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        ensureConnectivityManager();
        activeNetwork = connectivityManager
                .getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public boolean isWifi() {
        return isConnected() &&
                (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI);
    }
}
