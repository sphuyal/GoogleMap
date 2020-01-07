package com.sandesh.googlemap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sandesh.googlemap.model.LatitudeLongitude;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        List<LatitudeLongitude> latlngs = new ArrayList<>();
        latlngs.add(new LatitudeLongitude(27.7061943,85.3300408,"Hamro College"));
        latlngs.add(new LatitudeLongitude(27.7057242,85.3279674,"Hamro Library College"));
        //LatLng sydney = new LatLng(27.7061943, 85.3300408,);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Hamro College"));

        CameraUpdate center,zoom;
        for (int i = 0; i < latlngs.size(); i++){
            center =
                    CameraUpdateFactory.newLatLng(new LatLng(latlngs.get(i).getLat(),
                            latlngs.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(16);
            mMap.addMarker(new MarkerOptions().position(new LatLng(latlngs.get(i).getLat(),
                           latlngs.get(i).getLon())).title(latlngs.get(i).getHamro()));

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);

        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
