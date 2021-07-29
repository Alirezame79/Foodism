package com.method.foodism.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.method.foodism.Model.OrderList;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends ViewModel {

    public static ArrayList<OrderList> list = new ArrayList<>();
    public static MutableLiveData<List<OrderList>> mutableLiveData;

    public static LiveData<List<OrderList>> getInstance() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<OrderList>>();
        }
        return mutableLiveData;
    }

    public static ArrayList<OrderList> getList() {
        return list;
    }
    public static void setList(ArrayList<OrderList> list) {
        OrderViewModel.list = list;
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        mutableLiveData.setValue(list);
    }

    public static void clear(){
        list = new ArrayList<>();
    }
}
