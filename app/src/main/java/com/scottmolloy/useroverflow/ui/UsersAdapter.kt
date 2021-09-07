package com.scottmolloy.useroverflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scottmolloy.useroverflow.data.User
import com.scottmolloy.useroverflow.databinding.ItemUsersBinding

/**
 * Adapter for displaying list of users in a RecyclerView.
 *
 * @property users list to be displayed by adapter
 * @property onClick callback so click events can be handled elsewhere
 */
class UsersAdapter(private val users: List<User>, private val onClick: (User) -> Unit)
    : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemUsersBinding, val onClick: (User) -> Unit)
        : RecyclerView.ViewHolder(binding.root) {
        var currentUser: User? = null
        init {
            // Moved click listener setup out of bind to improve performance.
            itemView.setOnClickListener {
                currentUser?.let { user -> onClick(user) }
            }
        }

        fun bind(user: User) {
            currentUser = user
            binding.itemUsersName.text = user.display_name
            binding.itemUsersReputation.text = user.reputation.toString()
            binding.itemUsersGoldScore.text = user.badge_counts.gold.toString()
            binding.itemUsersSilverScore.text = user.badge_counts.silver.toString()
            binding.itemUsersBronzeScore.text = user.badge_counts.bronze.toString()
            Glide.with(this.itemView.context)
                .load(user.profile_image)
                .into(binding.itemUsersAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}
