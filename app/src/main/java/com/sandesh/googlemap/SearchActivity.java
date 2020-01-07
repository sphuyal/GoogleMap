package com.sandesh.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sandesh.googlemap.model.LatitudeLongitude;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity  implements OnMapReadyCallback {
    private GoogleMap mMap;
    private AutoCompleteTextView etCity;
    private Button btnSearch;
    private List<LatitudeLongitude> latitudeLongitudeList;
    Marker hamro;
    CameraUpdate center,zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        etCity = findViewById(R.id.etCity);
        btnSearch = findViewById(R.id.btnSearch);

        fillArrayListSetAdapeter();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCity.getText().toString()))
                {
                    etCity.setError("Please enter a place name");
                    return;
                }
                int postion = SearchArrayList(etCity.getText().toString());
                if (postion > -1)
                {
                    loadMap(postion);
                }
                else
                {
                    Toast.makeText(SearchActivity.this, "Location not found by name : " + etCity.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fillArrayListSetAdapeter(){
        latitudeLongitudeList = new ArrayList<>();
        latitudeLongitudeList.add(new LatitudeLongitude(27.7134481,85.3241922,"Naagpokhari"));
        latitudeLongitudeList.add(new LatitudeLongitude(27.7289889,85.304085,"Bikram Cafe"));
        latitudeLongitudeList.add(new LatitudeLongitude(27.7149518,85.2904165,"Swayambhunath Maha Chaitya"));

        String[] data = new String[latitudeLongitudeList.size()];

        for (int i = 0; i < data.length; i++){
            data[i] = latitudeLongitudeList.get(i).getHamro();
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                SearchActivity.this,
                android.R.layout.simple_list_item_1,
                data
        );
        etCity.setAdapter(adapter);
        etCity.setThreshold(1);
    }

    public int SearchArrayList(String name){
        for (int i = 0; i < latitudeLongitudeList.size(); i++){
            if (latitudeLongitudeList.get(i).getHamro().contains(name)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        center = CameraUpdateFactory.newLatLng(new LatLng(27.7172453,85.3239605));
        zoom = CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void loadMap(int position){
        if (hamro!= null)
        {
            hamro.remove();
        }
        double latitude = latitudeLongitudeList.get(position).getLat();
        double longitude = latitudeLongitudeList.get(position).getLon();
        String  ham = latitudeLongitudeList.get(position).getHamro();
        center = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
        zoom = CameraUpdateFactory.zoomTo(17);
        hamro = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,
                longitude)).title(ham));
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

}
