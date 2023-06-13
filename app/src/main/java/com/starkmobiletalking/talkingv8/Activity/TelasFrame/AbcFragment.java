package com.starkmobiletalking.talkingv8.Activity.TelasFrame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.starkmobiletalking.talkingv8.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AbcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbcFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private Context mContext;
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button bt_A;

    public AbcFragment() {
        mContext=getContext();
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AbcFragment newInstance(String param1, String param2) {
        AbcFragment fragment = new AbcFragment();
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
        View view = inflater.inflate(R.layout.fragment_abc, container, false);
        bt_A= view.findViewById(R.id.bt_A);
        bt_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bt_A.getText().equals("A")){
                  //Toast.makeText(getContext(), "|"+bt_A.getText()+"|77", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), TelaPrincipalAlfabertoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else{
                    //Toast.makeText(getContext(), "|"+bt_A.getText()+"|88", Toast.LENGTH_SHORT).show();
                }

            }
        });

    return  view;}







}