package site.ysy54.compose.screen_adaptation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDesignSize = compositionLocalOf {
    AppSize(width = 1080, height = 1920)
}

val LocalFullSize = compositionLocalOf {
    AppSize(width = 1080, height = 1920)
}

@Composable
fun ProvideFullSize(width: Int, height: Int, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalFullSize provides AppSize(width, height)) {
        content()
    }
}

@Composable
fun ProvideDesignSize(width: Int, height: Int, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDesignSize provides AppSize(width, height)) {
        content()
    }
}

private val widthRatioMap by lazy { mutableMapOf<Int, Float>() }

private val heightRatioMap by lazy { mutableMapOf<Int, Float>() }

val Double.dpWidth: Dp
    @Composable
    get() {
        val key = LocalDesignSize.current.width * 31 + LocalFullSize.current.width

        val ratio = widthRatioMap.getOrPut(key) {
            val fullDp = LocalFullSize.current.width / LocalDensity.current.density
            fullDp / LocalDesignSize.current.width
        }

        return (this * ratio).dp
    }

val Float.dpWidth: Dp
    @Composable
    get() = toDouble().dpWidth

val Int.dpWidth: Dp
    @Composable
    get() = toDouble().dpWidth

val Double.dpHeight: Dp
    @Composable
    get() {
        val key = LocalDesignSize.current.height * 31 + LocalFullSize.current.height

        val ratio = heightRatioMap.getOrPut(key) {
            val fullDp = LocalFullSize.current.height / LocalDensity.current.density
            fullDp / LocalDesignSize.current.height
        }

        return (this * ratio).dp
    }


val Float.dpHeight: Dp
    @Composable
    get() = toDouble().dpHeight

val Int.dpHeight: Dp
    @Composable
    get() = toDouble().dpHeight