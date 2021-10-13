package com.example.restauracja;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PizzaMaterialFragment extends Fragment {

    public PizzaMaterialFragment() {
        // Required empty public constructor
    }

    public static PizzaMaterialFragment newInstance() {
        PizzaMaterialFragment fragment = new PizzaMaterialFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
            R.layout.fragment_pizza_material,
            container,
            false
        );
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for(int i = 0;i < pizzaNames.length;i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        int[] pizzaImages = new int[Pizza.pizzas.length];
        for(int i = 0;i < pizzaImages.length;i++) {
            pizzaImages[i] = Pizza.pizzas[i].getImageId();
        }

        CaptionedImagesAdapter ciAdapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        recyclerView.setAdapter(ciAdapter);
        GridLayoutManager layout = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layout);

        ciAdapter.setListener(new CaptionedImagesAdapter.Listener(){
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_NO, position);
                getActivity().startActivity(intent);
            }
        });

        return recyclerView;
    }
}