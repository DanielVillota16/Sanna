package com.sanna_app.sanna.delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanna_app.sanna.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pedidoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pedidoFragment extends Fragment {



    public pedidoFragment() {
        // Required empty public constructor
    }


    public static pedidoFragment newInstance() {
        pedidoFragment fragment = new pedidoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedido, container, false);


        // Inflate the layout for this fragment
        return root;
    }
}