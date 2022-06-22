package kg.yoriichi.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.core.network.result.Resource.Companion.loading
import kg.yoriichi.youtube.data.remote.RemoteDataSource
import kg.yoriichi.youtube.data.remote.model.PlaylistItems
import kg.yoriichi.youtube.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository    (private val dataSource: RemoteDataSource) {

    fun getPlaylists(): LiveData<Resource<Playlists>> = liveData(Dispatchers.IO) {
            emit(loading())
            val response = dataSource.getPlayLists()
            emit(response)

    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> = liveData(Dispatchers.IO) {
            emit(loading())
            val response = dataSource.getPlaylistItems(playlistId)
            emit(response)
    }
}