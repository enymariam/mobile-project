# Advanced Mobile Project

## Requirements

## List of Exercises

- [Exercise 1](https://github.com/enymariam/mobile-project/blob/b3a19e49e07c2898222c308291b2d6689799f9e7/app/src/main/java/com/example/advancedmobileapp/basic_layout/LayoutExercise.kt) 

## List of Sources

1. [Android Studio Project to GitHub](https://www.youtube.com/watch?v=d0uith-LE3o&ab_channel=PracticalCoding)
2. [Jetpack Composer Bottom Border](https://medium.com/@banmarkovic/jetpack-compose-bottom-border-8f1662c2aa84)

## Use of AI

### Exercise 1

Used chatGPT because I could not figure out how to use the Icon in IconButton.
I tried `painter = painterResource(R.drawable...` as per [documentation](https://developer.android.com/develop/ui/compose/components/icon-button).

Question to chatGPT: 
>IconButton(onClick = {}) {
    Icon(painter = painterResource(R.drawable.refresh),
                   contentDescription = "Refresh")
}

>I do not want any functionality in the IconButton. I only wish to use the Refresh icon from Material Design.


chatGPT guided me to use `imageVector = Icons.Default.Refresh` which worked.