package lv.luhmirins.ideas

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import android.os.AsyncTask


/*
 * This is the main SQLite database access class. In this app we use Room framework for database.
 * More info: https://developer.android.com/topic/libraries/architecture/room.html
 */
@Database(entities = [Idea::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun notesDao(): IdeasDao

}

/*
 * This class describes "ideas" table in our database.
 */
@Entity(tableName = "ideas")
data class Idea(
    @ColumnInfo(name = "uid") @PrimaryKey(autoGenerate = true) var uid: Long = 0,
    @ColumnInfo(name = "place") var place: String = "",
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "notes") var notes: String = ""
)

/*
 * This interface describes what operation we can do with "ideas" database.
 * Query annotations contain proper SQL queries.
 * Insert, Update and Delete annotations are also just shortcuts for corresponding SQL queries.
 */
@Dao
interface IdeasDao {
    @Query("SELECT * FROM ideas")
    fun getAllIdeas(): LiveData<List<Idea>>

    @Query("SELECT * FROM ideas WHERE uid = :ideaId")
    fun getIdea(ideaId: Long): LiveData<Idea>

    @Insert
    fun saveIdea(idea: Idea)

    @Update
    fun updateIdea(idea: Idea)

    @Delete
    fun deleteIdea(idea: Idea)
}

/*
 * Since database operations can take a long time to complete those operations
 * MUST be do in background so it does not freeze applications UI.
 * For this reason we are using very simple AsyncTasks.
 *
 * https://developer.android.com/reference/android/os/AsyncTask.html
 */

class SaveAsync : AsyncTask<Idea, Unit, Unit>() {
    override fun doInBackground(vararg params: Idea) {
        MyApp.IDEAS.saveIdea(params[0])
    }
}

class UpdateAsync : AsyncTask<Idea, Unit, Unit>() {
    override fun doInBackground(vararg params: Idea) {
        MyApp.IDEAS.updateIdea(params[0])
    }
}

class DeleteAsync : AsyncTask<Idea, Unit, Unit>() {
    override fun doInBackground(vararg params: Idea) {
        MyApp.IDEAS.deleteIdea(params[0])
    }
}
