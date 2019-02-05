package kouta.submit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kouta.submit.data.IdArray;
import kouta.submit.data.ListArray;

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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(getContext(),view);
                MenuInflater menuInflater = popup.getMenuInflater();
                menuInflater.inflate(R.menu.popup_menu,popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.rename:
                                Toast.makeText(getContext(), String.valueOf(position)+ "名前変えます", Toast.LENGTH_SHORT).show();
                                Rename();
                                break;
                            case R.id.delete:
                                Toast.makeText(getContext(), String.valueOf(position)+ "削除します", Toast.LENGTH_SHORT).show();
                                Delete(position);
                                break;
                        }

                        //fragmentの初期化
                        ListFragment fragment = new ListFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();
                        return false;
                    }
                });

                return false;
            }
        });

        return layout;

    }

    //popmenuの項目名変更部分
    public void Rename() {
    }

    //popmenuの削除部分
    public void Delete(int position) {
        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getContext());
        final Gson gson = new Gson();
        final String jsonList = pre.getString("Submit/List", "[\"りんご\",\"みかん\",\"いちご\",\"なし\",\"ぶどう\",\"メロン\",\"スイカ\",\"さくらんぼ\",\"グレープフルーツ\",\"もも\",\"バナナ\"]");
        ArrayList arrayList = gson.fromJson(jsonList, new TypeToken<ArrayList<String>>(){}.getType());
        arrayList.remove(position);
        Log.d("checklist", String.valueOf(arrayList));
        ListArray.list = arrayList;
        String str1 = gson.toJson(arrayList);
        pre.edit().putString("Submit/List",str1).apply();

        final String jsonId = pre.getString("Submit/Id", "[]");
        ArrayList arrayId = gson.fromJson(jsonId, new TypeToken<ArrayList<String>>(){}.getType());
        arrayId.remove(String.valueOf(position));
        Log.d("checklist", String.valueOf(arrayId));
        IdArray.id = arrayId;
        String str2 = gson.toJson(arrayId);
        pre.edit().putString("Submit/Id",str2).apply();
    }
}
