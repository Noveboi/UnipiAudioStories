package com.example.unipiaudiostories.core;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.example.unipiaudiostories.domain.Story;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

/**
 * Contains methods for reading text using the text-to-speech API.
 */
public class TtsService implements TextToSpeech.OnInitListener {

    private final TextToSpeech tts;
    private final Queue<String> readQueue;
    private boolean isInitialized;
    private static final String logTag = "Audio Stories TTS";

    public TtsService(Context context) {
        isInitialized = false;
        tts = new TextToSpeech(context, this);
        readQueue = new LinkedList<>();
    }


    /**
     * Schedules the given story to be read.
     * @param story The story whose contents are going to be read.
     */
    public void readStory(Story story) {
        String text = "Now reading: \"" + story.getTitle() + "\" by " + story.getAuthor() + "... " +
                story.getContent();

        readQueue.offer(text);
        getNextSpeech();
    }

    /**
     * Uses the defined Queue to defer speech execution to avoid pre-init speeches.
     */
    private void getNextSpeech() {
        if (tts != null && !tts.isSpeaking() && isInitialized) {
            String text = readQueue.poll();
            if (text == null) {
                Log.i(logTag, "Queue is empty...");
                return;
            }

            Log.i(logTag, "Speaking next item in queue...");
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
            tts.setOnUtteranceProgressListener(new UtteranceListener());
        }
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.SUCCESS) {
            Log.w(logTag, "Failed to initialize TTS. Code: " + status);
            return;
        }

        int result = tts.setLanguage(Locale.ENGLISH);
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.w(logTag, "Language not supported.");
            return;
        }

        isInitialized = true;

        if (!readQueue.isEmpty()) {
            getNextSpeech();
        }
    }

    /**
     * Stops the current speech (if any) and shuts down the Text-to-Speech system.
     */
    public void shutdown() {
        if (tts != null) {
            readQueue.clear();
            tts.stop();
            tts.shutdown();
        }
    }

    private class UtteranceListener extends UtteranceProgressListener {

        @Override
        public void onStart(String utteranceId) {
            // Don't need this method
        }

        @Override
        public void onDone(String utteranceId) {
            Log.i(logTag, "Finished speaking.");
            getNextSpeech();
        }

        @Override
        public void onError(String utteranceId) {
            Log.w(logTag, "An error occurred inside UtteranceProgressListener");
            tts.stop();
        }
    }
}
