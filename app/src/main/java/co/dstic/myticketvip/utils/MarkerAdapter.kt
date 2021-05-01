package co.dstic.myticketvip.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import co.dstic.myticketvip.R
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class MarkerAdapter(context: Context) : InfoWindowAdapter {

    var mWindow: View = (context as Activity).layoutInflater.inflate(R.layout.item_marker, null)

    private fun rendowWindowText(marker: Marker, view: View){
        val markerName = view.findViewById<TextView>(R.id.markerName)
        val markerAddress = view.findViewById<TextView>(R.id.markerAddress)
        val markerSchedule = view.findViewById<TextView>(R.id.markerSchedule)
        val snippet = marker.snippet.split(",")
        markerName.text = marker.title
        markerAddress.text = snippet[0]
        markerSchedule.text = snippet[1]
    }

    override fun getInfoContents(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }
}