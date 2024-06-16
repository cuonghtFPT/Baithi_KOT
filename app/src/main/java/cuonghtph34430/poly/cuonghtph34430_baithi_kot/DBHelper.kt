package cuonghtph34430.poly.cuonghtph34430_baithi_kot

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Database (entities = [XehoiModel::class], version = 1)
abstract class XeHoiDB: RoomDatabase() {
    abstract fun XeHoiDAO(): XeHoiDAO
}
@Dao
interface XeHoiDAO {
    @Query("SELECT * FROM xehoi_table")
    fun getAll(): List<XehoiModel>

    @Delete
    fun delete(xh: XehoiModel)


    @Query("DELETE FROM xehoi_table WHERE id = :id")
    fun deleteById(id: Int)  // Thêm phương thức này để xóa dựa trên ID

    @Insert
    fun insertXehoi(vararg xh: XehoiModel)

    @Update
    fun update(xh: XehoiModel)

    @Query("SELECT * FROM xehoi_table WHERE id = :id")
    fun getXeHoiById(id: Int): XehoiModel?


}