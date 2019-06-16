package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;




public class NoteAdapter extends ListAdapter<Note,NoteAdapter.ViewHolder> {

    private onItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback DIFF_CALLBACK= new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return (oldItem.getId()==newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription()) && oldItem.getPriority()==newItem.getPriority());
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewdecsription;
        public TextView textViewpriority;

        public ViewHolder(View view) {
            super(view);
            textViewdecsription = view.findViewById(R.id.text_view_description);
            textViewpriority = view.findViewById(R.id.text_view_priority);
            textViewTitle = view.findViewById(R.id.text_view_title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });


        }
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewdecsription.setText(currentNote.getDescription());
        holder.textViewpriority.setText(String.valueOf(currentNote.getPriority()));
    }




    public Note getNoteAt(int position) {
        return getItem(position);
    }


    public interface onItemClickListener {
        public void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;

    }

}
