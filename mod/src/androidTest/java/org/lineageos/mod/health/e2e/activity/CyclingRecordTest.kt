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

package org.lineageos.mod.health.e2e.activity

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.lineageos.mod.health.e2e.RecordTest
import org.lineageos.mod.health.sdk.model.records.activity.ActivityRecord
import org.lineageos.mod.health.sdk.model.records.activity.CyclingRecord
import org.lineageos.mod.health.sdk.model.values.LengthValue
import org.lineageos.mod.health.sdk.model.values.SpeedValue
import org.lineageos.mod.health.sdk.repo.ActivityRecordsRepo

@RunWith(AndroidJUnit4::class)
class CyclingRecordTest : RecordTest<ActivityRecord, CyclingRecord, ActivityRecordsRepo>() {

    override fun getRepo(context: Context): ActivityRecordsRepo {
        return ActivityRecordsRepo.getInstance(context.contentResolver)
    }

    override fun getById(id: Long): CyclingRecord? {
        return repo.getCyclingRecord(id)
    }

    override fun getAllInMetric(): List<CyclingRecord> {
        return repo.allCyclingRecords
    }

    override fun testRecordA(): CyclingRecord {
        return CyclingRecord(
            0L,
            System.currentTimeMillis(),
            1000L,
            SpeedValue.kilometersPerHour(12.0),
            LengthValue.kilometers(50.0),
            LengthValue.meters(5.0)
        )
    }

    override fun testRecordB(): CyclingRecord {
        return CyclingRecord(
            0L,
            System.currentTimeMillis() - 1000L,
            60L,
            SpeedValue.kilometersPerHour(1.0),
            LengthValue.kilometers(9.1),
            LengthValue.meters(88.4)
        )
    }

    override fun invalidRecord(): CyclingRecord {
        return CyclingRecord(
            0L,
            System.currentTimeMillis(),
            -4L,
            SpeedValue.kilometersPerHour(-88.2),
            LengthValue.kilometers(-33.3),
            LengthValue.meters(-9.9)
        )
    }

    override fun updateTestRecord(record: CyclingRecord) {
        record.apply {
            duration += 5
            avgSpeed -= SpeedValue.milesPerHour(0.4)
            elevationGain += LengthValue.meters(2.0)
        }
    }
}
