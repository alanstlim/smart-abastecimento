package com.example.smartabastecimento.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.activity.menuprofile.MenuLogoutActivity;
import com.example.smartabastecimento.activity.menuprofile.MenuContributionActivity;
import com.example.smartabastecimento.activity.menuprofile.MenuPartnerActivity;
import com.example.smartabastecimento.adapter.AdapterProfile;
import com.example.smartabastecimento.helper.RecyclerItemClickListener;
import com.example.smartabastecimento.helper.UserFirebase;
import com.example.smartabastecimento.model.Profile;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private RecyclerView        recyclerView;
    private AdapterProfile      adapterProfile;
    private List<Profile>       listProfile = new ArrayList<>();
    private TextView            textNameUser,textEmailUser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView        = view.findViewById(R.id.recyclerViewProfile);
        textNameUser        = view.findViewById(R.id.textNameUser);
        textEmailUser       = view.findViewById(R.id.textEmailUser);

        // Configurar Adapter
        adapterProfile = new AdapterProfile(listProfile, getActivity());

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterProfile);

        // Evento de Click RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                switch (position) {
                                    case 0:
                                        Intent intentPartner = new Intent(getActivity(), MenuPartnerActivity.class);
                                        startActivity(intentPartner);
                                        break;
                                    case 1:
                                        Intent intentDonate = new Intent(getActivity(), MenuContributionActivity.class);
                                        startActivity(intentDonate);
                                        break;
                                    case 2:
                                        Intent intentLogout = new Intent(getActivity(), MenuLogoutActivity.class);
                                        startActivity(intentLogout);
                                        break;
                                    default:
                                        return;
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        //Recuperando dados do Usuario
        recoverUser();


        // Listagem de Menu Profile
        this.createProfileMenu();

        return view;
    }
    public void createProfileMenu(){

        Profile menuProfile = new Profile("Seja Parceiro", "Cadastre seu posto aqui", R.drawable.ic_partner);
        this.listProfile.add( menuProfile );

        menuProfile = new Profile("Contribuir / Contato", "Colabore com o nosso trabalho e/ou Entre em contato", R.drawable.ic_donation);
        this.listProfile.add( menuProfile );

        menuProfile = new Profile("Sair", "Deslogar da conta atual", R.drawable.ic_signout);
        this.listProfile.add( menuProfile );

    }

    public void recoverUser () {
            FirebaseUser currentUser = UserFirebase.getCurrentUser();
            textNameUser.setText(currentUser.getDisplayName());
            textEmailUser.setText(currentUser.getEmail());
    }
}