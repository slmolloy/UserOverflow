package com.scottmolloy.useroverflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.scottmolloy.useroverflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UsersViewModel by viewModels()

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
                            replace<UserFragment>(R.id.fragment_panel, "details")
                        }
                    }
                    is UserViewState.ListView -> {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<UsersFragment>(R.id.fragment_panel, "list")
                            addToBackStack(null)
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