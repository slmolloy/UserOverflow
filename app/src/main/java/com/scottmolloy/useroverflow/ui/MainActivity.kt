package com.scottmolloy.useroverflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.scottmolloy.useroverflow.*
import com.scottmolloy.useroverflow.databinding.ActivityMainBinding

/**
 * MainActivity contains the action bar and fragment container.
 * This activity updates the action bar title at the request of fragments.
 * This activity observers the UserViewModel to determine when navigation changes are made and
 * displays the appropriate fragment.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        lifecycleScope.launchWhenResumed {
            usersViewModel.viewState.observe(this@MainActivity) {
                when (it) {
                    is UserViewState.DetailsView -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<UserFragment>(R.id.fragment_panel)
                        }
                    }
                    is UserViewState.ListView -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<UsersFragment>(R.id.fragment_panel)
                        }
                    }
                }
            }
        }
    }

    override fun setTitle(titleId: Int) {
        binding.actionbar.setTitle(titleId)
    }

    override fun setTitle(title: CharSequence?) {
        binding.actionbar.title = title
    }

    override fun onBackPressed() {
        usersViewModel.viewState.value?.run {
            when (this) {
                is UserViewState.DetailsView -> { usersViewModel.showList() }
                is UserViewState.ListView -> { finish() }
            }
        }
    }
}