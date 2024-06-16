package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

@Composable
fun GetLayoutUpdateXeHoi(navController: NavHostController, id: Int) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        XeHoiDB::class.java,
        "xehoi"
    ).allowMainThreadQueries().build()

    // Trạng thái của các trường dữ liệu
    var st: XehoiModel? = null
    fun getXeHoi() {
        if (id != 0) {
            st = db.XeHoiDAO().getXeHoiById(id)
        }
    }
    getXeHoi()

    var name by remember { mutableStateOf(st?.name ?: "") }
    var img by remember { mutableStateOf(st?.img ?: "") }
    var price by remember { mutableStateOf(st?.price?.toString() ?: "") }
    var description by remember { mutableStateOf(st?.description ?: "") }
    var status by remember { mutableStateOf(st?.status?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Update xe hơi $id",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text(text = "Tên xe hơi", fontFamily = FontFamily.Serif) },
            textStyle = TextStyle(fontFamily = FontFamily.Serif),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = img,
            onValueChange = { img = it },
            placeholder = { Text(text = "Đường dẫn ảnh", fontFamily = FontFamily.Serif) },
            textStyle = TextStyle(fontFamily = FontFamily.Serif),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = price,
            onValueChange = { price = it },
            placeholder = { Text(text = "Giá tiền", fontFamily = FontFamily.Serif) },
            textStyle = TextStyle(fontFamily = FontFamily.Serif),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text(text = "Mô tả", fontFamily = FontFamily.Serif) },
            textStyle = TextStyle(fontFamily = FontFamily.Serif),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = status,
            onValueChange = { status = it },
            placeholder = { Text(text = "Trạng thái", fontFamily = FontFamily.Serif) },
            textStyle = TextStyle(fontFamily = FontFamily.Serif),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && img.isNotBlank() && price.isNotBlank() && description.isNotBlank() && status.isNotBlank()) {
                    st?.let {
                        it.name = name
                        it.img = img
                        it.price = price.toFloatOrNull() ?: 0f
                        it.description = description
                        it.status = status.toBoolean()

                        db.XeHoiDAO().update(it)
                        Toast.makeText(context, "Cập nhật xe hơi thành công", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTER.list.name) {
                            popUpTo(ROUTER.update.name) { inclusive = true }
                        }
                    }
                } else {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text(text = "Cập nhật", fontFamily = FontFamily.Serif)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetLayoutUpdateListXeHoi() {
    GetLayoutUpdateXeHoi(navController = rememberNavController(), id = 0)
}
