package com.example.mygithubapp_sbm2.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mygithubapp_sbm2.R
import com.example.mygithubapp_sbm2.adapter.SectionPagerAdapter
import com.example.mygithubapp_sbm2.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USER)
        val bundle = Bundle()
        bundle.putString(EXTRA_USER, username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setDetailUsers(username)
        }
        viewModel.getDetailUsers().observe(this, {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(imgItemPhoto)
                    tvItemName.text = it.name
                    tvItemUsername.text = it.login
                    tvItemCompany.text = it.company
                    tvItemLocation.text = it.location
                    tvItemFollowing.text = "${it.following} Following"
                    tvItemFollowers.text = "${it.followers} Followers"


                }
            }
        })

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.menu2 -> {
                sentToast("Menu About")
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun sentToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

}

