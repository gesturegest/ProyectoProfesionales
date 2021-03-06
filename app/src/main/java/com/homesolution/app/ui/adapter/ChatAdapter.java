package com.homesolution.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.homesolution.app.domain.Chat;
import com.squareup.picasso.Picasso;
import com.youtube.sorcjc.proyectoprofesionales.R;
import com.homesolution.app.ui.activity.ProfileActivity;
import com.homesolution.app.ui.activity.TalkActivity;
import com.homesolution.app.ui.custom.CircleTransform;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private ArrayList<Chat> chats;
    private Context context;

    public ChatAdapter(Context context) {
        this.context = context;
        this.chats = new ArrayList<>();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Chat chat = chats.get(position);

        holder.setImportant(chat.isImportant());
        holder.setName(chat.getName());
        holder.setImage(chat.getUrlPhoto());
        holder.setDescription(chat.getCatstr());
        holder.setDate(chat.getActivity());
        if (chat.isEsPrestador()) {
            // Send full data
            holder.setChatClick(chat.getUid(), chat.getPid(), chat.getName(), chat.getCatstr(), chat.getPrestador().getTel());

            // The profile activity is only for workers
            holder.setImageClick(chat.getPid());
        } // Just send basic data
        else holder.setChatClick(chat.getUid(), "", chat.getName(), "", "");
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public void setAll(ArrayList<Chat> chats) {
        if (chats == null)
            throw new NullPointerException("The results cannot be null.");

        this.chats = chats;
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private TextView tvImportant;
        private TextView tvName;
        private TextView tvDescription;
        private TextView tvDate;

        private LinearLayout contact_info;

        public MessageViewHolder(View itemView) {
            super(itemView);

            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvImportant = (TextView) itemView.findViewById(R.id.tvImportant);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);

            contact_info = (LinearLayout) itemView.findViewById(R.id.contact_info);
        }

        public void setImportant(boolean important) {
            if (important)
                tvImportant.setVisibility(View.VISIBLE);
            else
                tvImportant.setVisibility(View.GONE);
        }

        public void setName(String name){
            tvName.setText(name);
        }

        public void setImage(String urlImage) {
            Picasso.with(context)
                    .load(urlImage)
                    .placeholder(R.drawable.avatar_default)
                    .transform(new CircleTransform())
                    .into(ivPhoto);
        }

        public void setImageClick(final String pid) {
            // Set event click for the profile image
            ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ProfileActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("pid", pid);
                    i.putExtras(mBundle);
                    context.startActivity(i);
                }
            });
        }

        public void setDescription(String description) {
            if (description==null || description.isEmpty())
                tvDescription.setVisibility(View.GONE);
            else
                tvDescription.setText(description);
        }

        public void setDate(String date) {
            tvDate.setText(date);
        }

        public void setChatClick(final String uid, final String pid, final String name, final String catstr, final String tel) {
            // Set event click for open the chat
            ChatClickListener chatClickListener = new ChatClickListener(uid, pid, name, catstr, tel);

            contact_info.setOnClickListener(chatClickListener);
        }

        class ChatClickListener implements View.OnClickListener {
            private final String uid;
            private final String pid;
            private final String catstr;
            private final String name;
            private final String tel;

            public ChatClickListener(String uid, String pid, String name, String catstr, String tel) {
                this.uid = uid;
                this.pid = pid;
                this.catstr = catstr;
                this.name = name;
                this.tel = tel;
            }

            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), TalkActivity.class);
                Bundle b = new Bundle();
                b.putString("uid", uid);
                i.putExtras(b);
                view.getContext().startActivity(i);
            }
        }

    }

}
