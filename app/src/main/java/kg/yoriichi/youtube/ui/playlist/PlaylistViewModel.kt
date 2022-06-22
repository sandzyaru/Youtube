package kg.yoriichi.youtube.ui.playlist

import androidx.lifecycle.LiveData
import kg.yoriichi.youtube.core.base.BaseViewModel
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.data.remote.model.Playlists
import kg.yoriichi.youtube.repository.Repository


class PlaylistViewModel(private val repository: Repository): BaseViewModel() {
    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }
}