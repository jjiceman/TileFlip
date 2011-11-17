package com.jamie.tileflip;

import java.util.Random;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Pieces extends BaseAdapter {

	private Context mContext;
	private int stage;
	private int[][] pieces;

	public Pieces(Context c, int stageSize) {
		stage = stageSize;
		pieces = new int[stageSize][stageSize];
		mContext = c;

		Random r = new Random();
		int shuffle = 5 + r.nextInt(10);

		for (int i = 0; i < shuffle; i++) {
			int chosenOne = r.nextInt(stage * stage);
			flipPieceAndAjcacent(chosenOne);
		}
	}

	public void flipPieceAndAjcacent(int pieceId) {
		flipPiece(pieceId);
		flipAdjacent(pieceId);
	}

	public void setPiece(int pieceId, int value) {
		pieces[to2D(pieceId)[0]][to2D(pieceId)[1]] = value;
	}

	public void flipPiece(int pieceId) {
		pieces[to2D(pieceId)[0]][to2D(pieceId)[1]] = Math.abs(pieces[to2D(pieceId)[0]][to2D(pieceId)[1]] - 1);
	}

	public void flipPiece(int x, int y) {
		pieces[y][x] = Math.abs(pieces[y][x] - 1);
	}

	public void flipAdjacent(int pieceId) {
		int x = to2D(pieceId)[1];
		int y = to2D(pieceId)[0];

		if (x > 0)
			flipPiece(x - 1, y);

		if (x < stage - 1)
			flipPiece(x + 1, y);

		if (y > 0)
			flipPiece(x, y - 1);

		if (y < stage - 1)
			flipPiece(x, y + 1);
	}

	public boolean checkWin() {
		int winningPiece = pieces[0][0];

		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[0].length; j++) {
				if (pieces[i][j] != winningPiece)
					return false;
			}
		}

		return true;
	}

	private int[] to2D(int pieceId) {
		if (pieceId < stage) {
			return new int[] { 0, pieceId };
		}
		return new int[] { (pieceId - (pieceId % stage)) / stage, pieceId % stage };
	}

	private int toId(int x, int y) {
		return x % stage + y;
	}

	@Override
	public int getCount() {
		return pieces.length * pieces[0].length;
	}

	@Override
	public Object getItem(int pieceId) {
		return pieces[to2D(pieceId)[0]][to2D(pieceId)[1]];
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
		imageView.setBackgroundColor(0x80000000);
		imageView.setImageResource(pieces[to2D(position)[0]][to2D(position)[1]] == 0 ? R.drawable.red : R.drawable.green);
		return imageView;
	}
}
