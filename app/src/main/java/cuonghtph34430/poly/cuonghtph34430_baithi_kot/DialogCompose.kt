package cuonghtph34430.poly.cuonghtph34430_baithi_kot
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogCompose(
    onConfim: () -> Unit,
    onClose: () -> Unit,
    title: String,
    mess: String
) {

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White
                    )
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp,
                    color = Color.Cyan
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = mess,
                    fontFamily = FontFamily.Serif,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onClose,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Cyan
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Close",
                            fontFamily = FontFamily.Serif
                        )
                    }
                    Button(
                        onClick = onConfim,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Cyan
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)

                    ) {
                        Text(
                            text = "Confim",
                            fontFamily = FontFamily.Serif
                        )
                    }
                }

            }
        }
    }


}


@Composable
fun LayoutTest() {
    var isShowDialog by remember {
        mutableStateOf(false)
    }

    if (isShowDialog) {
        DialogCompose(
            onConfim = { /*TODO*/ },
            onClose = { /*TODO*/ },
            title = "Thông báo !",
            mess = "Bạn có chắc chắn muốn xóa không?"
        )
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Show dialog")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingLayoutTest() {
    LayoutTest()
}