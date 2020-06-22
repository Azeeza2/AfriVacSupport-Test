package com.michael.afrivac;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.SupportViewHolder> implements Filterable {
    private ArrayList<SupportItem> SupportList;
    private ArrayList<SupportItem> SupportListFiltered;

    public SupportAdapter(ArrayList<SupportItem> supportList) {
        SupportList = supportList;
    }


    @NonNull
    @Override
    public SupportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_page_recycler_list_item,
                parent, false);
        SupportViewHolder evh = new SupportViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull SupportViewHolder holder, int position) {


        SupportItem currentItem = SupportList.get(position);
        holder.mQuestionstv.setText(currentItem.getQuestionstv());
        holder.mAnswerstv.setText(currentItem.getAnswerstv());

        ImageView spinnerImageView = ImageView.<ImageView>findViewById(R.id.arrow_down_iv);
       /*TextView answers_tvTextView =TextView.findViewById(R.id.answer_tv);
        View Divider = View.findViewById(R.id.answers_view_line);*/
         RecyclerView recyclerLayout = null;
        recyclerLayout = recyclerLayout.findViewById(R.id.question_and_answer_recycler_view);
        final RecyclerView finalLinearLayout = recyclerLayout;
        holder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalLinearLayout.getVisibility() == View.VISIBLE) {
                    finalLinearLayout.setVisibility(View.INVISIBLE);}
                else {  finalLinearLayout.setVisibility(View.VISIBLE);  }
                // set spinner to visible
                spinnerImageView.setVisibility(View.VISIBLE);
               /* // set answers_tv to visible
                answers_tvTextView.setVisibility(View.VISIBLE);
                // set answers_view_line (divider) to visible
                Divider.setVisibility(View.VISIBLE);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return SupportList.size();
        // should be on filtered list
    }

    public void filterList(ArrayList<SupportItem> filteredList) {
        SupportList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        SupportListFiltered = SupportList;
                    } else {
                        ArrayList<SupportItem> filteredList = new ArrayList<>();
                       /* for (SupportItem : SupportList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            *//*if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                                filteredList.add(row);
                            }*//*
                        }*/

                        SupportListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = SupportListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    SupportListFiltered = (ArrayList<SupportItem>) filterResults.values;

                    // refresh the list with filtered data
                    notifyDataSetChanged();
                }
            };

        }

    public static class SupportViewHolder extends RecyclerView.ViewHolder {
        public TextView mQuestionstv;
        public TextView mAnswerstv;
        public View recyclerLayout;

        public SupportViewHolder(View itemView) {
            super(itemView);
            mQuestionstv = itemView.findViewById(R.id.questions_tv);
            mAnswerstv = itemView.findViewById(R.id.answer_tv);
        }

    }
}
