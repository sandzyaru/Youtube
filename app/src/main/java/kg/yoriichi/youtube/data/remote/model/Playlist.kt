package kg.yoriichi.youtube.data.remote.model


data class Playlist(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)