package app2.garrulousgirl.in.classattendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperViewHolder> {
    String[] papers;
    Context context;
    itemSelectorInterface clickListenerInterface;

    public PaperAdapter(String[] papers, Context context, itemSelectorInterface clickListenerInterface) {
        this.papers = papers;
        this.context = context;
        this.clickListenerInterface = clickListenerInterface;
    }

    @NonNull
    @Override
    public PaperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_papers, null);
        return new PaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaperViewHolder paperViewHolder, int i) {
        String p = papers[i];
        paperViewHolder.textView.setText(p);

    }

    @Override
    public int getItemCount() {
        return papers.length;
    }

    class PaperViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PaperViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.paper_textview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListenerInterface.onItemSelected(getAdapterPosition());
                }
            });

        }
    }
}