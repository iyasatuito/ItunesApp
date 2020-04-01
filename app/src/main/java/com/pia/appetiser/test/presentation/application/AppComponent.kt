package com.pia.appetiser.test.presentation.application

import com.pia.appetiser.test.data.di.DataModule
import com.pia.appetiser.test.data.di.NetworkModule
import com.pia.appetiser.test.presentation.di.PerApplication
import com.pia.appetiser.test.presentation.di.modules.ActivityBindingModule
import com.pia.appetiser.test.presentation.di.modules.ApplicationModule
import com.pia.appetiser.test.presentation.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


@Component(
    modules = [
        (ApplicationModule::class),
        (NetworkModule::class),
        (DataModule::class),
        (ActivityBindingModule::class),
        (ViewModelModule::class),
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class)]
)
@PerApplication
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(appetiserTestApplication: AppetiserTestApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: AppetiserTestApplication)
}
