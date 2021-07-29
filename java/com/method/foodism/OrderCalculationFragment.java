package com.method.foodism;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.method.foodism.Adapter.OrderAdapter;
import com.method.foodism.Model.OrderList;
import com.method.foodism.ViewModel.OrderViewModel;

public class OrderCalculationFragment extends Fragment {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;

    TextView totalCost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_calculation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalCost = view.findViewById(R.id.total_cost);

        initOrderList(view);
        updateCost();

    }

    private void updateCost() {
        OrderViewModel model = new ViewModelProvider(this).get(OrderViewModel.class);
        model.getInstance().observe(getViewLifecycleOwner(), users -> {
            int cost = 0;
            for (OrderList x: OrderViewModel.getList()){
                int thisOrder = 0;
                thisOrder += x.getCost();
                thisOrder *= x.getCounter();
                cost += thisOrder;
            }
            totalCost.setText(String.valueOf(cost));
            Log.d("TAF", "total cost" + cost);
        });
    }

    private void initOrderList(View view) {
        recyclerView = view.findViewById(R.id.order_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new OrderAdapter(OrderViewModel.getList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}