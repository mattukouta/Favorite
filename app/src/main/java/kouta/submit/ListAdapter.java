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

public class ListAdapter extends BaseAdapter {

    private String[] list={"りんご","みかん","いちご","なし","ぶどう","メロン","スイカ","さくらんぼ","グレープフルーツ","もも","バナナ"};
    private ListFragment listFragment;

    public ListAdapter(ListFragment listFragment) {
        super();
        this.listFragment = listFragment;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mLayoutInflater = listFragment.getLayoutInflater();
        convertView = mLayoutInflater.inflate(R.layout.list_item,null);
        TextView dateText = convertView.findViewById(R.id.item_text);
        //↓お気に入りボタンの実装

        final ImageButton favButton =convertView.findViewById(R.id.favButton);

        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(convertView.getContext());
        final Gson gson = new Gson();
        final String jsonText = pre.getString("Submit/FavoriteList", "[]");
        Log.d("checkjson",jsonText);
        ArrayList arrayList = gson.fromJson(jsonText, new TypeToken<ArrayList<Integer>>(){}.getType());
        final int id = Integer.parseInt(String.valueOf(position));


        Log.d("checklog", String.valueOf(arrayList));
        if (arrayList.indexOf(id) != -1){
            favButton.setImageResource(R.drawable.ic_favorite_black_48dp);
        }

        final View finalConvertView = convertView;
        favButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String jsonTextButton = pre.getString("Submit/FavoriteList", "[]");
                final ArrayList favoriteList = gson.fromJson(jsonTextButton, new TypeToken<ArrayList<Integer>>(){}.getType());
                Log.d("checkfavorite", String.valueOf(favoriteList));
                Animation animation = AnimationUtils.loadAnimation(finalConvertView.getContext(), R.anim.touch);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        String text;
                        if (favoriteList.indexOf(id) == -1) {
                            favButton.setImageResource(R.drawable.ic_favorite_black_48dp);
                            favoriteList.add(id);
                            text = gson.toJson(favoriteList);
                            pre.edit().putString("Submit/FavoriteList",text).apply();
                        }else if (favoriteList.indexOf(id) != -1) {
                            favButton.setImageResource(R.drawable.ic_favorite_border_black_48dp);
                            favoriteList.removeAll(Collections.singleton(id));
                            text = gson.toJson(favoriteList);
                            pre.edit().putString("Submit/FavoriteList",text).apply();
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                v.startAnimation(animation);
            }
        });
        //↑

        dateText.setText(list[position]);

        return convertView;
    }
}
