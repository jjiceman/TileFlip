package com.jamie.tileflip;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Pieces extends BaseAdapter {

	private Context mContext;
	private int[] pieces = { 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, };

	public Pieces(Context c) {
		mContext = c;
	}

	public void setPiece(int pieceId, int value) {
		pieces[pieceId] = value;
	}

	public void flipPiece(int pieceId) {
		pieces[pieceId] = Math.abs(pieces[pieceId] - 1);
	}

	@Override
	public int getCount() {
		return pieces.length;
	}

	@Override
	public Object getItem(int arg0) {
		return pieces[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(pieces[position] == 0 ? R.drawable.red : R.drawable.green);
		return imageView;
	}

}
