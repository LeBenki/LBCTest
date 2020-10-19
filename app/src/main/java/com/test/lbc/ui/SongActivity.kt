package com.test.lbc.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.lbc.LBCApplication
import com.test.lbc.R
import com.test.lbc.repository.SongSorting
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SongActivity : AppCompatActivity() {
    private lateinit var viewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SongAdapter()
        songList.adapter = adapter
        songList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        initViewModel()

        viewModel.fetchSongs()

        viewModel.allSongs.observe(this, {
            lifecycleScope.launch {
                it.collectLatest {
                    adapter.submitData(it)
                }
            }
        })
    }


    private fun initViewModel() {
        val component = (applicationContext as LBCApplication).getComponent()

        val factory = component.viewModelFactory
        viewModel = ViewModelProvider(this, factory!!).get(SongViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.album -> {
                viewModel.setSortType(SongSorting.BY_ALBUM)
                return true
            }
            R.id.title -> {
                viewModel.setSortType(SongSorting.BY_TITLE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}