package com.onestorecorp.gaa.prography

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.designsystem.component.PRBottomNavigation
import com.onestorecorp.gaa.prography.designsystem.component.PRHeader
import com.onestorecorp.gaa.prography.designsystem.component.PRIcons
import com.onestorecorp.gaa.prography.ui.SnapScreen
import com.onestorecorp.gaa.prography.ui.theme.PrographyTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrographyTheme {
                var state by remember { mutableStateOf(BottomNaviEnum.Home) }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Box(modifier = Modifier.windowInsetsPadding(TopAppBarDefaults.windowInsets)) {
                            Icon(modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center), imageVector = ImageVector.vectorResource(id = R.drawable.ic_prography), contentDescription = "")
                        }
                    },
                    bottomBar = {
                        PRBottomNavigation() {
                            NavigationBarItem(
                                icon = { Icon(painter = painterResource(id = PRIcons.House), contentDescription = "") },
                                onClick = { state = BottomNaviEnum.Home },
                                selected = state == BottomNaviEnum.Home
                            )
                            NavigationBarItem(
                                icon = { Icon(painter = painterResource(id = PRIcons.Cards), contentDescription = "") },
                                onClick = { state = BottomNaviEnum.BookMark },
                                selected = state == BottomNaviEnum.BookMark
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (state) {
                            BottomNaviEnum.Home -> MainScreen()
                            BottomNaviEnum.BookMark -> SnapScreen()
                        }
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
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photoPagingItems[item]?.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    modifier = Modifier.align(Alignment.BottomStart),
                    text = photoPagingItems[item]?.slug ?: ""
                )
            }
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

enum class BottomNaviEnum {
    Home, BookMark
}