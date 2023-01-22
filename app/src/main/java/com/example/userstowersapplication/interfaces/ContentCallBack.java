package com.example.userstowersapplication.interfaces;

import java.util.List;

public interface ContentCallBack<Model> {
    void onSuccess(List<Model> list);
    void  onFailure(String message);
}
