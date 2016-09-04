package scratchlab.com.ph.medicinereminder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

import scratchlab.com.ph.medicinereminder.extras.Information;
import scratchlab.com.ph.medicinereminder.R;

/**
 * Created by airap on 11/06/2016.
 */
public class ApolAdapter extends RecyclerView.Adapter<ApolAdapter.MyViewHolder>{

    List<Information> data = Collections.emptyList();
    private LayoutInflater inflater;

    //constructor for layout inflater
    public ApolAdapter(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    //3 methods(onCreateViewHolder, onBindViewHolder and getItemCount is automatically generated when we extends to RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current=data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //created class to extend to RecyclerView.ViewHolder plus constructor
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

    }
}