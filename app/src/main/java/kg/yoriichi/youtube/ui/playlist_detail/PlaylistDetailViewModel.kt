package kg.yoriichi.youtube.ui.playlist_detail

import androidx.lifecycle.LiveData
import kg.yoriichi.youtube.core.base.BaseViewModel
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.data.remote.model.PlaylistItems
import kg.yoriichi.youtube.repository.Repository


class PlaylistDetailViewModel(private val repository: Repository): BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> {
        return repository.getPlaylistItems(playlistId)
    }
}