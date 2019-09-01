package com.example.android.appstreet_app.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.appstreet_app.R;
import com.example.android.appstreet_app.api.model.User;
import com.example.android.appstreet_app.databinding.ListItemBinding;
import com.example.android.appstreet_app.ui.callback.IItemClick;
import com.example.android.appstreet_app.utils.ImageLoader;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private List<User> users;
    private IItemClick<User> listener;
    private ImageLoader imageLoader;

    public RepoAdapter(List<User> users) {
        this.users = users;
        imageLoader = ImageLoader.getInstance();
    }

    public void addListener(IItemClick itemClick) {
        this.listener = itemClick;
    }

    public void removeListener() {
        listener = null;
    }

    public void addRepoList(List<User> repoList) {
        if (!this.users.isEmpty()) {
            this.users.clear();
        }
        this.users.addAll(repoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.list_item, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final User user = users.get(position);
        viewHolder.bind(user);
        imageLoader.displayImage(user.getAvatar(), viewHolder.binding.itemUserImage);
        viewHolder.binding.setItemClickListener(click -> listener.onItemClick(user));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User user) {
            binding.setUser(user);
            binding.executePendingBindings();
        }
    }
}

