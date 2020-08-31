package com.example.smartabastecimento.activity.menuprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.helper.Mask;
import com.example.smartabastecimento.model.Station;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MenuPartnerActivity extends AppCompatActivity {

    private String valueRadio, imgFranchise;
    private double latitude, longitude;
    private EditText editPriceEthanol, editPriceGasoline, editNameStation, editAdress, editCep, editNumber;
    private Button buttonRegisterStation;
    private ImageView imageBack;

    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    private RadioGroup radioGroupFranchise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_partner);


        editAdress              = findViewById(R.id.editAddress);
        editNameStation         = findViewById(R.id.editStationName);
        editPriceEthanol        = findViewById(R.id.editPriceEthanol);
        editPriceGasoline       = findViewById(R.id.editPriceGasoline);
        editCep                 = findViewById(R.id.editCep);
        editNumber              = findViewById(R.id.editNumber);
        buttonRegisterStation   = findViewById(R.id.buttonRegisterStation);
        radioGroupFranchise     = findViewById(R.id.radioGroupFranchise);
        imageBack               = findViewById(R.id.imageBack);

        // Mascaras
        editPriceGasoline.addTextChangedListener(Mask.addMask(editPriceGasoline, Mask.FORMAT_PRICE));
        editPriceEthanol.addTextChangedListener(Mask.addMask(editPriceEthanol, Mask.FORMAT_PRICE));
        editCep.addTextChangedListener(Mask.addMask(editCep, Mask.FORMAT_CEP));


        radioGroupFranchise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                valueRadio = button.getText().toString();
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonRegisterStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editPriceEthanol.getText().toString().trim().equals("") ||
                        editPriceGasoline.getText().toString().trim().equals("") ||
                        editNameStation.getText().toString().trim().equals("") ||
                        editCep.getText().toString().trim().equals("") ||
                        editNumber.getText().toString().trim().equals("") ||
                        editAdress.getText().toString().trim().equals("") ||
                        valueRadio == null) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos acima",Toast.LENGTH_LONG).show();
                } else {
                    imgFranchise = valueRadio;
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address>  addressList = geocoder.getFromLocationName(editAdress.getText() +" "+ editNumber.getText() +" "+ editCep.getText(),1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = addressList.get(0);
                            latitude = address.getLatitude();
                            longitude = address.getLongitude();
                            //metodo para salvar no bando de dados
                            DatabaseReference dbStations = dbReference.child("stations");
                            Station station = new Station();
                            station.setNameStation(editNameStation.getText().toString());
                            station.setCep((editCep.getText().toString()));
                            station.setNumber(Integer.parseInt(editNumber.getText().toString()));
                            station.setAddress(editAdress.getText().toString());
                            station.setImageFranchise(imgFranchise+".png");
                            station.setPriceEthanol(editPriceEthanol.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));
                            station.setPriceGasoline(editPriceGasoline.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));
                            station.setLatitude(latitude);
                            station.setLongitude(longitude);
                            dbStations.push().setValue(station);
                            Toast.makeText(MenuPartnerActivity.this, "Estabelecimento cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    } catch (
                            IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Endereço não encontrado, verifique as dados acima!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}