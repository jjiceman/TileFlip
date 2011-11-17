package com.jamie.tileflip;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class TileFlipActivity extends Activity {

	static Pieces p;
	static int stage = 6;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Context c = this;
		p = new Pieces(c, stage);

		final GridView gridview = (GridView) findViewById(R.id.gridView1);
		gridview.setAdapter(p);
		gridview.setNumColumns(stage);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				p.flipPieceAndAjcacent(position);
				boolean b = p.checkWin();
				if (b) {
					Log.d("Info", "Winner!");
					stage++;
					gridview.setNumColumns(stage);
					p = new Pieces(c, stage);
				}
				p.notifyDataSetChanged();
			}
		});
	}

}