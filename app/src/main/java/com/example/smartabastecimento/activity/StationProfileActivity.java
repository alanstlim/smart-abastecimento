package com.example.smartabastecimento.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.smartabastecimento.R;
import com.example.smartabastecimento.activity.menuprofile.MenuPartnerActivity;
import com.example.smartabastecimento.helper.Permissions;
import com.example.smartabastecimento.model.Station;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class StationProfileActivity extends FragmentActivity implements OnMapReadyCallback {

    private TextView textNameStation, textPriceEthanol, textPriceGasoline, textAddress;
    private ImageView imageFranchise, imageBack;
    private Station recipientStation;
    private StorageReference storageReference;

    private GoogleMap mMap;
    private String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_profile);
        textNameStation = findViewById(R.id.textNameStation);
        textPriceEthanol = findViewById(R.id.textPriceEthanol);
        textPriceGasoline = findViewById(R.id.textPriceGasoline);
        textAddress = findViewById(R.id.textAddress);
        imageFranchise = findViewById(R.id.imageFranchise);
        imageBack = findViewById(R.id.imageBack);

        //Recuperar dados do posto destinatario
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recipientStation = (Station) bundle.getSerializable("profileStation");
            storageReference = FirebaseStorage.getInstance().getReference().child("franchise/" + recipientStation.getImageFranchise() + "");
            textNameStation.setText(recipientStation.getNameStation());
            textAddress.setText(recipientStation.getAddress() + ", " + recipientStation.getNumber() + " - " + recipientStation.getCep());
            textPriceGasoline.setText("R$: " + recipientStation.getPriceGasoline());
            textPriceEthanol.setText("R$: " + recipientStation.getPriceEthanol());
            Glide.with(StationProfileActivity.this)
                    .load(storageReference)
                    .into(imageFranchise);
        }
        //Validar Permissões
        Permissions.validatePermission(permissions, this, 1);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        String stationFranchise = recipientStation.getImageFranchise().replaceAll(".png" ,"");
        LatLng stationLocation = new LatLng(
                recipientStation.getLatitude(),
                recipientStation.getLongitude()
        );
                switch (stationFranchise) {
                    case "Shell":
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_shell)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
                    case "Ale":
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_ale)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
                    case "Texaco":
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_texaco)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
                    case "Petrobras":
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_petrobras)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
                    case "Ipiranga":
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_ipiranga)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
                    default:
                        mMap.addMarker(new MarkerOptions().position(stationLocation).title(recipientStation.getNameStation())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_outro)));
                        //Zoom da camera 2.0 a 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 18));
                        break;
        }

     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissionResult : grantResults) {
            if (permissionResult == PackageManager.PERMISSION_DENIED){
                alertValidatePermission();
            }
        }
    }

    private void alertValidatePermission(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
