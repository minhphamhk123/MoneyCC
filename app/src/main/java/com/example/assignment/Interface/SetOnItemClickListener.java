package com.example.assignment.Interface;

import android.view.View;

public interface SetOnItemClickListener {
    void onItemClick(View itemView, int position);
    void onEdit(int position);
    void onDelete(int position);
}
