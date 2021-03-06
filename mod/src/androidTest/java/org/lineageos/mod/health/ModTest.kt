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

package org.lineageos.mod.health

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun helloTest() {
        Assert.assertTrue(context.packageName.contains("org.lineageos.mod"))
    }

    @Test
    fun modInstalled() {
        val apps = context.packageManager.getInstalledApplications(0)
        Assert.assertTrue(
            apps.find { it.packageName == "org.lineageos.mod.health" } != null,
        )
    }

    @Ignore("Ignore for emulator testing")
    @Test
    fun featureXml() {
        Assert.assertTrue(CareCache.isSupported(context))
    }
}
