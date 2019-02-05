package kouta.submit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ListFragment extends Fragment {

    private ListView listView;
    private ListAdapter listAdapter;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list, container, false);
        super.onCreate(savedInstanceState);

        listView = layout.findViewById(R.id.listView);
        listAdapter = new ListAdapter(ListFragment.this);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                PopupMenu popup = new PopupMenu(getContext(),view);
                MenuInflater menuInflater = popup.getMenuInflater();
                menuInflater.inflate(R.menu.popup_menu,popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.rename:
                                Toast.makeText(getContext(), "名前変えます", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(getContext(), "削除します", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

                return false;
            }
        });

        return layout;

    }
}
