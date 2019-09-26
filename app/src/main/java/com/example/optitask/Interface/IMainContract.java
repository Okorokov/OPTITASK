package com.example.optitask.Interface;

public interface IMainContract {
    interface IMainActivity {
        interface View {

        }
        interface Presenter {
            boolean onAuthResult(String uid);
        }

        interface Repository {
            //на перспективу
        }
    }


}
