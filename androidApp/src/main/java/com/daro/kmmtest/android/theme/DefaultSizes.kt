package com.daro.kmmtest.android.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DefaultSizes(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 12.dp,
    val xLarge: Dp = 20.dp
)

val LocalSizes = compositionLocalOf { DefaultSizes() }
