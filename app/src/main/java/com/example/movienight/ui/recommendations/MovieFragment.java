package com.example.movienight.ui.recommendations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.movienight.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieFragment extends Fragment {
    public static final String MOVIE_URL = "url";
    public static final String ARG_OBJECT = "title";
    public static final String ARG_POSTER = "poster";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle args = getArguments();
        ((TextView) view.findViewById(R.id.textViewGallery))
                .setText(args.getString(ARG_OBJECT));
        ImageView iv = (ImageView) view.findViewById(R.id.imageView2);
        new RetrieveBitmapFromUrlTask(iv)
                .execute(args.getString(ARG_POSTER));
        iv.setClickable(true);
        final Intent i = new Intent(getActivity(), WebViewActivity.class);
        i.putExtra(Intent.EXTRA_TEXT, args.getString(MOVIE_URL));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        //((ImageView) view.findViewById(R.id.imageView2))
        //        .setImageBitmap(LoadImageFromWebOperations(args.getString(ARG_POSTER)));
    }

}

class RetrieveBitmapFromUrlTask extends AsyncTask<String, Void, Bitmap> {
    ImageView iv;

    public RetrieveBitmapFromUrlTask(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        try{
            URL conn = new URL("http://image.tmdb.org/t/p/w185//" + url);
            Bitmap bmp = BitmapFactory.decodeStream(conn.openConnection().getInputStream());
            return bmp;
        }
        catch(IOException ex){
            return null;
        }
    }

    protected void onPostExecute(Bitmap result) {
        iv.setImageBitmap(result);
    }
}
