package com.doggo.dogadopt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doggo.dogadopt.activity.resolvereqActivity;
import com.doggo.dogadopt.activity.startupActivity;
import com.doggo.dogadopt.activity.updateActivity;
import com.doggo.dogadopt.activity.viewActivity;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Request;

import org.jetbrains.annotations.NotNull;

public class RequestListAdapter extends BaseAdapter {

    Context context;
    private Request[] requests;
    private Dog[] dogs;


    public RequestListAdapter(Context context, Request[] requests, Dog[] dogs) {
        this.context = context;
        this.requests = requests;
        this.dogs = dogs;
    }

    @Override
    public int getCount() {
        return requests.length;
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
            convertView = inflater.inflate(R.layout.list_request_layout, parent, false);
            viewHolder.dNameTxt = (TextView) convertView.findViewById(R.id.dNameTxt);
            viewHolder.rdogIDTxt = (TextView) convertView.findViewById(R.id.rdogIDTxt);
            viewHolder.reqStatusTxt = (TextView) convertView.findViewById(R.id.reqStatusTxt);
            viewHolder.resolveRequest = (Button) convertView.findViewById(R.id.resolveRequest);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        for (Dog dog : dogs){

            String name ="";
            if (dog.getId().equals(requests[position].getDogId())){
                name = "For "+dog.getName().replace("\"", "");
                viewHolder.dNameTxt.setText(name);
            }

        };

        viewHolder.rdogIDTxt.setText("Dog ID: " +Integer.toString(Math.toIntExact(requests[position].getDogId())));
        viewHolder.reqStatusTxt.setText(requests[position].getReqStatus().replace("\"", ""));

        viewHolder.resolveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), resolvereqActivity.class);
                context.startActivity(i);
            }
        });

        return convertView;
    }

    private class ViewHolder {

        TextView dNameTxt;
        TextView rdogIDTxt;
        TextView reqStatusTxt;
        Button resolveRequest;

    }


}
