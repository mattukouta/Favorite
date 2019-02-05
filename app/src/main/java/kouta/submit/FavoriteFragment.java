package kouta.submit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FavoriteFragment extends Fragment {

    private ListView listView;
    private FavoriteAdapter favoriteAdapter;

    public FavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_favorite, container, false);
        super.onCreate(savedInstanceState);

        listView = layout.findViewById(R.id.favoriteList);
        favoriteAdapter = new FavoriteAdapter(FavoriteFragment.this);
        listView.setAdapter(favoriteAdapter);

        return layout;

    }
}
