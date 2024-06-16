package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cuonghtph34430.poly.cuonghtph34430_baithi_kot.ui.theme.Cuonghtph34430_Baithi_KOTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           GetLayoutNavigation()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Cuonghtph34430_Baithi_KOTTheme {

    }
}