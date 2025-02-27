package com.example.space_invaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear e instalar la vista personalizada
        GameView gameView = new GameView(this, null);
        setContentView(gameView);
    }
}

class GameView extends View {
    private Bitmap invader;
    private Bitmap starship;
    private int screenWidth, screenHeight;
    private static final int INVADER_SIZE = 80;
    private static final int STARSHIP_WIDTH = 150;
    private static final int STARSHIP_HEIGHT = 100;
    private static final int ROWS = 5;
    private static final int COLUMNS = 5;
    private static final int PADDING = 20;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        invader = BitmapFactory.decodeResource(getResources(), R.drawable.invader);
        starship = BitmapFactory.decodeResource(getResources(), R.drawable.starship);

        // Escalar las imágenes
        invader = Bitmap.createScaledBitmap(invader, INVADER_SIZE, INVADER_SIZE, false);
        starship = Bitmap.createScaledBitmap(starship, STARSHIP_WIDTH, STARSHIP_HEIGHT, false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        // Poner el fondo negro
        canvas.drawColor(android.graphics.Color.BLACK);

        // Dibujar los invaders en una cuadrícula 5x5
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int x = col * (INVADER_SIZE + PADDING);
                int y = row * (INVADER_SIZE + PADDING);
                canvas.drawBitmap(invader, x, y, paint);
            }
        }

        // Dibujar la nave en la parte inferior centrada
        int shipX = (screenWidth - STARSHIP_WIDTH) / 2;
        int shipY = screenHeight - STARSHIP_HEIGHT - PADDING;
        canvas.drawBitmap(starship, shipX, shipY, paint);
    }

}
