package com.onestorecorp.gaa.prography.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.onestorecorp.gaa.prography.R


@Composable
fun PRBottomNavigation(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        modifier = Modifier,
        containerColor = Color.Black
    ) {
        Row { content() }
    }
}


@Preview
@Composable
private fun PRBottomNavigationPreview() {
    PRBottomNavigation() {
        NavigationBarItem(
            icon = { Icon(painter = R.drawable.ic_cards, contentDescription = item) },
            label = { Text(item) },
            selected = selectedItem == index,
            onClick = { selectedItem = index }
        )
        Icon(painter = painterResource(id = R.drawable.ic_cards), tint = Color.White, contentDescription = "")
    }
}