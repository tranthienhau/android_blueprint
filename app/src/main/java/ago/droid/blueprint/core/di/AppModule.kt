package ago.droid.blueprint.core.di

import ago.droid.blueprint.MainApplication
import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesApplication(): MainApplication = MainApplication.instance

}