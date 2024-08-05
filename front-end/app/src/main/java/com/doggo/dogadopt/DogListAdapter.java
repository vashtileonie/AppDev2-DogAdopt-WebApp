package com.doggo.dogadopt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.doggo.dogadopt.activity.updateActivity;
import com.doggo.dogadopt.activity.viewActivity;
import com.doggo.dogadopt.model.Dog;

import org.jetbrains.annotations.NotNull;

public class DogListAdapter extends BaseAdapter {

    Context context;
    private Dog[] dogs;

    private String userType;
    private Long userID;

    public DogListAdapter(Context context, Dog[] dogs, String userType, Long userID) {
        this.context = context;
        this.dogs = dogs;
        this.userType = userType;
        this.userID = userID;
    }

    @Override
    public int getCount() {
        return dogs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        final View result;

        if (convertView == null){

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.dNameTxt = (TextView) convertView.findViewById(R.id.dNameTxt);
            viewHolder.dBreedTxt = (TextView) convertView.findViewById(R.id.dBreedTxt);
            viewHolder.dStatusTxt = (TextView) convertView.findViewById(R.id.dStatusTxt);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.dPicture);
            viewHolder.function = (Button) convertView.findViewById(R.id.item_button);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.dNameTxt.setText(dogs[position].getName().replace("\"", ""));
        viewHolder.dBreedTxt.setText(dogs[position].getBreed().replace("\"", ""));
        viewHolder.dStatusTxt.setText(dogs[position].getStatus().replace("\"", ""));
        viewHolder.picture.setImageBitmap(
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeByteArray(dogs[position].getPhoto(), 0, dogs[position].getPhoto().length),
                        130,
                        130,
                        false
                )
        );
        if (userType.equals("ADMIN")){
            viewHolder.function.setText("EDIT");
        } else if (userType.equals("USER")) {
            viewHolder.function.setText("Get to know me!");
        }

        viewHolder.function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (userType.equals("ADMIN")){
                    i = new Intent(context.getApplicationContext(), updateActivity.class);
                } else {
                    i = new Intent(context.getApplicationContext(), viewActivity.class);
                }
                i.putExtra("dogID",dogs[position].getId());
                i.putExtra("userID",userID);
                context.startActivity(i);
            }
        });

        return convertView;
    }

    private class ViewHolder {

        ImageView picture;
        TextView dNameTxt;
        TextView dBreedTxt;
        TextView dStatusTxt;
        Button function;

    }


}
