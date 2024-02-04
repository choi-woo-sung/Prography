package com.onestorecorp.gaa.prography.ui

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import com.onestorecorp.gaa.prography.SnapViewModel
import com.onestorecorp.gaa.prography.designsystem.component.PRCard
import kotlin.math.roundToInt

@Composable
fun SnapScreen(snapViewModel: SnapViewModel = viewModel()) {
    //withListCycle로 하는게 맞는데 시간이 없음.
    val photoList =  snapViewModel.photoStateFlow
    Box {
        photoList.reversed().forEachIndexed() {index , it ->
            SwipeableCard (
                onSwipeRight = {
                    
                }
            ){
                PRCard(url = it.url)
            }
        }
    }
}

@Composable
fun SwipeableCard(
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Float = 500f,
    sensitivityFactor: Float = 3f,
    content: @Composable () -> Unit
) {
    var offset by remember { mutableFloatStateOf(0f) }
    var dismiss by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    Box(
        modifier = Modifier
            .offset { IntOffset(offset.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragEnd = {
                    offset = 0f
                }) { change, dragAmount ->

                    offset += (dragAmount / density) * sensitivityFactor
                    Log.d("swipeOffset" , offset.toString())
                    when {
                        offset > swipeThreshold -> {
                            dismiss = true
                            onSwipeLeft.invoke()
                        }

                        offset < -swipeThreshold -> {
                            dismiss = true
                            onSwipeRight.invoke()
                        }
                    }
                    if (change.positionChange() != Offset.Zero) change.consume()
                }
            }
            .graphicsLayer(
                alpha = 1f - animateFloatAsState(if (dismiss) 1f else 0f).value,
                rotationZ = animateFloatAsState(offset / 50).value
            )
    ) {
        content()
    }
}