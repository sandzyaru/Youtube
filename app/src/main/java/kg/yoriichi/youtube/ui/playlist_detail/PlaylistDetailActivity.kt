package kg.yoriichi.youtube.ui.playlist_detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kg.yoriichi.youtube.core.base.BaseActivity
import kg.yoriichi.youtube.core.network.result.Status
import kg.yoriichi.youtube.data.remote.model.Item
import kg.yoriichi.youtube.databinding.ActivityPlaylistDetailBinding
import kg.yoriichi.youtube.ui.playlist.PlayListActivity
import kg.yoriichi.youtube.utils.NetworkStatus
import kg.yoriichi.youtube.utils.NetworkStatusHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding, PlaylistDetailViewModel>() {

    private var playlistId: String? = null

    override val viewModel: PlaylistDetailViewModel by viewModel()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

    override fun initView() {
        playlistId = intent.getStringExtra(PlayListActivity.idPut).toString()
        binding.playlistTitle.text = intent.getStringExtra(PlayListActivity.titlePut).toString()
        binding.playlistDescription.text = intent.getStringExtra(PlayListActivity.descriptionPut).toString()
    }

    override fun initListener() {
        super.initListener()
        binding.tvBack.setOnClickListener{
            onBackClick()
        }
    }


    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        initVM()
    }

    private fun initRecyclerView(playlistsList: List<Item>) {
        binding.videosRecyclerView.adapter = PlaylistDetailAdapter(playlistsList)
    }

    private fun initVM() {
        playlistId?.let { it ->
            viewModel.getPlaylistItems(it).observe(this) {
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
    }

    private fun checkConnection() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.detailsMain.visibility = View.VISIBLE
                binding.networkLayout.root.visibility = View.GONE
                initVM()
            } else {
                binding.detailsMain.visibility = View.GONE
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }
    }

    override fun checkInternet() {
        checkConnection()

        binding.networkLayout.btnTryAgain.setOnClickListener {
            checkConnection()
        }
    }

    private fun onBackClick(){
        Intent(this, PlayListActivity::class.java).apply {
            startActivity(this)
        }
    }



}