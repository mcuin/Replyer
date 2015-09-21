package com.mykal.textback.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.mykal.textback.AddReply;

import junit.framework.Assert;

/**
 * Created by mykal on 9/21/15.
 */
public class AddReplyTest extends ActivityInstrumentationTestCase2 {
    public AddReplyTest(Class<AddReply> activityClass) {
        super(activityClass);
    }

    public void TitleEmptyTest() throws Exception {

        AddReply activity = (AddReply) getActivity();

        String title = activity.saveName();

        Assert.assertNotNull(title);
    }
    
    public void MessageEmptyTest() throws Exception {

        AddReply activity = (AddReply) getActivity();

        String message = activity.saveMessage();

        Assert.assertNotNull(message);
    }
}
