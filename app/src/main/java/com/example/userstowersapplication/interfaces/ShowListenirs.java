package com.example.userstowersapplication.interfaces;


import com.example.userstowersapplication.modils.ShowCategorie;

import java.util.List;

public interface ShowListenirs {
    void onSuccess(List<ShowCategorie> list);
    void onFailure(String message);
}
