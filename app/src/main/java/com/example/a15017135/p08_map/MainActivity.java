package com.example.a15017135.p08_map;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng singapore = new LatLng(1.382287, 103.796140);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        11));

                UiSettings uicompass = map.getUiSettings();
                uicompass.setCompassEnabled(true);

                UiSettings uizoom = map.getUiSettings();
                uizoom.setZoomControlsEnabled(true);
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                final LatLng north = new LatLng(1.44224, 103.785733);
                final Marker northmarker = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm  Tel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                final LatLng central = new LatLng(1.337668, 103.805066);
                Marker centralmarker = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("HQ-Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 Operating hours: 11am-8pm   Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final LatLng east = new LatLng(1.351912, 103.944798);
                Marker eastmarker = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("HQ-East")
                        .snippet("Block 555, Tampines Ave 3, 287788 Operating hours: 9am-5pm Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLng(north));
                            map.animateCamera(CameraUpdateFactory.zoomIn());
                            map.animateCamera(CameraUpdateFactory.zoomTo(15),1000,null);
                        }
                    }

                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLng(central));
                            map.animateCamera(CameraUpdateFactory.zoomIn());
                            map.animateCamera(CameraUpdateFactory.zoomTo(15),1000,null);
                        }
                    }

                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLng(east));
                            map.animateCamera(CameraUpdateFactory.zoomIn());
                            map.animateCamera(CameraUpdateFactory.zoomTo(15),1000,null);
                        }
                    }

                });
            }

        });

    }
}

