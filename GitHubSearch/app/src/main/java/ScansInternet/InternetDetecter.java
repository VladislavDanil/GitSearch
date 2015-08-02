package ScansInternet;


import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.nitrogenium.githubsearch.R;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class implements methods for testing and activates interenet in his absence.
 * @author Danilov Vladislav
 */
public class InternetDetecter {
    /**
     * check the operation of the Internet
     * @param context the current context
     * @return positive or negative response
     */
    public boolean hasConnection(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        internetConect(context);
        return false;
    }

    /**
     * it displays a window with buttons to activate the Internet
     * @param context the current context
     */
    public void internetConect(final Context context){
        final Dialog dialogConnect = new Dialog(context);
        dialogConnect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final LinearLayout alertDialogCust;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        alertDialogCust = (LinearLayout) inflater.inflate(R.layout.verificator_internet, null);
        dialogConnect.setContentView(alertDialogCust);
        Button wifi = (Button) alertDialogCust.findViewById(R.id.wifi);
        Button mobil = (Button) alertDialogCust.findViewById(R.id.mobil);
        Button exit = (Button) alertDialogCust.findViewById(R.id.exit_conect);
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
                dialogConnect.cancel();
            }
        });
        mobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                Class conmanClass = null;
                try {
                    conmanClass = Class.forName(conman.getClass().getName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Field connectivityManagerField = null;
                try {
                    connectivityManagerField = conmanClass.getDeclaredField("mService");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                connectivityManagerField.setAccessible(true);
                Object connectivityManager = null;
                try {
                    connectivityManager = connectivityManagerField.get(conman);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Class connectivityManagerClass = null;
                try {
                    connectivityManagerClass = Class.forName(connectivityManager.getClass().getName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Method setMobileDataEnabledMethod = null;
                try {
                    setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                setMobileDataEnabledMethod.setAccessible(true);

                try {
                    setMobileDataEnabledMethod.invoke(connectivityManager, true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                dialogConnect.cancel();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dialogConnect.cancel();
            }
        });
        dialogConnect.show();
        return;
    }
}
