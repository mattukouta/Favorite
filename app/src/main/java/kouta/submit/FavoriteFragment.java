package kouta.submit;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_favorite, container, false);
        super.onCreate(savedInstanceState);

        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(layout.getContext());
        String position = pre.getString("position", "[]");

        TextView text = layout.findViewById(R.id.hoge);
        text.setText(position);

        return layout;
    }
}
