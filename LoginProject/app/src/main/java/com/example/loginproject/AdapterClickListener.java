package com.example.loginproject;

import com.example.loginproject.database.model.Localidade;

public interface AdapterClickListener {
    void onDeleteItem(int id);
    void onAddItem(int id);
    void onItemClicked(Object object);
}
