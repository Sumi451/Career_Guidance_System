package com.example.careerguidancesystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homescreen extends AppCompatActivity {
    private RecyclerView recommendationsRecyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoModel> videoList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button user_feedback_button=findViewById(R.id.user_feedback_button);
        Button social_button=findViewById(R.id.social_button);
        Button resume_button=findViewById(R.id.resume_button);
        Button progress_button=findViewById(R.id.progress_button);
        ImageView profile=findViewById(R.id.profile_icon);
        ImageView profile_click_dp=findViewById(R.id.profile_icon);
        TextView profile_name=findViewById(R.id.profile_name);
        CardView profile_cardview=findViewById(R.id.profile_cardview);
        Button log_out_button=findViewById(R.id.log_out_button);
        Button chatbot=findViewById(R.id.chatbot);
        Button form_button= findViewById(R.id.form_button);

        recommendationsRecyclerView = findViewById(R.id.recommendations_recycler_view);
        videoList = new ArrayList<>();
        // Example video data (replace with your own data)
        videoList.add(new VideoModel("https://img.youtube.com/vi/iPDh1QeTIaY/default.jpg", "https://www.youtube.com/watch?v=iPDh1QeTIaY"));
        videoList.add(new VideoModel("https://img.youtube.com/vi/jyvdq4woCYQ/hqdefault.jpg", "https://www.youtube.com/watch?v=jyvdq4woCYQ"));
        videoList.add(new VideoModel("https://img.youtube.com/vi/Iq_r7IcNmUk/hqdefault.jpg", "https://www.youtube.com/watch?v=Iq_r7IcNmUk"));
        videoList.add(new VideoModel("https://img.youtube.com/vi/iPDh1QeTIaY/default.jpg", "https://www.youtube.com/watch?v=iPDh1QeTIaY"));
        videoList.add(new VideoModel("https://img.youtube.com/vi/jyvdq4woCYQ/hqdefault.jpg", "https://www.youtube.com/watch?v=jyvdq4woCYQ"));
        videoList.add(new VideoModel("https://img.youtube.com/vi/Iq_r7IcNmUk/hqdefault.jpg", "https://www.youtube.com/watch?v=Iq_r7IcNmUk"));
        videoAdapter = new VideoAdapter(videoList, this);
        recommendationsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recommendationsRecyclerView.setAdapter(videoAdapter);

        CourseReminderNotification.scheduleDailyReminder(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photourl = user.getPhotoUrl();
            String uid = user.getUid();
            Glide.with(this).load(user.getPhotoUrl()).into(profile);
            Glide.with(this).load(user.getPhotoUrl()).into(profile_click_dp);
            profile_name.setText(name);

            // ...
        }
        profile.setOnClickListener(view ->
        {
            if(profile_cardview.getVisibility()==CardView.GONE)
            {
                profile_cardview.setVisibility(CardView.VISIBLE);
            }
            else
            {
                profile_cardview.setVisibility(CardView.GONE);
            }


        });
        log_out_button.setOnClickListener(view ->
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(Homescreen.this,Login.class);
            startActivity(intent);
            finish();
        });

        chatbot.setOnClickListener(view ->
        {
           Intent intent=new Intent(Homescreen.this,Chatbot.class);
            startActivity(intent);
        });
        form_button.setOnClickListener(view ->
                {
                    Intent intent=new Intent(Homescreen.this,info_form.class);
                    startActivity(intent);
                }
                );
        progress_button.setOnClickListener(view ->
        {
            Intent intent=new Intent(Homescreen.this,Progress.class);
            startActivity(intent);
        });
        resume_button.setOnClickListener(view ->
        {
            String url = "https://resume.io/resume-templates";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);

        });
        user_feedback_button.setOnClickListener(view ->
        {
            Intent intent=new Intent(Homescreen.this,Feedback.class);
            startActivity(intent);
        });
        social_button.setOnClickListener(view ->
        {
            Intent intent=new Intent(Homescreen.this,Social.class);
            startActivity(intent);
        });

        }



    }

