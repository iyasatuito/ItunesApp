package com.pia.appetiser.test.presentation.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.text.format.Formatter
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {


        if (isNetworkConnected(context)) {
            LocalBroadcastManager.getInstance(context!!).sendBroadcast(Intent(BROADCAST_NETWORK_CONNECTION_CHANGED))

            //do some stuff
        } else {
            //do smoke stuff
        }
    }

    private fun isNetworkConnected(context: Context?): Boolean {


        val manager = context?.getSystemService(WIFI_SERVICE) as WifiManager
        val dhcp = manager.dhcpInfo
        val address = Formatter.formatIpAddress(dhcp.gateway)


        val connectivityManager: ConnectivityManager? =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager?
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                if (networkInfo != null) {
                    return networkInfo.isConnected
                            && networkInfo.type == ConnectivityManager.TYPE_WIFI
                            || networkInfo.type == ConnectivityManager.TYPE_MOBILE
                }
            } else {
                val network: Network? = connectivityManager.activeNetwork
                if (network != null) {
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                    return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
                }
            }
        }
        return false
    }
}