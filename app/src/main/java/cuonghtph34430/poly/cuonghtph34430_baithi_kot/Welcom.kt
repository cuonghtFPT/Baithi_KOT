package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun GetWelcom(navController: NavHostController) {
   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center

   ) {
       Column(
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Image(painter = painterResource(id = R.drawable.logo_),
               contentDescription = null,
               modifier = Modifier.size(250.dp))

           Text(
               text = "PH34430-16/06/2024",
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(bottom = 30.dp)
           )

           Button(onClick = {
           navController.navigate("list")/*TODO*/ }) {
               Text(text = "Next")

           }
       }
   }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetNavigation() {
    GetWelcom(navController = rememberNavController())
}