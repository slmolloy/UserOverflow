package com.scottmolloy.useroverflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scottmolloy.useroverflow.databinding.ItemUsersBinding
import kotlin.math.roundToInt

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.itemUsersName.text = user.display_name
            binding.itemUsersReputation.text = user.reputation.roundToInt().toString()
            binding.itemUsersGoldScore.text = user.badge_counts.gold.toString()
            binding.itemUsersSilverScore.text = user.badge_counts.silver.toString()
            binding.itemUsersBronzeScore.text = user.badge_counts.bronze.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}
