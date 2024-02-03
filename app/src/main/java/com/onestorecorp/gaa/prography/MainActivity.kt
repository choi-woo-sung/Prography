package com.onestorecorp.gaa.prography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.designsystem.component.PRHeader
import com.onestorecorp.gaa.prography.ui.theme.PrographyTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrographyTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {

                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
        val moviePagingItems: LazyPagingItems<Photo> =
            mainViewModel.photoStateFlow.collectAsLazyPagingItems()

        RecentImageScreen(photoPagingItems = moviePagingItems)

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
) {
    Greeting(name = "Bookmark")
}

@Composable
fun RecentImageScreen(
    photoPagingItems: LazyPagingItems<Photo>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2), modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) { PRHeader(text = "최신 이미지") }
        items(photoPagingItems.itemCount) { item ->

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoPagingItems[item]?.url)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(12.dp))
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrographyTheme {
        Greeting("Android")
    }
}