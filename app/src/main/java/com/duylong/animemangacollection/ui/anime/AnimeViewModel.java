package com.duylong.animemangacollection.ui.anime;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnimeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnimeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}