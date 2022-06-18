package kg.yoriichi.youtube.ui.playlist

import androidx.lifecycle.LiveData
import kg.yoriichi.App
import kg.yoriichi.youtube.core.base.BaseViewModel
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.data.remote.model.Playlists


class PlaylistViewModel: BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return App().repository.getPlaylists()
    }
}