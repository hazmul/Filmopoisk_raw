package com.ashush.filmopoisk_raw.di.domain

import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.data_interfaces.IRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(repository: IRepository): Interactor {
        return Interactor(repository)
    }
}