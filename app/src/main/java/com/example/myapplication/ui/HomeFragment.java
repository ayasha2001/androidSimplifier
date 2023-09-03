package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.dto.EquationTableDto;
import com.example.myapplication.repo.EquationRepo;
import com.example.myapplication.utils.DateUtils;
import com.example.myapplication.utils.roomDB.DbHelper;
import com.example.myapplication.view_model.EquationVM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends Fragment {
    private EquationVM viewModel;
    List<EquationTableDto> list = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view model and DbHelper initialization
        viewModel = new ViewModelProvider(requireActivity()).get(EquationVM.class);
        viewModel.setDBHelper(DbHelper.getInstance(requireContext()));

        Button submitBtn = view.findViewById(R.id.btn_submit);
        Button historyBtn = view.findViewById(R.id.btn_history);
        EditText editText = view.findViewById(R.id.tv_expression);

        //submit button listen to submit the expression and call api
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expressionsText = editText.getText().toString();
                String[] expressionsArray = expressionsText.split("\n");
                editText.setText("");

                if (list.size() > 0) {
                    list.clear();
                }

                for (String expression : expressionsArray) {
                    if (expression != null && !expression.isEmpty()) {
                        EquationRepo.getInstance().getResponseData(expression.trim())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<Integer>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        // Handle subscription
                                    }

                                    @Override
                                    public void onSuccess(Integer result) {

                                        Date d = new Date();
                                        String date = DateUtils.convertToDDMMYYYY(d);
                                        String data = expression + " = " + result;
                                        viewModel.insertDbData(new EquationTableDto(date, data));

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // Handle errors for this expression
                                        //show dialog box
                                    }
                                });
                    }
                }
            }
        });

        //to move to the history tab to see lists of past entered expression
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //navigation using nav graph
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_historyFragment);
            }
        });

    }

}