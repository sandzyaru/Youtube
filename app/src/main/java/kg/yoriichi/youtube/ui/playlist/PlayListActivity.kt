package kg.yoriichi.youtube.ui.playlist


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider

import kg.yoriichi.youtube.core.base.BaseActivity
import kg.yoriichi.youtube.core.network.result.Status
import kg.yoriichi.youtube.data.remote.model.Item
import kg.yoriichi.youtube.databinding.ActivityPlayListBinding
import kg.yoriichi.youtube.ui.playlist_detail.PlaylistDetailActivity
import kg.yoriichi.youtube.utils.NetworkStatus
import kg.yoriichi.youtube.utils.NetworkStatusHelper

class PlayListActivity : BaseActivity<ActivityPlayListBinding, PlaylistViewModel>() {

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlayListBinding {
        return ActivityPlayListBinding.inflate(inflater)
    }
    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        initVM()
    }

    private fun initRecyclerView(playlistsList: List<Item>) {
        binding.recycler.adapter = PlaylistAdapter(playlistsList, this::onItemClick)
    }

    private fun onItemClick(channelId: String, title: String, description: String) {
        Intent(this, PlaylistDetailActivity::class.java).apply {
            putExtra(idPut, channelId)
            putExtra(titlePut, title)
            putExtra(descriptionPut, description)
            startActivity(this)
        }
    }

    private fun initVM() {
        viewModel.getPlaylists().observe(this) {
            when(it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        viewModel.loading.postValue(false)
                        initRecyclerView(it.data.items)
                    }
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
            }
        }
    }

    companion object {
        const val idPut = "id"
        const val titlePut = "title"
        const val descriptionPut = "description"
    }

    override fun checkInternet() {
        checkConnection()

        binding.networkLayout.btnTryAgain.setOnClickListener {
            checkConnection()
        }
    }

    private fun checkConnection() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.recycler.visibility = View.VISIBLE
                binding.networkLayout.root.visibility = View.GONE
                initVM()
            } else {
                binding.recycler.visibility = View.GONE
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }
    }


}