# Getting Started

## Code

```kotlin
document.body?.textContent = "Hello, ${greet()}"
```

# Ecosystem

## Gradle

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
implementation("io.ktor:ktor-client-core:1.5.1")
implementation("io.ktor:ktor-client-serialization:1.5.1")
```

## HTML

```html
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
```

## Code

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

## Gradle

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.2")
implementation(npm("camelcase", "6.2.0", generateExternals = true))
...
useCommonJs()
```

## Code

```kotlin
main(classes = "center") {
    section(classes = "in-out") {
        input(classes = "in") {
            autoFocus = true
            type = text
            placeholder = "in"
            onInputFunction = { event ->
                val input = (event.target as HTMLInputElement).value
                document.querySelector(".out")?.textContent = camelcase(input).ifEmpty { "n/a" }
            }
        }
        div(classes = "down") { +"⇓" }
        output(classes = "out") { +"n/a" }
    }
}
...
val options = js("{}").unsafeCast<Options>().apply {
    pascalCase = true
}
```

# React

## Gradle

```kotlin
maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
...
implementation("org.jetbrains:kotlin-react:17.0.1-pre.148-kotlin-1.4.21")
implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.148-kotlin-1.4.21")
implementation("org.jetbrains:kotlin-styled:5.2.1-pre.148-kotlin-1.4.21")
```

## Code

```kotlin
fun RBuilder.colorName(handler: ColorNameProps.() -> Unit): ReactElement =
    child(ColorNameComponent::class) {
        attrs(handler)
    }
```

# Fritz2

## Gradle

```kotlin
maven("https://oss.jfrog.org/artifactory/jfrog-dependencies")
maven("https://dl.bintray.com/patternfly-kotlin/patternfly-fritz2")
...
implementation("dev.fritz2:core:0.8")
```

## Code

```kotlin
class ColorDefinitionStore : RootStore<ColorDefinition>(ColorDefinition(YELLOW, 400)) {

    val background: Flow<String> = data.map { "bg-${it.color.name.toLowerCase()}-${it.level}" }

    val changeColor: Handler<Color> = handle { cd, color ->
        cd.copy(color = color)
    }
}
```

## Shell

```bash
cloc --include-lang=Kotlin .
```
