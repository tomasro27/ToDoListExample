package com.rodrigueztomas.todolistexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<String> objects;
    //private ArrayAdapter<String> adapter;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        objects = new ArrayList<String>();
        objects.add("Test 1");

        adapter = new CustomAdapter(this, R.layout.list_item, objects);

        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_item) {
            //TODO: add item to listview
            objects.add("Item " + (objects.size() + 1));
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class CustomAdapter extends ArrayAdapter<String>{
        Context context;
        int resourceId;
        ArrayList<String> items;

        public CustomAdapter(Context context, int resourceId, ArrayList<String> items)
        {
            super(context, resourceId, items);
            this.context = context;
            this.resourceId = resourceId;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View v = convertView;

            if ( v == null )
            {
                LayoutInflater vi = getLayoutInflater();
                v = vi.inflate(resourceId, null);
            }

            String str = objects.get(position);

            TextView title = (TextView) v.findViewById(R.id.title);
            ImageButton button = (ImageButton) v.findViewById(R.id.button);

            button.setTag(position);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (Integer)v.getTag();
                    objects.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });

            title.setText(str);

            return v;
        }
    }



}
