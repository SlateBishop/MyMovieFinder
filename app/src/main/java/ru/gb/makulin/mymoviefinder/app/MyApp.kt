package ru.gb.makulin.mymoviefinder.app

import android.app.Application
import androidx.room.Room
import ru.gb.makulin.mymoviefinder.room.HistoryDAO
import ru.gb.makulin.mymoviefinder.room.HistoryDataBase

class MyApp : Application() {


    companion object {
        private var appInstance: MyApp? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "HistoryDataBase.db"

        fun getHistoryDAO(): HistoryDAO {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance != null) {
                            db = Room.databaseBuilder(
                                appInstance!!.applicationContext,
                                HistoryDataBase::class.java,
                                DB_NAME
                            )
                                .allowMainThreadQueries() //FIXME временно для проверки работы кода
                                .build()
                        } else {
                            throw  IllegalStateException("Application is null")
                        }
                    }
                }
            }
            return db!!.historyDAO()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }


}