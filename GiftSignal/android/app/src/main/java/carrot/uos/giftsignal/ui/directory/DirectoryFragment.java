package carrot.uos.giftsignal.ui.directory;

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

public class DirectoryFragment extends Fragment {

    private DirectoryViewModel directoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get ViewModel
        directoryViewModel =
                new ViewModelProvider(requireActivity()).get(DirectoryViewModel.class);

        // get View from layout xml file (inflate)
        View root = inflater.inflate(R.layout.fragment_list_view, container, false);

        // get value in ViewModel

        return root;
    }
}
