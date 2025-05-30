# Advanced Mobile Project

This project is for the 2025 course "Advanced Mobile Programming" (R504TL197-3002).

## Backend

Project is dependent on this backend: [emo2025_backend](https://github.com/juhaniguru/emo2025_backend)

## List of Exercises

- [Exercise 1](https://github.com/enymariam/mobile-project/blob/b3a19e49e07c2898222c308291b2d6689799f9e7/app/src/main/java/com/example/advancedmobileapp/basic_layout/LayoutExercise.kt) 

- Exercises 2, 3, 4 and parts of 5 are implemented in the main app.

### Tests
Tests are pending

## List of Sources

Resources I used to resolve encountered issues. Most of these were later on presented in the course material also but I was too eager to move forward.

1. [Android Studio Project to GitHub](https://www.youtube.com/watch?v=d0uith-LE3o&ab_channel=PracticalCoding)
2. [Jetpack Composer Bottom Border](https://medium.com/@banmarkovic/jetpack-compose-bottom-border-8f1662c2aa84)
3. [CLEARTEXT communication not supported...etc](https://stackoverflow.com/questions/41650965/cleartext-communication-not-supported-on-retrofit)
4. [SocketException: socket failed: EPERM](https://stackoverflow.com/questions/56266801/java-net-socketexception-socket-failed-eperm-operation-not-permitted)
5. [Retrofit request to local server](https://stackoverflow.com/questions/40077927/simple-retrofit2-request-to-a-localhost-server)
6. [Composables.com/ModalNavigationDrawer](https://composables.com/material3/modalnavigationdrawer)

## Use of AI

I've tried to reference the use of AI as clearly as possible. In code I've commented on parts where AI was used, example:
`//Use of AI [0]`. The index number corresponds with each chapter below where I've further explained how the AI was used. Hopefully this is sufficient.

### 1 

Used in [Exercise 1](https://github.com/enymariam/mobile-project/blob/b3a19e49e07c2898222c308291b2d6689799f9e7/app/src/main/java/com/example/advancedmobileapp/basic_layout/LayoutExercise.kt)

Used chatGPT because I could not figure out how to use the Icon in IconButton.
I tried `painter = painterResource(R.drawable...` as per [documentation](https://developer.android.com/develop/ui/compose/components/icon-button).

Question to chatGPT: 
>IconButton(onClick = {}) {
    Icon(painter = painterResource(R.drawable.refresh),
                   contentDescription = "Refresh")
}

>I do not want any functionality in the IconButton. I only wish to use the Refresh icon from Material Design.


chatGPT guided me to use `imageVector = Icons.Default.Refresh` which worked. This was later shown on course material also, simply as `Icons(Icons.Default.Refresh...`

### 2
Used in the ModalNavigationDrawer (in [MainActivity.kt](https://github.com/enymariam/mobile-project/blob/6586684bf70584a332293509f132888cc1c14e50/app/src/main/java/com/example/advancedmobileapp/MainActivity.kt)) to map custom labels to each icon.

Question to chatGPT:
`How do I apply custom labels to composable modal navigation drawer?`

chatGPT guided me to create a small data class so that I can add the label as a parameter.

### 3

I got an error:
`Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $`

Which was caused by:
```
data class RestaurantsDto(
    val name: String,
    val review: List<RestaurantDto>
)

data class RestaurantState(
    val error: String? = null,
    val loading: Boolean = false,
    val theRestaurant: RestaurantsDto? = null
)
```
I checked the sample codes multiple times and I believe my implementation was the same. Could not get it to work though.

RestaurantDto is an array from API. RestaurantsDto is an object that includes the array. According to chatGpt, Gson got confused here.

Question to chatGPT:
```
I get an arror "Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $" and I believe it's coming from here:
data class RestaurantDto(
    val id: Int,
    @SerializedName("user_id") val userId: Int?,
    val value: Number?,
    val description: String?,
    @SerializedName("date_rated") val dateRated: String,
)

data class RestaurantsDto(
    val name: String,
    val review: List<RestaurantDto>
)

data class RestaurantState(
    val error: String? = null,
    val loading: Boolean = false,
    val theRestaurant: RestaurantsDto? = null
)
```

chatGpt guided me to make changes and with its help, it started to work.

## Summary

I seem to turn to AI when I'm still coding quite late at night and simply do not have the brainpower to think for myself or follow multistep instructions.
So an easy way to avoid AI is to just code at reasonable hours.

Also wanted to make a note that I do not copy+paste any AI answers (or code in general) but type it. At least that way I am still learning something.

I work mainly with PHP and got confused a few times when I got a code error (tried to concatenate using PHP syntax..), Kotlin does not come naturally quite yet.

All in all this course was much more pleasant than the basic mobile course that used xml and the design view.
Coding felt much more natural with it than xml (or using the design view). My overall stance on mobile programming was super negative after the first course but now I'm kind of looking forward to learn more. 
So Yay!

Please do message me if the functionalities I've done are not enough to pass the course.