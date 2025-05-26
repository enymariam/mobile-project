package com.example.advancedmobileapp.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme


@Composable
fun LayoutExercise(modifier: Modifier = Modifier) {
    Scaffold{paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row(modifier = Modifier
                .fillMaxWidth ()
                .height(50.dp)
                .background(Color.LightGray)
                .drawBehind {
                    val borderSize = 1.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

            }
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF)
@Composable
private fun LayoutExercisePreview() {
    AdvancedMobileAppTheme{
        LayoutExercise()
    }

}