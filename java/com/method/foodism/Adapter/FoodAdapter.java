package com.method.foodism.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.method.foodism.Model.Food;
import com.method.foodism.Model.OrderList;
import com.method.foodism.R;
import com.method.foodism.ViewModel.OrderViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.method.foodism.API.Global.getContext;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    public List<Food> foodList;

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_structure_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        holder.name.setText(foodList.get(position).getName());
        holder.cost.setText(String.valueOf(foodList.get(position).getCost()));
        Picasso.get().load(foodList.get(position).imageUrl).resize(250, 250).centerCrop()
                .transform(new CropCircleTransformation()).into(holder.image);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderViewModel viewModel = new OrderViewModel();
                ArrayList<OrderList> orderListArrayList = OrderViewModel.getList();
                OrderList orderList = new OrderList();
                orderList.name = foodList.get(position).getName();
                orderList.cost = foodList.get(position).getCost();
                orderList.counter = 1;
                orderListArrayList.add(orderList);
                OrderViewModel.setList(orderListArrayList);
                Log.d("TAF", OrderViewModel.getList().toString());
                holder.add.setEnabled(false);
                holder.add.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView cost;
        public ImageView image;
        public TextView add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_name_txt);
            cost = itemView.findViewById(R.id.food_cost_txt);
            image = itemView.findViewById(R.id.food_image);
            add = itemView.findViewById(R.id.add_btn);
        }
    }
}
