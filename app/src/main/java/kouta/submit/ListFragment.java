package kouta.submit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListFragment extends Fragment {

    private ListView listView;
    private ListAdapter listAdapter;
    private Context context;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list, container, false);
        super.onCreate(savedInstanceState);

        listView = layout.findViewById(R.id.listView);
        this.context = getContext();
        listAdapter = new ListAdapter(context);
        listView.setAdapter(listAdapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                Intent intent = new Intent(this, )
//                SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getContext());
//                pre.edit().putString("position",String.valueOf(position)).apply();
//            }
//        });

        return layout;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
