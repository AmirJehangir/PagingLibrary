package com.amir.mart.paginglibraryv3.modules

import android.content.Context
import android.content.SharedPreferences
import com.amir.mart.paginglibraryv3.data.repositoryimpl.MartRepositoryImpl
import com.amir.mart.paginglibraryv3.data.source.SharedPrefDataSource
import com.amir.mart.paginglibraryv3.data.source.db.MartDataBase
import com.amir.mart.paginglibraryv3.data.source.remote.ApiInterface
import com.amir.mart.paginglibraryv3.domain.repository.MartRepository
import com.amir.mart.paginglibraryv3.domain.usecase.MartContentUseCase
import com.amir.mart.paginglibraryv3.domain.usecase.MartLocleContentUseCase
import com.amir.mart.paginglibraryv3.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Amir.jehangir on 27,September,2021
 */



var RepositoryModule = module {

    single { SharedPrefDataSource(createSharedPreference(get())) }
    single { createSetMartContentRepo(get(),get()) }

}
fun createSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE)
}

var MartContentModules = module {

    single { createSetMartContentUseCase(get()) }
    single { createSetMartLocleContentUseCase(get()) }
    viewModel { MainViewModel(get(),get(),get()) }
}

//
fun createSetMartContentUseCase(changePasswordRepository: MartRepository): MartContentUseCase {
    return MartContentUseCase(changePasswordRepository)
}
//
fun createSetMartLocleContentUseCase(changePasswordRepository: MartRepository): MartLocleContentUseCase {
    return MartLocleContentUseCase(changePasswordRepository)
}
//
fun createSetMartContentRepo(apiInterface: ApiInterface,martDataBase: MartDataBase): MartRepository {
    return MartRepositoryImpl(apiInterface,martDataBase)
}
