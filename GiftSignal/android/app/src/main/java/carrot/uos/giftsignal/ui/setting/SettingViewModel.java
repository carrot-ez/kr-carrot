package carrot.uos.giftsignal.ui.setting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/*
개인적 공부 내용
## ViewModel
클래스의 수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리하기 위함

View와 독립적이며, View가 필요로 하는 데이터만을 소유한다.
따라서 프레그먼트간에 의존적이지 않아도 된다.
 */


public class SettingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is setting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}