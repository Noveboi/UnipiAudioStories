package com.example.unipiaudiostories.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewbinding.ViewBinding;

import com.example.unipiaudiostories.R;
import com.example.unipiaudiostories.core.SettingsService;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class AppActivityBase<TBinding extends ViewBinding> extends AppCompatActivity {

    private TBinding binding;
    protected TBinding getBinding() { return binding; }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflate the ViewBinding so that all Views (Buttons, TextViews, etc...) are initialized.
        binding = inflateBinding();
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the Locale from storage and refresh the Activity if it's not the default
        LocaleListCompat appLocale = SettingsService.getLocale(this);
        AppCompatDelegate.setApplicationLocales(appLocale);

        onAfterCreate();
    }

    /**
     * Use reflection to dynamically invoke ViewBinding.inflate(getLayoutInflater()) and
     * avoid having to declare an abstract method for this.
     */
    private TBinding inflateBinding() {
        try {
            ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
            Class<TBinding> bindingClass = (Class<TBinding>) superClass.getActualTypeArguments()[0];

            Method inflateMethod = bindingClass.getMethod("inflate", LayoutInflater.class);
            return (TBinding) inflateMethod.invoke(null, getLayoutInflater());
        } catch (Exception e) {
            throw new RuntimeException("Failed to inflate ViewBinding in " + getClass().getSimpleName(), e);
        }
    }
    protected abstract void onAfterCreate();
}
