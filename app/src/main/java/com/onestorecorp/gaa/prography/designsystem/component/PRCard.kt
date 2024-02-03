package com.onestorecorp.gaa.prography.designsystem.component

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.onestorecorp.gaa.prography.ui.theme.BrandColor
import com.onestorecorp.gaa.prography.ui.theme.Gray30
import com.onestorecorp.gaa.prography.ui.theme.Gray60

@Composable
fun PRCard(url: String) {
    Card {
//        AsyncImage(model = url, contentDescription = "")
        PRCardBottom()
    }
}

@Composable
private fun PRCardBottom(
    modifier: Modifier,
    closeButtonClicked: () -> Unit = {},
    bookmarkButtonClicked: () -> Unit = {},
    informationButtonClicked: () -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 43.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.size(52.dp), onClick = closeButtonClicked) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                color = Color.White,
                border = BorderStroke(1.dp, Gray30)
            ) {
                Icon(
                    modifier = Modifier.padding(16.32.dp),
                    painter = painterResource(id = PRIcons.Close), contentDescription = "",
                    tint = Gray60
                )
            }
        }
        IconButton(modifier = Modifier.size(72.dp), onClick = bookmarkButtonClicked) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                color = BrandColor,
            ) {
                Icon(
                    modifier = Modifier.padding(20.dp),
                    painter = painterResource(id = PRIcons.Bookmark), contentDescription = "",
                    tint = Color.White
                )
            }
        }
        IconButton(modifier = Modifier.size(52.dp), onClick = informationButtonClicked) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                color = Color.White,
                border = BorderStroke(1.dp, Gray30)
            ) {
                Icon(
                    modifier = Modifier.padding(16.32.dp),
                    painter = painterResource(id = PRIcons.Information),
                    contentDescription = "",
                    tint = Gray60
                )
            }
        }

    }
}

@Preview
@Composable
private fun PRCardPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        PRCard(url = "https://images.unsplash.com/photo-1634170380000-3e3e3e3e3e3e")
    }
}