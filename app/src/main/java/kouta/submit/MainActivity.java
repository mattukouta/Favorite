package kouta.submit;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kouta.submit.data.IdArray;
import kouta.submit.data.ListArray;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Gson gson = new Gson();
        final String jsonList = pre.getString("Submit/List", "[\"りんご\",\"みかん\",\"いちご\",\"なし\",\"ぶどう\",\"メロン\",\"スイカ\",\"さくらんぼ\",\"グレープフルーツ\",\"もも\",\"バナナ\"]");
        Log.d("checkjson",jsonList);
        ArrayList arrayList = gson.fromJson(jsonList, new TypeToken<ArrayList<String>>(){}.getType());
        ListArray.list = arrayList;

        final String jsonId = pre.getString("Submit/Id", "[]");
        ArrayList arrayId = gson.fromJson(jsonId, new TypeToken<ArrayList<Integer>>(){}.getType());
        IdArray.id = arrayId;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadFragment(new ListFragment());

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_list:
                fragment = new ListFragment();

                break;

            case R.id.navigation_fab:
                fragment = new FavoriteFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
