package com.luclx.rxandroid;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.luclx.rxandroid.cheesefinder.CheeseActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by LucLX on 2/1/17.
 */

@RunWith(AndroidJUnit4.class)
public class CheeseActivityEspressoTest {
    @Rule
    public ActivityTestRule<CheeseActivity> mActivityRule =
            new ActivityTestRule<>(CheeseActivity.class);

    @Test
    public void ensureSearchCorrect() {
        // Type text and then press the button.
        onView(withId(R.id.query_edit_text))
                .perform(typeText("Ban"), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());

//         Check that the text was changed.
        onView(withRecyclerView(R.id.list).atPosition(0)).check(matches(withText("Bandel")));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
