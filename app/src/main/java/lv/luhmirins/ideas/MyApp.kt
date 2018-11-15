package lv.luhmirins.ideas

import android.app.Application
import androidx.room.Room

/*
 * This is a main class of our app. It is preserved in memory while app is running so it
 * is relatively safe to store out database access object here.
 */
class MyApp : Application() {

    companion object {
        lateinit var IDEAS: IdeasDao
    }

    /*
     * This is an application lifecycle callback that is executed single time when application starts.
     */
    override fun onCreate() {
        super.onCreate()

        IDEAS = Room
            .databaseBuilder(this, AppDb::class.java, "notes-db")
            .build()
            .notesDao()
    }
}
