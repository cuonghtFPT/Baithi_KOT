package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType", "CoroutineCreationDuringComposition")
@Composable
fun GetLayoutList(navController: NavHostController) {
    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        XeHoiDB::class.java,
        "xehoi"
    ).allowMainThreadQueries().build()

    val initialCars = listOf(
        XehoiModel(
            name = "Xe 1",
            img = "https://baohaauto.vn/wp-content/uploads/xe-hoi-the-thao-gia-re-8.jpg",
            price = 200f,
            description = "Đẹp, sang trọng",
            status = true
        ),
        XehoiModel(
            name = "Xe 2",
            img = "https://baohaauto.vn/wp-content/uploads/xe-hoi-the-thao-gia-re-8.jpg",
            price = 300f,
            description = "Mạnh mẽ",
            status = true
        ),
        XehoiModel(
            name = "Xe 3",
            img = "https://baohaauto.vn/wp-content/uploads/xe-hoi-the-thao-gia-re-8.jpg",
            price = 250f,
            description = "Sang trọng",
            status = false
        ),
        XehoiModel(
            name = "Xe 4",
            img = "https://baohaauto.vn/wp-content/uploads/xe-hoi-the-thao-gia-re-8.jpg",
            price = 220f,
            description = "Tiện nghi",
            status = true
        ),
        XehoiModel(
            name = "Xe 5",
            img = "https://baohaauto.vn/wp-content/uploads/xe-hoi-the-thao-gia-re-8.jpg",
            price = 280f,
            description = "An toàn",
            status = false
        )
    )

    // Insert initial cars if database is empty
    if (db.XeHoiDAO().getAll().isEmpty()) {
        CoroutineScope(Dispatchers.IO).launch {
            db.XeHoiDAO().insertXehoi(*initialCars.toTypedArray())
        }
    }

    var list by remember {
        mutableStateOf(db.XeHoiDAO().getAll())
    }

    var isShowDeleteDialog by remember {
        mutableStateOf(false)
    }

    var carToDelete by remember {
        mutableStateOf<XehoiModel?>(null)
    }

    var isShowDetailDialog by remember {
        mutableStateOf(false)
    }

    var carToShowDetails by remember {
        mutableStateOf<XehoiModel?>(null)
    }

    if (isShowDeleteDialog) {
        DialogCompose(
            onConfim = {
                carToDelete?.let {
                    db.XeHoiDAO().deleteById(it.id)
                    isShowDeleteDialog = false
                    Toast.makeText(
                        context,
                        "Delete xe hơi successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    list = db.XeHoiDAO().getAll()
                }
            },
            onClose = { isShowDeleteDialog = false },
            title = "Thông báo !",
            mess = "Bạn có chắc chắn muốn xóa không?"
        )
    }

    if (isShowDetailDialog) {
        carToShowDetails?.let { car ->
            DetailDialog(
                car = car,
                onClose = { isShowDetailDialog = false }
            )
        }
    }

    Column {
        Text(
            text = "Danh sách xe hơi",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(600),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            LazyColumn {
                items(list) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                carToShowDetails = item
                                isShowDetailDialog = true
                            }
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                val painter = rememberImagePainter(
                                    data = item.img,
                                    builder = {
                                        placeholder(R.drawable.baseline_place_24)
                                        error(R.drawable.baseline_error_24)
                                    }
                                )
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(top = 10.dp)
                                )
                                Text(
                                    text = "Tên xe: ${item.name}",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Giá: ${item.price}",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Mô tả: ${item.description}",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Trạng thái: ${if (item.status) "Sản phẩm mới" else "Sản phẩm cũ"}",
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.weight(0.3f)
                        ) {
                            IconButton(onClick = {
                                navController.navigate("${ROUTER.update.name}/${item.id}")
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )
                            }
                            IconButton(onClick = {
                                carToDelete = item
                                isShowDeleteDialog = true
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(ROUTER.add.name) {
                    popUpTo(ROUTER.list.name) { inclusive = true }
                }
            },
            containerColor = Color.Cyan,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun DetailDialog(car: XehoiModel, onClose: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onClose,
        title = {
            Text(text = "Chi tiết xe hơi")
        },
        text = {
            Column {
                val painter = rememberImagePainter(
                    data = car.img,
                    builder = {
                        placeholder(R.drawable.baseline_place_24)
                        error(R.drawable.baseline_error_24)
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)
                )
                Text(text = "Tên: ${car.name}", fontWeight = FontWeight.Bold)
                Text(text = "Giá: ${car.price}", fontWeight = FontWeight.Bold)
                Text(text = "Mô tả: ${car.description}", fontWeight = FontWeight.Bold)
                Text(
                    text = "Trạng thái: ${if (car.status) "Sản phẩm mới" else "Sản phẩm cũ"}",
                    fontWeight = FontWeight.Bold
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onClose) {
                Text("OK")
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GetListNavigation() {
    GetLayoutList(navController = rememberNavController())
}
