package com.example.pk.selfgeography.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.selfgeography.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsRVAdapter extends RecyclerView.Adapter<StatisticsRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> answers;

    public StatisticsRVAdapter(Context context, ArrayList<String> answers) {
        this.context = context;
        this.answers = answers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = layoutInflater.inflate(R.layout.as_statistics_rv_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.statistics.setText(answers.get(position));
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.as_statistics_rv_item_question)
        TextView statistics;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
