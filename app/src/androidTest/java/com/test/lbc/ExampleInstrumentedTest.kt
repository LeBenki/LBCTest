package com.test.lbc

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu
import com.test.lbc.database.SongDb
import com.test.lbc.ui.SongActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var activityScenario: ActivityScenario<SongActivity>

    private var webServer: MockWebServer? = null

    @Before
    fun setup() {
        webServer = MockWebServer()
        webServer?.start(8080)
        ApplicationProvider.getApplicationContext<UiTestApp>().deleteDatabase(SongDb.DB_NAME)
    }

    @After
    fun tearDown() {
        activityScenario.close()
        webServer?.shutdown()
    }


    @Test
    fun testSongsSorting() {
        val dispatcher = MockServerDispatcher()
        webServer!!.dispatcher = dispatcher.RequestDispatcher()
        activityScenario = ActivityScenario.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SongActivity::class.java
            )
        )
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.STARTED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        assertListItemCount(R.id.songList, 3)
        assertDisplayedAtPosition(
            R.id.songList,
            0,
            R.id.name,
            "baccusamus beatae ad facilis cum similique qui sunt"
        )

        clickMenu(R.id.title)
        assertDisplayedAtPosition(R.id.songList, 0, R.id.name, "aincidunt alias vel enim")

        clickMenu(R.id.album)
        assertDisplayedAtPosition(
            R.id.songList,
            0,
            R.id.name,
            "baccusamus beatae ad facilis cum similique qui sunt"
        )

        activityScenario.moveToState(Lifecycle.State.DESTROYED)

    }
}