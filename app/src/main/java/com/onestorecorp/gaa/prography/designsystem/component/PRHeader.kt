package com.onestorecorp.gaa.prography.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PRHeader(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp), text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun PRHeaderPreview() {
    Surface {
        PRHeader(text = "Header")
    }
}