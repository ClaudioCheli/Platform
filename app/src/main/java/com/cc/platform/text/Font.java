package com.cc.platform.text;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.cc.platform.textures.Texture;
import com.cc.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 22/09/2017.
 */

public class Font {

    public final static int CHAR_START      = 32;           // First Character (ASCII Code)
    public final static int CHAR_END        = 126;            // Last Character (ASCII Code)
    public final static int CHAR_CNT        = ( ( ( CHAR_END - CHAR_START ) + 1 ) + 1 );  // Character Count (Including Character to use for Unknown)

    public final static int CHAR_NONE       = 32;                     // Character to Use for Unknown (ASCII Code)
    public final static int CHAR_UNKNOWN    = ( CHAR_CNT - 1 );    // Index of the Unknown Character

    public final static int FONT_SIZE_MIN   = 6;          // Minumum Font Size (Pixels)
    public final static int FONT_SIZE_MAX   = 180;

    AssetManager asset;

    int fontPadX, fontPadY;                            // Font Padding (Pixels; On Each Side, ie. Doubled on Both X+Y Axis)

    float fontHeight;                                  // Font Height (Actual; Pixels)
    float fontAscent;                                  // Font Ascent (Above Baseline; Pixels)
    float fontDescent;                                 // Font Descent (Below Baseline; Pixels)

    int textureSize;                                   // Texture Size for Font (Square) [NOTE: Public for Testing Purposes Only!]
    TextureRegion textureRgn;                          // Full Texture Region

    float charWidthMax;                                // Character Width (Maximum; Pixels)
    float charHeight;                                  // Character Height (Maximum; Pixels)
    final float[] charWidths;                          // Width of Each Character (Actual; Pixels)
    TextureRegion[] charRgn;                           // Region of Each Character (Texture Coordinates)
    int cellWidth, cellHeight;                         // Character Cell Width/Height
    int rowCnt, colCnt;                                // Number of Rows/Columns

    float scaleX, scaleY;                              // Font Scale (X,Y Axis)
    float spaceX;

    private Texture texture;

    public Font(AssetManager asset, String file, int fontSize, Vector2f padding) {
        this.asset = asset;

        charWidths = new float[CHAR_CNT];
        charRgn = new TextureRegion[CHAR_CNT];

        fontPadX = 0; fontPadY = 0;

        fontHeight = 0.0f;
        fontAscent = 0.0f; fontDescent = 0.0f;

        textureSize = 0;

        charWidthMax = 0; charHeight = 0;

        cellWidth = 0; cellHeight = 0;
        rowCnt = 0; colCnt = 0;

        scaleX = 1.0f; scaleY = 1.0f;
        spaceX = 0.0f;

        loadFont(file, fontSize, padding);
    }

    public boolean loadFont(String file, int size, Vector2f padding) {
        fontPadX = (int) padding.x;
        fontPadY = (int) padding.y;

        Typeface tf = Typeface.createFromAsset(asset, file);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setColor(0xffffffff);
        paint.setTypeface(tf);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        fontHeight = (float) Math.ceil(Math.abs(fontMetrics.bottom) + Math.abs(fontMetrics.top));
        fontAscent = (float) Math.ceil(Math.abs(fontMetrics.ascent));
        fontDescent = (float) Math.ceil(Math.abs(fontMetrics.descent));

        char[] s = new char[2];
        charWidthMax = charHeight = 0;
        float[] w = new float[2];
        int cnt = 0;
        for(char c = CHAR_START; c <= CHAR_END; c++) {
            s[0] = c;
            paint.getTextWidths(s, 0, 1, w);
            charWidths[cnt] = w[0];
            if(charWidths[cnt] > charWidthMax) {
                charWidthMax = charWidths[cnt];
            }
            cnt++;
        }

        s[0] = CHAR_NONE;
        paint.getTextWidths(s, 0, 1, w);
        charWidths[cnt] = w[0];
        if(charWidths[cnt] > charWidthMax) {
            charWidthMax = charWidths[cnt];
        }
        cnt++;

        charHeight = fontHeight;

        cellWidth = (int) charWidthMax + (2 * fontPadX);
        cellHeight = (int) charHeight + (2 * fontPadY);
        int maxSize = cellWidth > cellHeight ? cellWidth : cellHeight;
        if(maxSize < FONT_SIZE_MIN || maxSize > FONT_SIZE_MAX)
            return false;

        if(maxSize <= 24)
            textureSize = 256;
        else if(maxSize <= 40)
            textureSize = 512;
        else if(maxSize <= 80)
            textureSize = 1024;
        else
            textureSize = 2048;

        Bitmap bitmap = Bitmap.createBitmap(textureSize, textureSize, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0x00000000);

        colCnt = textureSize / cellWidth;
        rowCnt = (int) Math.ceil((float) CHAR_CNT / (float) colCnt);

        float x = fontPadX;
        float y = (cellHeight - 1) - fontDescent - fontPadY;
        for(char c = CHAR_START; c <= CHAR_END; c++) {
            s[0] = c;
            canvas.drawText(s, 0, 1, x, y, paint);
            x += cellWidth;
            if((x + cellWidth - fontPadX) > textureSize) {
                x = fontPadX;
                y += cellHeight;
            }
        }
        s[0] = CHAR_NONE;
        canvas.drawText(s, 0, 1, x, y, paint);
        bitmap.getWidth();
        texture = new Texture(bitmap);

        x = 0;
        y = 0;
        for(int c = 0; c < CHAR_CNT; c++) {
            charRgn[c] = new TextureRegion(textureSize, textureSize, x, y, cellWidth-1, cellHeight-1);
            x += cellWidth;
            if(x + cellWidth > textureSize) {
                x = 0;
                y += cellHeight;
            }
        }

        textureRgn = new TextureRegion(textureSize, textureSize, 0, 0, textureSize, textureSize);

        return true;
    }

    public Texture getTexture() {return texture;}

    public Vector2f getDimension() {return new Vector2f(cellWidth*scaleX, cellHeight*scaleY);}

    public TextureRegion getRegion(int c) {return charRgn[c]; }
}
