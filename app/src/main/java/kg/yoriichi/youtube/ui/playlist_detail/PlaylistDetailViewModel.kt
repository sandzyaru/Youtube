package kg.yoriichi.youtube.ui.playlist_detail

import androidx.lifecycle.LiveData
import kg.yoriichi.App
import kg.yoriichi.youtube.core.base.BaseViewModel
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.data.remote.model.PlaylistItems


class PlaylistDetailViewModel: BaseViewModel() {
    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> {
        return App().repository.getPlaylistItems(playlistId)
    }
}