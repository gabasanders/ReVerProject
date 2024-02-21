package com.example.reverproject

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.reverproject.data.source.local.CardDatabase
import com.example.reverproject.data.source.local.LocalCard
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var database: CardDatabase

    @Before
    fun initDv(){
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            CardDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertCardandGetCard() = runTest {
        val card = LocalCard(
            id = "id",
            title = "hey",
            text = "String",
            progress = 200
        )

        database.cardDao().upsert(card)

        val cards = database.cardDao().observeAll().first()

        assertEquals(1,cards.size)
        assertEquals(card,cards[0])


    }
}



//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.reverproject", appContext.packageName)
//    }
//}