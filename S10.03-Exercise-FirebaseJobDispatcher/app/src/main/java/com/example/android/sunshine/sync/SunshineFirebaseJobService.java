package com.example.android.sunshine.sync;/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
// COMPLETED (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService

// COMPLETED (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService
public class SunshineFirebaseJobService extends JobService {
    //  COMPLETED (4) Declare an ASyncTask field called mFetchWeatherTask
    AsyncTask mFetchWeatherTask;

    /**
     * The entry point to your Job. Implementations should offload work to another thread of
     * execution as soon as possible.
     * <p>
     * This is called by the Job Dispatcher to tell us we should start our job. Keep in mind this
     * method is run on the application's main thread, so we need to offload work to a background
     * thread.
     *
     * @return whether there is more work remaining.
     */
    //  COMPLETED (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
    @Override
    public boolean onStartJob(@NonNull final JobParameters job) {

        mFetchWeatherTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = getApplicationContext();
                SunshineSyncTask.syncWeather(context);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                //  COMPLETED (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments
                jobFinished(job, false);
            }
        };

        mFetchWeatherTask.execute();
        return false;
    }


    /**
     * Called when the scheduling engine has decided to interrupt the execution of a running job,
     * most likely because the runtime constraints associated with the job are no longer satisfied.
     *
     * @return whether the job should be retried
     *///  COMPLETED (7) Override onStopJob, cancel the ASyncTask if it's not null and return true
    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        if (mFetchWeatherTask != null) mFetchWeatherTask.cancel(true);
        return true;
    }
}


