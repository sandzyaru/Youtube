package kg.yoriichi.youtube.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kg.yoriichi.youtube.BuildConfig
import kg.yoriichi.youtube.core.network.RetrofitClient
import kg.yoriichi.youtube.core.network.result.Resource
import kg.yoriichi.youtube.core.network.result.Resource.Companion.loading
import kg.yoriichi.youtube.core.network.result.Resource.Companion.success
import kg.yoriichi.youtube.data.remote.ApiService
import kg.yoriichi.youtube.data.remote.model.PlaylistItems
import kg.yoriichi.youtube.data.remote.model.Playlists
import kg.yoriichi.youtube.utils.Constant
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }
    fun getPlaylists(): LiveData<Resource<Playlists>> {
        return liveData(Dispatchers.IO) {
            emit(loading())
            val response = apiService.getPlaylists(
                Constant.part,
                Constant.channelId,
                BuildConfig.BASE_API,
                Constant.maxResult
            )

            emit(if (response.isSuccessful) success(response.body()!!) else error(response.message()))
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItems>> {
        return liveData(Dispatchers.IO) {
            emit(loading())
            val response = apiService.getPlaylistItems(
                Constant.part,
                playlistId,
                BuildConfig.BASE_API,
                Constant.maxResult
            )

            emit(if (response.isSuccessful) success(response.body()!!) else error(response.message()))
        }
    }
}