package com.duylong.animemangacollection.ui.manga;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MangaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MangaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}