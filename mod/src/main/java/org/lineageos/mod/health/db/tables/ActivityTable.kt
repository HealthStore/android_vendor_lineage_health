/*
 * Copyright (C) 2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.mod.health.db.tables

import net.sqlcipher.database.SQLiteDatabase
import org.lineageos.mod.health.common.db.RecordColumns

object ActivityTable : Table {
    const val NAME = "activity"

    private const val CREATE_CMD = "CREATE TABLE IF NOT EXISTS $NAME (" +
        "${RecordColumns._ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
        "${RecordColumns._METRIC} INTEGER NOT NULL, " +
        "${RecordColumns.TIME} INTEGER NOT NULL DEFAULT 0, " +
        "${RecordColumns.DURATION} INTEGER NOT NULL DEFAULT 0, " +
        "${RecordColumns.AVG_SPEED} REAL NOT NULL DEFAULT 0, " +
        "${RecordColumns.CALORIES} INTEGER NOT NULL DEFAULT 0, " +
        "${RecordColumns.DISTANCE} REAL NOT NULL DEFAULT 0, " +
        "${RecordColumns.ELEVATION_GAIN} REAL NOT NULL DEFAULT 0, " +
        "${RecordColumns.NOTES} TEXT NOT NULL DEFAULT '', " +
        "${RecordColumns.STEPS} INTEGER NOT NULL DEFAULT 0 " +
        ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_CMD)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // empty
    }
}
