package com.scottmolloy.useroverflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.scottmolloy.useroverflow.R
import com.scottmolloy.useroverflow.data.User
import com.scottmolloy.useroverflow.databinding.FragmentUsersBinding

/**
 * Fragment for displaying list of users.
 */
class UsersFragment : Fragment(R.layout.fragment_users) {

    private var binding: FragmentUsersBinding? = null
    private val usersViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            activity?.setTitle(R.string.list_title)

            usersViewModel.users.observe(viewLifecycleOwner) { users ->
                val adapter = UsersAdapter(users) { user -> itemClicked(user) }
                binding?.usersRecyclerView?.adapter = adapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUsersBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun itemClicked(user: User) {
        usersViewModel.showDetails(user)
    }
}