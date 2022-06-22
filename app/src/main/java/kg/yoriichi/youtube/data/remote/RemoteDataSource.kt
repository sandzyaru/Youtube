package kg.yoriichi.youtube.data.remote

import kg.yoriichi.youtube.BuildConfig
import kg.yoriichi.youtube.core.network.BaseDataSource
import kg.yoriichi.youtube.utils.Constant
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: ApiService): BaseDataSource() {

    suspend fun getPlayLists() = getResult {
        apiService.getPlaylists(
            Constant.part,
            Constant.channelId,
            BuildConfig.BASE_API,
            Constant.maxResult
        )
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(
            Constant.part,
            playlistId,
            BuildConfig.BASE_API,
            Constant.maxResult
        )
    }
}