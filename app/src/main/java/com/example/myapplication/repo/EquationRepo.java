package com.example.myapplication.repo;


import com.example.myapplication.utils.networking.RetrofitService;
import com.example.myapplication.utils.roomDB.RoomRepository;

import io.reactivex.Single;

public class EquationRepo extends RoomRepository {
    private static EquationRepo equationRepository;

    private EquationRepo() {
    }

    public static EquationRepo getInstance() {
        if (equationRepository == null) {
            equationRepository = new EquationRepo();
        }
        return equationRepository;
    }

    public Single<Integer> getResponseData(String expression) {
        return RetrofitService.getApiInterface().getResponse(expression);
    }
}
