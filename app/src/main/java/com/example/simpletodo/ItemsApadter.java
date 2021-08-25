package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into the row in the recycler view
public class ItemsApadter extends RecyclerView.Adapter<ItemsApadter.ViewHolder>
{
    public interface OnLongClickListener
    {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsApadter(List<String> items, OnLongClickListener longClickListener)
    {
        this.items =items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap in a view holder and return it
        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //grab item at the position
        String item = items.get(position);
        //Bind the item to the specific view holder
        holder.bind(item);
    }
    //Tells the  RV how many items are in the list
    @Override
    public int getItemCount()
    {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        //update the view inside of the view holder w/ data
        public void bind(String item)
        {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    //notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
