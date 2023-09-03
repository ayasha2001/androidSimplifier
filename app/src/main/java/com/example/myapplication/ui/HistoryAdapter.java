package com.example.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemDateBinding;
import com.example.myapplication.databinding.ItemEquationBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> list = new ArrayList<>();

    public void setList(List<String> equationTableDtoList) {
        try {
            this.list.clear();
            this.list.addAll(equationTableDtoList);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
            return new ViewHolderDate(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equation, parent, false);
            return new ViewHolderDetails(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            ViewHolderDate holderDate = (ViewHolderDate) holder;
            holderDate.setItemDate(position);
        } else {
            ViewHolderDetails holderDetails = (ViewHolderDetails) holder;
            holderDetails.setItemEquationInfo(position);
        }
    }


    //check if item is of date type or expression and assign view type to it
    @Override
    public int getItemViewType(int position) {
        if (list.get(position).contains("/")) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderDetails extends RecyclerView.ViewHolder {

        public ItemEquationBinding itemEquationBinding;

        public ViewHolderDetails(@NonNull View itemView) {
            super(itemView);
            itemEquationBinding = ItemEquationBinding.bind(itemView);
        }

        //setting list item if it is expression
        public void setItemEquationInfo(int position) {
            try {
                if (list == null || list.isEmpty()) {
                    return;
                }
                itemEquationBinding.textDate.setText(list.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class ViewHolderDate extends RecyclerView.ViewHolder {
        private final ItemDateBinding itemDateBinding;
        public ViewHolderDate(@NonNull View itemView) {
            super(itemView);
            itemDateBinding = ItemDateBinding.bind(itemView);
        }

        //setting list item if it is a date
        public void setItemDate(int position) {
            try {
                if (list == null || list.isEmpty()) {
                    return;
                }
                itemDateBinding.textDate.setText(list.get(position));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
