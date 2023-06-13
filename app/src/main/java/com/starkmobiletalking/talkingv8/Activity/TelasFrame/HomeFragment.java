package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.starkmobiletalking.talkingv8.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Button bt_Silabas,bt_Abc;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bt_Abc=view.findViewById(R.id.bt_Abc);
        bt_Silabas=view.findViewById(R.id.bt_Silabas);

       bt_Abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarFragAbc();
            }
        });
        bt_Silabas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarFragSilabas();
            }
        });


        return view;}

    private void chamarFragAbc() {
       AbcFragment fragment= new AbcFragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
       // Toast.makeText(getContext(), "asdad", Toast.LENGTH_SHORT).show();
    }
    private void chamarFragSilabas() {
        SilabasFragment fragment= new SilabasFragment();
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_Principal,fragment).commit();
        //Toast.makeText(getContext(), "asdasssssd", Toast.LENGTH_SHORT).show();
    }
}