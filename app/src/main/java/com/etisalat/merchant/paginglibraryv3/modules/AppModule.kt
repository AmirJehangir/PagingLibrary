package com.etisalat.merchant.paginglibraryv3.modules

import android.content.Context
import android.content.SharedPreferences
import com.etisalat.merchant.paginglibraryv3.data.repositoryimpl.MartRepositoryImpl
import com.etisalat.merchant.paginglibraryv3.data.source.SharedPrefDataSource
import com.etisalat.merchant.paginglibraryv3.data.source.db.Dao.MartDao
import com.etisalat.merchant.paginglibraryv3.data.source.db.MartDataBase
import com.etisalat.merchant.paginglibraryv3.data.source.remote.ApiInterface
import com.etisalat.merchant.paginglibraryv3.domain.repository.MartRepository
import com.etisalat.merchant.paginglibraryv3.domain.usecase.MartContentUseCase
import com.etisalat.merchant.paginglibraryv3.presentation.main.MainViewModel
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
    viewModel { MainViewModel(get(),get()) }
}

//
fun createSetMartContentUseCase(changePasswordRepository: MartRepository): MartContentUseCase {
    return MartContentUseCase(changePasswordRepository)
}
//
fun createSetMartContentRepo(apiInterface: ApiInterface,martDataBase: MartDataBase): MartRepository {
    return MartRepositoryImpl(apiInterface,martDataBase)
}
