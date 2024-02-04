package com.onestorecorp.gaa.prography.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.onestorecorp.gaa.prography.R


@Composable
fun PRBottomNavigation(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black
    ) {
        Row { content() }
    }
}


@Preview(showBackground = true)
@Composable
private fun PRBottomNavigationPreview() {
    PRBottomNavigation() {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = PRIcons.House), contentDescription = "") },
            onClick = {},
            selected = false
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = PRIcons.Cards), contentDescription = "") },
            onClick = {},
            selected = false
        )
    }
}