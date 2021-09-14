import com.example.qtube.data.domain.Feeds
import com.google.gson.annotations.SerializedName

data class VideoResponse (
    @SerializedName("feed") val feed: List<Feeds>?,
)
