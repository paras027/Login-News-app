package com.codingstuff.customtablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class recadapter extends RecyclerView.Adapter<recadapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    List<newsmodel> E1;

    public recadapter(Context ctx, ArrayList<newsmodel> photosses) {
        this.inflater=LayoutInflater.from(ctx);
        this.E1=photosses;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.sahise,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title=E1.get(position).getTitle();
        holder.tit.setText(title);

        String desc=E1.get(position).getDesc();
        holder.desc.setText(desc);

        String name=E1.get(position).getSource();
        holder.name.setText(name);

        Date dateObject = new Date((E1.get(position).getTime()));
        try {
            String formattedDate = formatDate(dateObject);
            holder.time.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String lin2= E1.get(position).getImage();
        Glide.with(context)
                .load(lin2)
                .centerCrop()
                .into(holder.img);
    }

    private String formatDate(Date dateObject) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse(String.valueOf(dateFormat));//You will get date object relative to server/client timezone wherever it is parsed
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return  dateStr;
    }

    @Override
    public int getItemCount() {
        return E1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tit,desc,name,time;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tit=itemView.findViewById(R.id.textView3);
            desc=itemView.findViewById(R.id.textView4);
            name=itemView.findViewById(R.id.source);
            time=itemView.findViewById(R.id.time);
            img=itemView.findViewById(R.id.imageView);
        }
    }
}
