package com.example.android.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class BakingWidgetService extends IntentService {
    private static final String ACTION_UPDATE_WIDGET = "com.example.android.bakingapp.action.update_widget";
    public BakingWidgetService() {
        super("com.example.android.bakingapp.BakingWidgetService");
    }
    public static void startWidget(Context context) {
        Intent intent = new Intent(context, BakingWidgetService.class);
        intent.setAction(ACTION_UPDATE_WIDGET);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            String action = intent.getAction();
            if(ACTION_UPDATE_WIDGET.equals(action)) {
                startUpdatingWidget();
            }
        }
    }
    private void startUpdatingWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidget.class));
        BakingWidget.updateWidget(this, appWidgetManager, appWidgetIds);
    }
}
