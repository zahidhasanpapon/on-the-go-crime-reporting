package com.example.firebase01;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CrimeStatusViewHolder extends AppCompatActivity implements View.OnCreateContextMenuListener {
    private RecyclerView crimeList;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crimes);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Crimes");

        crimeList = (RecyclerView) findViewById(R.id.crimeList);
        crimeList.setHasFixedSize(true);
        crimeList.setLayoutManager(new LinearLayoutManager(this));

        
    }


    public void onCreateContextMenu (ContextMenu menu,
                                              View v,
                                              ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("Select The Action");

        menu.add(0, v.getId(),0,"Accept");
        menu.add(0, v.getId(),1,"Decline");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Crime> options=
                new FirebaseRecyclerOptions.Builder<Crime>()
                        .setQuery(databaseReference,Crime.class)
                        .setLifecycleOwner(this)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Crime, showCrimes.CrimeViewHolder>(options) {
            @Override
            public showCrimes.CrimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.crime_row, parent, false);

                return new showCrimes.CrimeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(showCrimes.CrimeViewHolder holder, int position, Crime model) {
                // Bind the Chat object to the ChatHolder
                // ...
                holder.setArea(model.getArea());
                holder.setDesc(model.getDesc());
                holder.setImage(model.getImage());
            }
        };

        crimeList.setAdapter(adapter);
    }

    public static class CrimeViewHolder extends RecyclerView.ViewHolder{

        View view;

        public CrimeViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }
        public void setArea (String area){
            TextView post_area = (TextView) view.findViewById(R.id.crimeArea);
            post_area.setText(area);
        }

        public void setDesc(String desc){
            TextView post_desc = (TextView) view.findViewById(R.id.crimeDesc);
            post_desc.setText(desc);
        }
        public void setImage(String image){

            ImageView post_image = (ImageView) view.findViewById(R.id.crimeImg);
            Picasso.get().load(image).into(post_image);


        }
    }

}
