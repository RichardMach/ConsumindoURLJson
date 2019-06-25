package co.nf.axesoft.consumindourljson;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.nf.axesoft.consumindourljson.model.People;

public class PeopleSWAdapter extends BaseAdapter {
    private List<People> list =null;
    private Context context = null;


    public PeopleSWAdapter(Context c, List<People> peopleArrayList){
        this.context = c;
        this.list = peopleArrayList;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public People getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =null;
        if(view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            v = inflater.inflate(R.layout.item_listview_sw, null);
        }else   {
            v = view;
        }

        TextView txtId = v.findViewById(R.id.itemTxtPersonIdSw);
        TextView txtNome = v.findViewById(R.id.itemTxtNomeSw);




        People people = getItem(i);



        txtId.setText( String.valueOf(people.getId()) );
        txtNome.setText( people.getName() );

        return v;

    }
}
