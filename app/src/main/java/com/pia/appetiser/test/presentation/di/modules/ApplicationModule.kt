package com.pia.appetiser.test.presentation.di.modules

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.room.Room
import com.pia.appetiser.test.data.local.AppDatabase
import com.pia.appetiser.test.presentation.application.AppetiserTestApplication
import com.pia.appetiser.test.presentation.common.arch.PostExecutionThread
import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import com.pia.appetiser.test.presentation.common.arch.ThreadPoolExecutor
import com.pia.appetiser.test.presentation.common.arch.UIThread
import com.pia.appetiser.test.presentation.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplication(appetiserItunesApplication: AppetiserTestApplication): Application = appetiserItunesApplication

    @Provides
    @PerApplication
    fun provideContext(
        appetiserItunesApplication: AppetiserTestApplication
    ): Context = appetiserItunesApplication.applicationContext


    @SuppressLint("HardwareIds")
    @Provides
    @Named(Settings.Secure.ANDROID_ID)
    @PerApplication
    internal fun provideDeviceId(context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    @Provides
    @PerApplication
    fun provideLocalBroadcastManager(context: Context) =
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(context)

    @Provides
    @PerApplication
    fun provideSharedPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: ThreadPoolExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread


    @Provides
    @PerApplication
    fun provideUserRoomDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "itunes-room-db"
        )
            .allowMainThreadQueries()
            .build()
    }
}

