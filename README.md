# Advanced Mobile Project

This project is for the 2025 course "Advanced Mobile Programming" (R504TL197-3002).

## List of Exercises

- [Exercise 1](https://github.com/enymariam/mobile-project/blob/b3a19e49e07c2898222c308291b2d6689799f9e7/app/src/main/java/com/example/advancedmobileapp/basic_layout/LayoutExercise.kt) 
- Exercise 2: [Model](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/models/RestaurantWithAvgRatingDto.kt), [viewModel](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/vm/RestaurantsWithAvgRatingsViewModel.kt) & [Root](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/RestaurantsWithAvgRatings.kt)
- Exercise 3: [Model](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/models/RestaurantDto.kt), [viewModel](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/vm/RestaurantViewModel.kt) & [Root](https://github.com/enymariam/mobile-project/blob/0b889d0682bcf28475f1850f80a70874e84ef2f7/app/src/main/java/com/example/advancedmobileapp/Restaurant.kt)
- Exercise 4: Mainly in [MainActivity.kt](https://github.com/enymariam/mobile-project/blob/6586684bf70584a332293509f132888cc1c14e50/app/src/main/java/com/example/advancedmobileapp/MainActivity.kt)
  - The drawable modal is not quite finished yet. It opens by swiping from left to right but the items do nothing.
  - Navigation between views works: Click card -> Click back.
  
- Exercise 5 is implemented in exercises 2-4. 
  - In Exercise 4, the view is still missing the card the the restaurant whose ratings we are viewing
  
- Dagger Hilt is used...
- Other relevant files: [AppModule.kt](https://github.com/enymariam/mobile-project/blob/7950f2ed3f362487b52c55574c435bf682140a8f/app/src/main/java/com/example/advancedmobileapp/AppModule.kt), [DataApi.kt](https://github.com/enymariam/mobile-project/blob/7950f2ed3f362487b52c55574c435bf682140a8f/app/src/main/java/com/example/advancedmobileapp/DataApi.kt), [TestApp.kt](https://github.com/enymariam/mobile-project/blob/7950f2ed3f362487b52c55574c435bf682140a8f/app/src/main/java/com/example/advancedmobileapp/TestApp.kt)

### Tests
Tests have been implemented in the following features...

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
Used in the ModalNavigationDrawer to map custom labels to each icon.

Question to chatGPT:
`How do I apply custom labels to composable modal navigation drawer?`

chatGPT guided me to create a small data class so tha I can add the label as a parameter.

### Summary about AI use

I seem to turn to AI when I'm still coding quite late at night and simply do not have the brainpower to think for myself or follow multistep instructions.
So an easy way to avoid AI is to just code at reasonable hours.

Also wanted to make a note that I do not copy+paste any AI answers (or code in general) but type it. At least that way I am still learning something.