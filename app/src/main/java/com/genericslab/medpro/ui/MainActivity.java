package com.genericslab.medpro.ui;

import android.os.Bundle;

import com.genericslab.droidplate.CoreActivity;
import com.genericslab.droidplate.config.Config;
import com.genericslab.droidplate.ui.login.DPSignInActivity_;
import com.genericslab.medpro.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Trace;

/**
 * Created by shahab on 7/5/16.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends CoreActivity {

    protected final String TRACE_TAG = Config.TRACE_TAG + "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @Trace(tag = TRACE_TAG)
    @AfterViews
    void afterViews() {
        DPSignInActivity_.intent(this).start();
        finish();
    }
}