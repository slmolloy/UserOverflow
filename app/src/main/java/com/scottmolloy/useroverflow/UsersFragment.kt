package com.scottmolloy.useroverflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.scottmolloy.useroverflow.databinding.FragmentUsersBinding

class UsersFragment : Fragment(R.layout.fragment_users) {

    private var binding: FragmentUsersBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            activity?.setTitle(R.string.list_title)

            val adapter = UsersAdapter(UsersDataSource().getUsers())
            binding?.usersRecyclerView?.adapter = adapter
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
}