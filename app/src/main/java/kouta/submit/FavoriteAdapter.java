package kouta.submit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class FavoriteAdapter extends BaseAdapter {

    private String[] list={"りんご","みかん","いちご","なし","ぶどう","メロン","スイカ","さくらんぼ","グレープフルーツ","もも","バナナ"};
    private FavoriteFragment favoriteFragment;

    public FavoriteAdapter(FavoriteFragment favoriteFragment) {
        super();
        this.favoriteFragment = favoriteFragment;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mLayoutInflater = favoriteFragment.getLayoutInflater();
        convertView = mLayoutInflater.inflate(R.layout.favorite_item,null);
        TextView dateText = convertView.findViewById(R.id.text_item);
        //↓お気に入りボタンの実装
        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(convertView.getContext());
        final Gson gson = new Gson();
        final String jsonText = pre.getString("Submit/FavoriteList", "[]");
        Log.d("checkjson",jsonText);
        ArrayList arrayList = gson.fromJson(jsonText, new TypeToken<ArrayList<Integer>>(){}.getType());


        if (arrayList.indexOf(position) != -1){
            dateText.setText(list[position]);
        }
//        dateText.setText((CharSequence) arrayList.get(position));


        return convertView;
    }
}
