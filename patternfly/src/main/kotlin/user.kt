import kotlinx.serialization.Serializable
import org.patternfly.ItemsStore
import org.patternfly.SortInfo

val userStore = ItemsStore<User> { it.login.uuid }
val sortInfos = linkedMapOf<String, SortInfo<User>>(
    "last-name" to SortInfo("last-name", "Last name", compareBy { it.name.last }),
    "first-name" to SortInfo("first-name", "First name", compareBy { it.name.first }),
    "user-name" to SortInfo("user-name", "User name", compareBy { it.login.username }),
    "age" to SortInfo("age", "Age", compareBy { it.dob.age }),
    "nat" to SortInfo("nat", "Nationality", compareBy { it.nat }),
)

@Serializable
data class User(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DateOfBirth,
    val registered: DateOfBirth,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
) {
    fun match(query: String): Boolean = if (query.isEmpty()) true else {
        name.first.toLowerCase().contains(query.toLowerCase()) ||
                name.last.toLowerCase().contains(query.toLowerCase()) ||
                email.toLowerCase().contains(query.toLowerCase()) ||
                login.username.toLowerCase().contains(query.toLowerCase())
    }
}

@Serializable
data class Name(val title: String, val first: String, val last: String)

@Serializable
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    var coordinates: Coordinates,
    val timezone: Timezone
)

@Serializable
data class Street(val name: String, val number: Int)

@Serializable
data class Coordinates(val latitude: String, val longitude: String)

@Serializable
data class Timezone(val offset: String, val description: String)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

@Serializable
data class DateOfBirth(val date: String, val age: Int)

@Serializable
data class Picture(val large: String, val medium: String, val thumbnail: String)

@Serializable
data class RandomUsers(val results: List<User>, val info: Info)

@Serializable
data class Info(val seed: String, val results: Int, val page: Int, val version: String)
