package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hARSH.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;


    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        this.mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        String defaultTrans = currentWord.getmDefaultTranslation();
        defaultTrans = defaultTrans.substring(0,1).toUpperCase() + defaultTrans.substring(1);
        defaultTextView.setText(defaultTrans);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        String miwokTrans = currentWord.getmMiwokTranslation();
        miwokTrans = miwokTrans.substring(0,1).toUpperCase() + miwokTrans.substring(1);
        miwokTextView.setText(miwokTrans);

        ImageView iconImage = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            iconImage.setImageResource(currentWord.getWordImageId());
        }else {
//            iconImage.setVisibility(View.GONE);
            View viewParent = (View) iconImage.getParent();
            viewParent.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.textContainer);

        int col = ResourcesCompat.getColor(this.getContext().getResources(), mColorResourceId, null);

        textContainer.setBackgroundColor(col);

        return listItemView;
    }
}
