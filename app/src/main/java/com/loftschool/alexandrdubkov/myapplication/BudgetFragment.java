package com.loftschool.alexandrdubkov.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.voice.AlwaysOnHotwordDetector;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BudgetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PRICE_COLOR = "price_color";
    public static final int REQUEST_CODE = 1001;
    private static final String TYPE = "type";
    private ItemsAdapter mItemsAdapter;
    private Api mApi;

    public BudgetFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BudgetFragment newInstance(FragmentType fragmentType) {
        BudgetFragment fragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt(PRICE_COLOR, fragmentType.getPriceColor());
        args.putString(TYPE, fragmentType.name());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = ((LoftApp) getActivity().getApplication()).getApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_budget, container, false);
        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);
        mItemsAdapter = new ItemsAdapter(getArguments().getInt(PRICE_COLOR));

        recyclerView.setAdapter(mItemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button openAddScreenButton = fragmentView.findViewById(R.id.open_add_screen);
        openAddScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), AddItemActivity.class), REQUEST_CODE);
            }
        });
        return fragmentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK )
        {
            final String token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("auth_token", "");
            final int price = Integer.parseInt(data.getStringExtra("price");
            final String name = data.getStringExtra("name");
            Call <Status> call = mApi.addItems(new AddItemRequest(price, name, getArguments().getString(TYPE)), token);
            ((retrofit2.Call) call).enqueue();


            mItemsAdapter.addItem(item);
        }
    }
}
