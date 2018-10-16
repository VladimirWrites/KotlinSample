package com.vlad1m1r.kotlintest.di

import com.vlad1m1r.kotlintest.data.providers.PhotosProvider
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.domain.interactors.IPhotosProvider
import org.koin.dsl.module.module

val photosModule = module {
    single<IPhotosProvider> { PhotosProvider(get()) }
    single { GetPhotos(get()) }
}