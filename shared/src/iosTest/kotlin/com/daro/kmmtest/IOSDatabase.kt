package com.daro.kmmtest

import co.touchlab.sqliter.DatabaseConfiguration
import com.daro.kmmtest.db.KmmDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual fun getDatabase(): SqlDriver {
    val schema = KmmDatabase.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "breeds_database",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}
