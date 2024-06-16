package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "xehoi_table")
data class XehoiModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "ph34430_name") var name: String?,
    @ColumnInfo(name = "ph34430_img") var img: String,
    @ColumnInfo(name = "ph34430_price") var price: Float?,
    @ColumnInfo(name = "ph34430_description") var description: String?,
    @ColumnInfo(name = "ph34430_status") var status: Boolean

)
