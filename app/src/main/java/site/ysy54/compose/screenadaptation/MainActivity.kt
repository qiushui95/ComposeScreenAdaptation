package site.ysy54.compose.screenadaptation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.doOnNextLayout
import site.ysy54.compose.screen_adaptation.ProvideDesignSize
import site.ysy54.compose.screen_adaptation.ProvideFullSize
import site.ysy54.compose.screen_adaptation.dpHeight
import site.ysy54.compose.screen_adaptation.dpWidth
import site.ysy54.compose.screenadaptation.ui.theme.ComposeScreenAdaptationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(android.R.id.content)
            .doOnNextLayout {
                setContent {
                    ComposeScreenAdaptationTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(color = MaterialTheme.colors.background) {
                            ProvideFullSize(width = it.width, height = it.height) {
                                ProvideDesignSize(width = 750, height = 1334) {
                                    SplashScreen()
                                }
                            }
                        }
                    }
                }
            }
    }
}

@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(740.dpWidth)
                .height(1330.dpHeight)
                .background(Color.Red)
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ComposeScreenAdaptationTheme {
        Greeting("Android")
    }
}