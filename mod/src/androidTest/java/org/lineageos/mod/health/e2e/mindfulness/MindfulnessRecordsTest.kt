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

package org.lineageos.mod.health.e2e.mindfulness

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.lineageos.mod.health.common.HealthStoreUri
import org.lineageos.mod.health.common.Metric
import org.lineageos.mod.health.common.db.RecordColumns
import org.lineageos.mod.health.sdk.model.records.mindfulness.SleepRecord
import org.lineageos.mod.health.sdk.repo.MindfulnessRecordsRepo

@RunWith(AndroidJUnit4::class)
class MindfulnessRecordsTest {
    private lateinit var cr: ContentResolver
    private lateinit var repo: MindfulnessRecordsRepo

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        cr = context.contentResolver
        repo = MindfulnessRecordsRepo.getInstance(cr)
    }

    @Test
    fun invalidGet() {
        val a = SleepRecord(
            0L,
            System.currentTimeMillis(),
            1580000,
        )
        val idA = repo.insert(a)
        Assert.assertNull(repo.getMeditationRecord(idA))
        val fromDb = repo.getSleepRecord(idA)
        if (fromDb == null) {
            Assert.fail("fromDb == null")
            return
        }
        Assert.assertTrue(repo.delete(fromDb))
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidInsert() {
        val cv = ContentValues().apply {
            put(RecordColumns._METRIC, Metric.MOOD)
            put(RecordColumns.TIME, System.currentTimeMillis())
        }

        val invalidUri = Uri.withAppendedPath(
            HealthStoreUri.MINDFULNESS,
            "${Metric.SLEEP}"
        )
        cr.insert(invalidUri, cv)
        Assert.fail("Did not throw IllegalArgumentException")
    }
}