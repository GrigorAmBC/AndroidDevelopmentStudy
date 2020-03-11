/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.instagramclone;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

//    //Enable Local Datastore.
//    Parse.enableLocalDatastore(this);
//
//    // Add your initialization code here
//    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
//            .applicationId("f33f5d9b-fd3a-4455-963a-9536424e85fa")
//            .clientKey("652a9e42-7bb3-46fe-af3d-9bc340345631")
//            .server("https://instagramandroid.azurewebsites.net")
//            .build()
//    );
//
//    //ParseUser.enableAutomaticUser();
//
//    ParseACL defaultACL = new ParseACL();
//    defaultACL.setPublicReadAccess(true);
//    defaultACL.setPublicWriteAccess(true);
//    ParseACL.setDefaultACL(defaultACL, true);

  }
}
