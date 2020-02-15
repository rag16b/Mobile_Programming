package edu.fsu.cs.hw3;

/*
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class Homework3Test {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    private String[] urls;

    private String selectListItem(final int pos) {
        final ListView list = (ListView) mActivityRule.getActivity().findViewById(R.id.list_url);
        assertNotNull("The list was not loaded", list);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                list.performItemClick(list.getAdapter().getView(pos, null, null),
                        pos, list.getAdapter().getItemId(pos));
            }

        });
        getInstrumentation().waitForIdleSync();

        View child = list.getChildAt(pos);
        return ((TextView) child).getText().toString();
    }

    @Before
    public void initValidUrls() {

        urls = mActivityRule.getActivity().getResources().getStringArray(R.array.default_urls);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("edu.fsu.cs.hw3", appContext.getPackageName());
    }

    @Test
    public void checkListFragmentLoaded() throws Exception {
        UrlListFragment fragment = (UrlListFragment) mActivityRule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.list_frame);
        assertNotNull(fragment);
    }

    @Test
    public void checkWebFragmentLoaded() throws Exception {
        MyWebFragment fragment = (MyWebFragment) mActivityRule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.web_frame);
        assertNotNull(fragment);
    }

    @Test
    public void clickFirstLink() throws Exception {
        String link = selectListItem(0);
        assertEquals(urls[0], link);
    }

    @Test
    public void clickSecondLink() throws Exception {
        String link = selectListItem(1);
        assertEquals(urls[1], link);
    }

    @Test
    public void clickThirdLink() throws Exception {
        String link = selectListItem(2);
        assertEquals(urls[2], link);
    }

}
*/