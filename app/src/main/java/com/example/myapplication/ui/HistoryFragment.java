package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.dto.EquationTableDto;
import com.example.myapplication.utils.ListUtils;
import com.example.myapplication.view_model.EquationVM;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private EquationVM viewModel;
    private HistoryAdapter historyAdapter;
    private List<EquationTableDto> list = new ArrayList<>();

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adapter initialization
        historyAdapter = new HistoryAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting adapter to recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(historyAdapter);

        viewModel = new ViewModelProvider(requireActivity()).get(EquationVM.class);

        //calling data for RoomDb to show the list
        viewModel.getDataFromDb();

        setObservers();
    }

    private void setObservers(){
        viewModel.getAllDbData().observe(getViewLifecycleOwner(),details->{
            if(details==null){
               return;
            }else{
                if(list.size()>0){
                    list.clear();
                }else{
                    list.addAll(details);
                    //filtering data date wise to show in the multi view type recyclerview
                    List<String> organizedList = ListUtils.filterAndOrganizeByDate(list);

                    //setting the list in the adapter
                    historyAdapter.setList(organizedList);
                }
            }
        });
    }
}