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

package org.lineageos.build;

import org.gradle.api.JavaVersion;

public final class Config {

    public static final int compileSdk = 29;
    public static final int minSdk = 27;
    public static final int targetSdk = 29;

    public static final int versionCode = 1;
    public static final String versionName = "1.0.0";

    public static final JavaVersion javaVersion = JavaVersion.VERSION_1_8;

}