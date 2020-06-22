package com.michael.afrivac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupportPageActivity extends AppCompatActivity  {

    private ArrayList<SupportItem> SupportList;
    private RecyclerView supportRecyclerView;
    private RecyclerView.Adapter SupportAdapter;
    private RecyclerView.LayoutManager supportLayoutManager;

    /*EditText editText*/;
    SearchView searchView;
    TextView questions_tvTextView;
    TextView answers_tvTextView;
    ImageView spinnerImageView;
    LinearLayout Linear;
    View Divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_page);

        createSupportList();
        buildRecyclerView();




/*
        SupportAdapter = new ArrayAdapter<String>(this, android.R.layout.su,list);
        supportRecyclerView.setAdapter(SupportAdapter);
        searchView = findViewById(R.id.describe_edit_text);
        SupportList.setAdapter(SupportAdapter);

        editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    SupportAdapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                SupportAdapter.getFilter().filter(newText);
                return false;
            }
        });
*/

       /* editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });*/

        RelativeLayout contactusRelativeLayout = (RelativeLayout) findViewById(R.id.contact_us_relative_layout);
        contactusRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open contact us activity
                Intent intent = new Intent(v.getContext(), ContactUsActivity2.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // perform an action on where to send feedback. Mail intent is used here for sending feedback
        TextView feedbackTextView = (TextView) findViewById(R.id.feedback_tv);
        feedbackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sending mail intent to be handled by the best app available
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:balogunzzt@gmail.com")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello AFRIVAC!");
                intent.putExtra(Intent.EXTRA_TEXT, "FEEDBACK ON AFRIVAC:");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // opening terms and conditions activity
        TextView tncTextView = (TextView) findViewById(R.id.terms_and_condition_textView);
        tncTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open terms and conditions activity
                Intent intent = new Intent(v.getContext(), TermsAndConditionsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        spinnerImageView = (ImageView) findViewById(R.id.arrow_down_iv);
        answers_tvTextView =(TextView) findViewById(R.id.answer_tv);
        Divider = (View) findViewById(R.id.answers_view_line);

       final RecyclerView linearLayout = findViewById(R.id.question_and_answer_recycler_view);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getVisibility() == View.VISIBLE) {linearLayout.setVisibility(View.INVISIBLE);}
                else {  linearLayout.setVisibility(View.VISIBLE);  }
                // set spinner to visible
                spinnerImageView.setVisibility(View.VISIBLE);
                // set answers_tv to visible
                answers_tvTextView.setVisibility(View.VISIBLE);
                // set answers_view_line (divider) to visible
                Divider.setVisibility(View.VISIBLE);
            }
        });

        /*questions_tvTextView = (TextView) findViewById(R.id.questions_tv);
        questions_tvTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerImageView = findViewById(R.id.arrow_down_iv);
                spinnerImageView.setVisibility(View.VISIBLE);
                answers_tvTextView = findViewById(R.id.answer_tv);
                answers_tvTextView.setVisibility(View.VISIBLE);
                Divider = findViewById(R.id.answers_view_line);
                Divider.setVisibility(View.VISIBLE);
            }
        });

        spinnerImageView = (ImageView) findViewById(R.id.arrow_down_iv);
        spinnerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find answer text view and set to visible when arrow down is clicked
                answers_tvTextView = findViewById(R.id.answer_tv);
                answers_tvTextView.setVisibility(View.VISIBLE);
                Divider = findViewById(R.id.answers_view_line);
                Divider.setVisibility(View.VISIBLE);
            }
        });
*/
    }

    private void filter(String text) {
        ArrayList<SupportItem> filteredList = new ArrayList<>();
        for (SupportItem item : SupportList) {
            if (item.getQuestionstv().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        /*SupportAdapter.filter(filteredList);*/
    }

    private void createSupportList() {
        SupportList = new ArrayList<>();
        SupportList.add(new SupportItem("How many people can join a tour", "We keep our group sizes low so you can have the freedom to " +
                "\n move around and get involved with your surroundings," +
                "\n as well as more personal attention fro  our local " +
                "\n guides. This intimate size ensures that your group will " +
                "\n not crowd your experience. You can expect up to 15 " +
                "\n travellers on a trip but the average is 10. Check individual " +
                "\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you help arrange my travel visas", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you provide me with a list of the hotels", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Are airport transfers included", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you help arrange my travel visas", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "How is Afrivac able to offer such competitive services", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Is tipping included and if not, how much should I budget?", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem("How many people can join a tour", "We keep our group sizes low so you can have the freedom to " +
                "\n move around and get involved with your surroundings," +
                "\n as well as more personal attention fro  our local " +
                "\n guides. This intimate size ensures that your group will " +
                "\n not crowd your experience. You can expect up to 15 " +
                "\n travellers on a trip but the average is 10. Check individual " +
                "\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you help arrange my travel visas", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you provide me with a list of the hotels", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Are airport transfers included", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Can you help arrange my travel visas", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "How is Afrivac able to offer such competitive services", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem( "Is tipping included and if not, how much should I budget?", "We keep our group sizes low so you can have the freedom to \" +\n" +
                "                \"\\n move around and get involved with your surroundings,\" +\n" +
                "                \"\\n as well as more personal attention fro  our local \" +\n" +
                "                \"\\n guides. This intimate size ensures that your group will \" +\n" +
                "                \"\\n not crowd your experience. You can expect up to 15 \" +\n" +
                "                \"\\n travellers on a trip but the average is 10. Check individual \" +\n" +
                "                \"\\n trip pages for maximum group sizes "));
        SupportList.add(new SupportItem("How old is Afrivac?", "Afrivac " +
                "\n is " +
                "\n 2 " +
                "\n years " +
                "\n old"));

    }
    private void buildRecyclerView() {
        supportRecyclerView = findViewById(R.id.question_and_answer_recycler_view);
        supportRecyclerView.setHasFixedSize(true);
        supportLayoutManager = new LinearLayoutManager(this);
        SupportAdapter = new SupportAdapter(SupportList);
        supportRecyclerView.setLayoutManager(supportLayoutManager);
        supportRecyclerView.setAdapter(SupportAdapter);
    }
}
