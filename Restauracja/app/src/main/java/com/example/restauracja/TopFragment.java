package com.example.restauracja;

import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopFragment extends Fragment {

    public TopFragment() {
        // Required empty public constructor
    }

    public static android.app.Fragment newInstance() {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.rvPizzas);

        String[] pizzasName = new String[2];
        for (int i = 0; i < pizzasName.length; i++) {
            pizzasName[i] = Pizza.pizzas[i].getName();
        }

        int[] pizzasImg = new int[2];
        for (int i = 0; i < pizzasImg.length; i++) {
            pizzasImg[i] = Pizza.pizzas[i].getImageId();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzasName, pizzasImg);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_NO, position);
                getActivity().startActivity(intent);
            }
        });
        return layout;
    }
}