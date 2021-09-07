package com.scottmolloy.useroverflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.scottmolloy.useroverflow.R
import com.scottmolloy.useroverflow.data.User
import com.scottmolloy.useroverflow.databinding.FragmentUserBinding

/**
 * Fragment for displaying user details.
 */
class UserFragment : Fragment(R.layout.fragment_user) {

    private var binding: FragmentUserBinding? = null
    private val usersViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            usersViewModel.viewState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UserViewState.DetailsView -> { bindUser(state.user) }
                    else -> {
                        // This would be an error
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserBinding.inflate(inflater, container, false).let {
            binding = it
            it.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun bindUser(user: User) = binding?.run {
        activity?.title = user.display_name
        Glide.with(this@UserFragment)
            .load(user.profile_image)
            .into(userAvatar)
        userReputation.text = user.reputation.toString()

        /**
         * Some location strings contained html escaped characters and needed this for display.
         * This code to handle html escaping would probably be needed in a lot more places.
         */
        userLocation.text = HtmlCompat.fromHtml(user.location ?: "", FROM_HTML_MODE_LEGACY).toString()
    }
}