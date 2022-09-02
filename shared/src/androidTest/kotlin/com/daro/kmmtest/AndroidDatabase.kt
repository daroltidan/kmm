package com.daro.kmmtest

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.daro.kmmtest.db.KmmDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual fun getDatabase(): SqlDriver {
    return try {
        val app = ApplicationProvider.getApplicationContext<Application>()
        AndroidSqliteDriver(KmmDatabase.Schema, app, "breeds_database")
    } catch (exception: IllegalStateException) {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { KmmDatabase.Schema.create(it) }
    }
}
