package com.method.foodism.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.method.foodism.Model.OrderList;
import com.method.foodism.R;
import com.method.foodism.ViewModel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public List<OrderList> list;

    public OrderAdapter(List<OrderList> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_structure_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.oName.setText(list.get(position).getName());
        holder.oCost.setText(String.valueOf(list.get(position).getCost()));
        holder.oCounter.setText(String.valueOf(list.get(position).getCounter()));
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.oCounter.setText(String.valueOf(list.get(position).getCounter() + 1));
                ArrayList<OrderList> list = OrderViewModel.getList();
                for (OrderList x: list){
                    if (x.getName().equals(list.get(position).getName())){
                        x.setCounter(x.getCounter() + 1);
                        break;
                    }
                }
                OrderViewModel.setList(list);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getCounter() == 0){
                    return;
                }
                holder.oCounter.setText(String.valueOf(list.get(position).getCounter() - 1));
                ArrayList<OrderList> list = OrderViewModel.getList();
                for (OrderList x: list){
                    if (x.getName().equals(list.get(position).getName())){
                        x.setCounter(x.getCounter() - 1);
                        break;
                    }
                }
                OrderViewModel.setList(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView oName;
        public TextView oCost;
        public TextView oCounter;
        public ImageView plus;
        public ImageView minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            oName = itemView.findViewById(R.id.order_list_name_txt);
            oCost = itemView.findViewById(R.id.order_list_cost_txt);
            oCounter = itemView.findViewById(R.id.order_list_counter_txt);
            plus = itemView.findViewById(R.id.order_list_add_counter);
            minus = itemView.findViewById(R.id.order_list_reduce_counter);
        }
    }
}
