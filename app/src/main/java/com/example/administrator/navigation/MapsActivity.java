package com.example.administrator.navigation;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double Lat = 18.7833;
    double Long = 98.9512;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);




        GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
        int status = gAPI.isGooglePlayServicesAvailable(MapsActivity.this);
        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        Button cmdClear =  findViewById(R.id.cmdClear);
        cmdClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng jj = new LatLng(Lat, Long);
        if (mMap != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);

            }
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
        mMap.addMarker(new MarkerOptions().position(jj).title("วัดอุโมง"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jj));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String strAddress = "Lat:" + String.valueOf(latLng.latitude) + "Long" + String.valueOf(latLng.longitude);
                MarkerOptions m = new MarkerOptions();
                m.position(latLng);
                m.title(strAddress);
                mMap.addMarker(m);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.mnuNone:
                if(mMap != null){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                    return true;
                }
            case R.id.mnuNormal:
                if(mMap != null){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    return true;
                }
            case R.id.mnuSatellite:
                if(mMap != null){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    return true;
                }
            case R.id.mnuTerrain:
                if(mMap != null){
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    return true;
                }
            case R.id.mnuHybrid:
                if(mMap != null){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    return true;
                }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
