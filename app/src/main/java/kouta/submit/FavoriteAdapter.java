package kouta.submit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import kouta.submit.data.IdArray;
import kouta.submit.data.ListArray;

public class FavoriteAdapter extends BaseAdapter {

    private FavoriteFragment favoriteFragment;

    public FavoriteAdapter(FavoriteFragment favoriteFragment) {
        super();
        this.favoriteFragment = favoriteFragment;
    }

    @Override
    public int getCount() {
        return IdArray.id.size();
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

        Log.d("check", String.valueOf(IdArray.id.size()));

        LayoutInflater mLayoutInflater = favoriteFragment.getLayoutInflater();
        convertView = mLayoutInflater.inflate(R.layout.favorite_item,null);
        TextView dateText = convertView.findViewById(R.id.text_item);

        dateText.setText((CharSequence) ListArray.getList().get((Integer) IdArray.getId().get(position)));


        return convertView;
    }
}
