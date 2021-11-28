package com.example.appnicolas

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appnicolas.utils.Validator
import com.google.common.truth.Truth

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.appnicolas", appContext.packageName)
    }

    @Test
    fun when_Input_Url_IsValid(){
        val url = "https://www.google.com"
        val result =   if(url.isNotEmpty()){
            Validator.isValidUrl(url)
        }else{
            false
        }
       assertEquals(true, result)
    }

    @Test
    fun when_Input_Url_IsNotValid(){
        val url = "Pepito eats an apple"
        val result = Validator.isValidUrl(url)
        Truth.assertThat(result).isEqualTo(false)
    }
}