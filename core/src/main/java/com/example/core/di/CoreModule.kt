package com.example.core.di

import androidx.room.Room
import com.example.core.data.GamesRepository
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.GamesDatabase
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.IGamesRepository
import com.example.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataBaseModule = module {
    factory { get<GamesDatabase>().gamesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("supri".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java, "Games.db",
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/UGwY2lttaRoHnGd1gpeydmov1LzioQpzYTywtNSJkAU=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/R+V29DqDnO269dFhAAB5jMlZHepWpDGuoejXJjprh7A=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGamesRepository> {
        GamesRepository(
            get(),
            get(),
        )
    }
}