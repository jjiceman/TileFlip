package com.jamie.tileflip;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TileFlipActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Pieces p = new Pieces(this);

	    GridView gridview = (GridView) findViewById(R.id.gridView1);
	    gridview.setAdapter(p);
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	p.flipPiece(position);
	        	p.notifyDataSetChanged();
	        }
	    });
    }
}