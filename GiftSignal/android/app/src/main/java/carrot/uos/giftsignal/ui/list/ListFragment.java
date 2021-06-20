package carrot.uos.giftsignal.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import carrot.uos.giftsignal.R;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // get ViewModel
        listViewModel =
                new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        // get View from layout xml file (inflate)
        View root = inflater.inflate(R.layout.fragment_dir_view, container, false);

        // get value in ViewModel


        return root;
    }
}
