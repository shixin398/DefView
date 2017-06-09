/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.example.android.autofillframework.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.autofillframework.R;

/**
 * This is used to launch sample activities that showcase autofill.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.standardViewSignInButton).setOnClickListener(this);
        findViewById(R.id.virtualViewSignInButton).setOnClickListener(this);
        findViewById(R.id.creditCardCheckoutButton).setOnClickListener(this);
        findViewById(R.id.standardLoginWithAutoCompleteButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.standardViewSignInButton:
                intent = StandardSignInActivity.getStartActivityIntent(this);
                break;
            case R.id.virtualViewSignInButton:
                intent = VirtualSignInActivity.getStartActivityIntent(this);
                break;
            case R.id.creditCardCheckoutButton:
                intent = CreditCardActivity.getStartActivityIntent(this);
                break;
            case R.id.standardLoginWithAutoCompleteButton:
                intent = StandardAutoCompleteSignInActivity.getStartActivityIntent(this);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}