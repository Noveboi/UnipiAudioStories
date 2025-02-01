package com.example.unipiaudiostories.core;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.example.unipiaudiostories.domain.Story;

import java.util.Locale;

/**
 * Contains methods for reading text using the text-to-speech API.
 */
public class TtsService implements TextToSpeech.OnInitListener {

    TextToSpeech tts;

    public TtsService(Context context) {
        tts = new TextToSpeech(context, this);
    }

    public void readStory(Story story) {
        int result = tts.speak(story.getContent(), TextToSpeech.QUEUE_FLUSH, null, null);

        Log.i("TTS", "Reading story, result: " + result);
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.SUCCESS) {
            Log.w("TTS INIT", "Failed to initialize TTS. Code: " + status);
            return;
        }

        int result = tts.setLanguage(Locale.ENGLISH);
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.w("TTS LANG", "Language not supported.");
        }
    }

    public void shutdown() {
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
}
