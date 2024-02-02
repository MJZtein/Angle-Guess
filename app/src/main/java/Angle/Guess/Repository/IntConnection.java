package Angle.Guess.Repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.widget.Toast;

public class IntConnection {
    private final Context context;

    public IntConnection(Context context) {
        this.context = context;
    }

    public boolean getInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }

    public boolean getNoInternetConnection() {
        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        return true;
    }
}
