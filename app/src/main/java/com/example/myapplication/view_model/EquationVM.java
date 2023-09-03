package com.example.myapplication.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.dto.EquationTableDto;
import com.example.myapplication.repo.EquationRepo;
import com.example.myapplication.utils.roomDB.DbHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EquationVM extends ViewModel {

    private MutableLiveData<Integer> resDataLive = new MutableLiveData<>();
    private MutableLiveData<List<EquationTableDto>> allDbData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private EquationRepo equationRepo = EquationRepo.getInstance();

    public void setDBHelper(DbHelper dbHelper){
        equationRepo.setDbHelper(dbHelper);
    }

    public void getDataFromDb() {
        compositeDisposable.add(equationRepo.
                getAllDbData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<EquationTableDto>>() {
                    @Override
                    public void onSuccess(List<EquationTableDto> data) {
                        allDbData.postValue(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    public void insertDbData(EquationTableDto equationTableDto){
        compositeDisposable.add(equationRepo.
                setDbData(equationTableDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public MutableLiveData<Integer> getResDataLive() {
        return resDataLive;
    }

    public MutableLiveData<List<EquationTableDto>> getAllDbData() {
        return allDbData;
    }

    public void setAllDbData(MutableLiveData<List<EquationTableDto>> allDbData) {
        this.allDbData = allDbData;
    }
}
