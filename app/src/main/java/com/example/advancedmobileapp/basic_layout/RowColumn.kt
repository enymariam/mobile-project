package com.example.advancedmobileapp.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme


@Composable
fun RowColumnDemo(modifier: Modifier = Modifier) {
    Scaffold{paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).background(Color.LightGray).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text("Hello")
            Text("World")

            Row {
                Text("First")
                Text("Second")
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF)
@Composable
private fun RowColumnDemoPreview() {
    AdvancedMobileAppTheme{
        RowColumnDemo()
    }

}