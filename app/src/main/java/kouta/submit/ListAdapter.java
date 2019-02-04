package kouta.submit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private String[] list={"りんご","みかん","いちご","なし","ぶどう","メロン","スイカ","さくらんぼ","グレープフルーツ","もも","バナナ"};
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private static class ViewHolder {
        public TextView dateText;
    }

    public ListAdapter(Context context){
        mContext=context;
        mLayoutInflater= LayoutInflater.from(context);
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

        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.dateText.setText(list[position]);

        return convertView;
    }
}
