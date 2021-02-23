# Getting Started

## Kotlin

```kotlin
document.body?.textContent = "Hello, ${greet()}"
```

# Ecosystem

## HTML

```html
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
```

## Kotlin

### User

```kotlin
@Serializable
data class RandomUsers(val results: List<User>)

@Serializable
data class User(val name: Name, val picture: Picture)

@Serializable
data class Name(val title: String, val first: String, val last: String)

@Serializable
data class Picture(val large: String, val medium: String, val thumbnail: String)
```

### Client

```kotlin
const val URL = "https://randomuser.me/api/"

val client = HttpClient(Js) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            ignoreUnknownKeys = true
        })
    }
}

suspend fun randomUser(): User = client.get<RandomUsers>(URL).results.first()
```

### App

```kotlin
div(classes = "min-h-screen bg-gray-800 flex flex-col justify-center") {
    figure(classes = "w-2/5 mx-auto rounded-md bg-gray-100") {
        img(classes = "w-32 h-32 mx-auto rounded-full mt-8 ring-1 ring-offset-4 ring-gray-400") {
            id = "user-photo"
            alt = "New user"
            onClickFunction = { reload() }
        }
        figcaption(classes = "p-8 text-center") {
            div(classes = "text-lg text-gray-400") { +"Hi, my name is" }
            div(classes = "text-3xl") { id = "user-name" }
        }
    }
}
```

# JS Interop

pending

# React

pending

# PatternFly

pending

