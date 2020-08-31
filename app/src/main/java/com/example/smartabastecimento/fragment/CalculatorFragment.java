package com.example.smartabastecimento.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.helper.Mask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {
    private double          priceGasoline, priceEthanol, gasolineKmCity, gasolineKmRoad, ethanolKmCity, ethanolKmRoad, yieldRoad, yieldCity, resultPrice;
    private EditText        editPriceGasoline, editPriceEthanol, editGasolineKmCity, editGasolineKmRoad, editEthanolKmCity, editEthanolKmRoad;
    private Button          buttonCalculate;
    private TextView        textResult, textResultCity, textResultRoad, textCity, textRoad;
    private Switch          switchSimple;
    private LinearLayout    linearLayoutRoad, linearLayoutCity, linearLayoutResult;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        editPriceGasoline = view.findViewById(R.id.editPriceGasoline);
        editPriceEthanol = view.findViewById(R.id.editPriceEthanol);
        editEthanolKmCity = view.findViewById(R.id.editEthanolKmCity);
        editGasolineKmCity = view.findViewById(R.id.editGasolineKmCity);
        editEthanolKmRoad = view.findViewById(R.id.editEthanolKmRoad);
        editGasolineKmRoad = view.findViewById(R.id.editGasolineKmRoad);
        buttonCalculate = view.findViewById(R.id.buttonCalculate);
        textResult = view.findViewById(R.id.textResult);
        textResultCity = view.findViewById(R.id.textResultCity);
        textResultRoad = view.findViewById(R.id.textResultRoad);
        textCity = view.findViewById(R.id.textCity);
        textRoad = view.findViewById(R.id.textRoad);
        switchSimple = view.findViewById(R.id.switchSimple);
        linearLayoutCity = view.findViewById(R.id.linearLayoutCity);
        linearLayoutRoad = view.findViewById(R.id.linearLayoutRoad);
        linearLayoutResult = view.findViewById(R.id.linearLayoutResult);

        // Mascaras
        editPriceGasoline.addTextChangedListener(Mask.addMask(editPriceGasoline, Mask.FORMAT_PRICE));
        editPriceEthanol.addTextChangedListener(Mask.addMask(editPriceEthanol, Mask.FORMAT_PRICE));

        // Ativar o Listener do Switch
        switchListener();

        //Evento de Click do botão calcular
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (switchSimple.isChecked()) {
                    if (editPriceEthanol.getText().toString().trim().equals("") ||
                            editPriceGasoline.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Preencha todos os campos acima",Toast.LENGTH_LONG).show();
                    } else {
                        // Convertendo os valores do EditText para Double
                        priceGasoline   = Double.parseDouble(editPriceGasoline.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));
                        priceEthanol    = Double.parseDouble(editPriceEthanol.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));

                        //Exibir Layout do resultado
                        linearLayoutResult.setVisibility(View.VISIBLE);
                        textResult.setVisibility(View.VISIBLE);
                        textResultCity.setVisibility(View.GONE);
                        textResultRoad.setVisibility(View.GONE);
                        textCity.setVisibility(View.GONE);
                        textRoad.setVisibility(View.GONE);

                        // Fazendo o calculo do rendimento simples
                        resultPrice = priceEthanol / priceGasoline;

                        if (resultPrice < 0.7) {
                            resultPrice = priceEthanol / priceGasoline;
                            textResult.setText("Abasteça com ETANOL");
                        } else {
                            textResult.setText("Abasteça com GASOLINA");
                        }
                    }
                } else {

                    if (editPriceEthanol.getText().toString().trim().equals("") ||
                            editPriceGasoline.getText().toString().trim().equals("") ||
                            editEthanolKmCity.getText().toString().trim().equals("") ||
                            editEthanolKmRoad.getText().toString().trim().equals("") ||
                            editGasolineKmCity.getText().toString().trim().equals("") ||
                            editGasolineKmRoad.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(getActivity(), "Preencha todos os campos acima",Toast.LENGTH_LONG).show();

                    } else {

                        // Convertendo os valores do EditText para Double
                        priceGasoline = Double.parseDouble(editPriceGasoline.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));
                        priceEthanol = Double.parseDouble(editPriceEthanol.getText().toString().replaceAll("[,]", ".").replaceAll("[R$]", "").replaceAll("[ ]", "").replaceAll("[:]", ""));
                        ethanolKmCity = Double.parseDouble(editEthanolKmCity.getText().toString());
                        gasolineKmCity = Double.parseDouble(editGasolineKmCity.getText().toString());
                        ethanolKmRoad = Double.parseDouble(editEthanolKmRoad.getText().toString());
                        gasolineKmRoad = Double.parseDouble(editGasolineKmRoad.getText().toString());

                        //Exibir Layout do resultado
                        linearLayoutResult.setVisibility(View.VISIBLE);
                        textResult.setVisibility(View.GONE);
                        textResultCity.setVisibility(View.VISIBLE);
                        textResultRoad.setVisibility(View.VISIBLE);
                        textCity.setVisibility(View.VISIBLE);
                        textRoad.setVisibility(View.VISIBLE);

                        // Fazendo o calculo do rendimento avançado
                        resultPrice = priceEthanol / priceGasoline;
                        yieldCity = ethanolKmCity / gasolineKmCity;
                        yieldRoad = ethanolKmRoad / gasolineKmRoad;
                        
                        if (resultPrice < yieldCity) {
                            textResultCity.setText("Abasteça com ETANOL na Cidade");
                        } else {
                            textResultCity.setText("Abasteça com GASOLINA na Cidade");
                        }

                        if (resultPrice < yieldRoad) {
                            textResultRoad.setText("Abasteça com ETANOL Estrada");
                        } else {
                            textResultRoad.setText("Abasteça com GASOLINA na Estrada");
                        }
                    }
                }
            }


        });


        return view;
    }

    // Configuração do Switch
    public void switchListener() {

        switchSimple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ( isChecked ){
                    switchSimple.setText(R.string.calculate_simple);
                    linearLayoutCity.setVisibility(View.GONE);
                    linearLayoutRoad.setVisibility(View.GONE);
                    linearLayoutResult.setVisibility(View.GONE);
                } else {
                    switchSimple.setText(R.string.calculate_complete);
                    linearLayoutCity.setVisibility(View.VISIBLE);
                    linearLayoutRoad.setVisibility(View.VISIBLE);
                    linearLayoutResult.setVisibility(View.GONE);
                }
            }
        });

    }
}