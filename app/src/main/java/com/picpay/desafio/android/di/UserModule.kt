package com.picpay.desafio.android.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.UserLocalDataSource
import com.picpay.desafio.android.data.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.mapper.UserCacheToDomainMapper
import com.picpay.desafio.android.data.mapper.UserDomainToCacheMapper
import com.picpay.desafio.android.data.mapper.UserResponseToModelMapper
import com.picpay.desafio.android.data.remote.api.UserApi
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.datasource.UserRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.presentation.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataBaseModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database").build() }
    single { get<AppDatabase>().userDao() }
}
val useCaseModule = module {
    single { GetUserUseCase(get()) }
}
val mapperModule = module {
    single { UserDomainToCacheMapper() }
    single { UserCacheToDomainMapper() }
    single { UserResponseToModelMapper() }
}
val dataSourceModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), get()) }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get(), get(), get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val userViewModelModule = module {
    viewModel { UserViewModel(get()) }
}

val networkModule = module {
    factory { createRetrofit().create(UserApi::class.java) }
}

private fun createRetrofit(): Retrofit {
    val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    val okHttp = OkHttpClient.Builder().build()
    val gson = GsonBuilder().create()
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}