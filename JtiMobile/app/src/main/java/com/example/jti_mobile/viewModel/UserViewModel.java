package com.example.jti_mobile.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jti_mobile.model.User;
import com.example.jti_mobile.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<User> userLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userLiveData = new MutableLiveData<>();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public LiveData<User> getUser(String email) {
        User user = userRepository.getUser(email);
        userLiveData.setValue(user);
        return userLiveData;
    }
}
