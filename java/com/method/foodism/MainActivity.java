package com.method.foodism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.method.foodism.API.Global;
import com.method.foodism.Adapter.FoodAdapter;
import com.method.foodism.Model.Food;
import com.method.foodism.Model.OrderList;
import com.method.foodism.ViewModel.OrderViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView calculate;
    TextView foodTxt;
    TextView potableTxt;
    TextView dessertTxt;
    ProgressBar progressBar;
    SwipeRefreshLayout refresh;
    RecyclerView foodRecyclerview;
    RecyclerView potableRecyclerview;
    RecyclerView dessertRecyclerview;

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;

    public String[] list = {"food", "potable", "dessert"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        getLists();
        refreshNecessity();
        moveOrderPage();

    }

    private void moveOrderPage() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                OrderCalculationFragment fragment = new OrderCalculationFragment();
                fragmentTransaction.
                        setCustomAnimations(android.R.anim.slide_in_left,
                                            0,
                                        0,
                                            android.R.anim.slide_out_right).add(R.id.fragment_container , fragment).addToBackStack(null).commit();
            }
        });
    }

    private void refreshNecessity() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLists();
                refresh.setRefreshing(false);
            }
        });
    }

    private void loadingSteps(int i) {
        if (i == 0){
            foodTxt.setVisibility(View.INVISIBLE);
            potableTxt.setVisibility(View.INVISIBLE);
            dessertTxt.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            foodRecyclerview.setVisibility(View.INVISIBLE);
            potableRecyclerview.setVisibility(View.INVISIBLE);
            dessertRecyclerview.setVisibility(View.INVISIBLE);
        }else if (i == 1){
            foodTxt.setVisibility(View.VISIBLE);
            potableTxt.setVisibility(View.VISIBLE);
            dessertTxt.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            foodRecyclerview.setVisibility(View.VISIBLE);
            potableRecyclerview.setVisibility(View.VISIBLE);
            dessertRecyclerview.setVisibility(View.VISIBLE);
        }
    }

    private void getLists() {
        OrderViewModel.clear();
        loadingSteps(0);

        for (int i=0; i < 3; i++){
            int finalI = i;
            Global.getMyAPI().getFoodsList(list[i]).enqueue(new Callback<ArrayList<Food>>() {
                @Override
                public void onResponse(Call<ArrayList<Food>> call, Response<ArrayList<Food>> response) {
                    if (response.body() != null){
                        loadingSteps(1);
                        initRecyclerview(finalI, response.body());
                    }else {
                    Toast.makeText(MainActivity.this, "There is a problem is " + list[finalI] + " part", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Food>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "There is a problem is " + list[finalI] + " part", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initRecyclerview(int i, ArrayList<Food> foods) {
        if (i == 0){
            recyclerView = findViewById(R.id.food_recyclerview);
        }else if (i == 1){
            recyclerView = findViewById(R.id.potable_recyclerview);
        }else if (i == 2){
            recyclerView = findViewById(R.id.dessert_recyclerview);
        }
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        adapter = new FoodAdapter(foods);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void findViews(){
        calculate = findViewById(R.id.calculate_btn);
        foodTxt = findViewById(R.id.food_board);
        potableTxt = findViewById(R.id.potable_board);
        dessertTxt = findViewById(R.id.dessert_board);
        progressBar = findViewById(R.id.load_progress_bar);
        refresh = findViewById(R.id.swipe_refresh);
        foodRecyclerview = findViewById(R.id.food_recyclerview);
        potableRecyclerview = findViewById(R.id.potable_recyclerview);
        dessertRecyclerview = findViewById(R.id.dessert_recyclerview);
    }
}