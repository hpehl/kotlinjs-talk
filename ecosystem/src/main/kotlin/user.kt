import kotlinx.serialization.Serializable

@Serializable
data class RandomUsers(val results: List<User>)

@Serializable
data class User(val name: Name, val picture: Picture)

@Serializable
data class Name(val title: String, val first: String, val last: String)

@Serializable
data class Picture(val large: String, val medium: String, val thumbnail: String)
