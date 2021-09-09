package com.ashush.filmopoisk_raw.di.domain

import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.domain.data_interfaces.IConfigRepository
import com.ashush.filmopoisk_raw.domain.data_interfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideDomainConfig(): DomainConfig {
        return  DomainConfig()
    }

    @Provides
    fun provideInteractor(repository: IDataRepository): Interactor {
        return Interactor(repository)
    }
}