package com.test.lbc

import android.app.Application
import com.test.lbc.di.AppComponent
import com.test.lbc.di.DaggerAppComponent
import com.test.lbc.di.ApplicationModule

open class LBCApplication : Application() {
    private var appComponent: AppComponent? = null

    open fun getComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
        }
        return appComponent!!
    }
}