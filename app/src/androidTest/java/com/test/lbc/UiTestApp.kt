package com.test.lbc

import com.test.lbc.di.AppComponent
import com.test.lbc.di.DaggerAppComponent

class UiTestApp : LBCApplication() {

    override fun getComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .applicationModule(TestApplicationModule(this))
            .build()
    }
}