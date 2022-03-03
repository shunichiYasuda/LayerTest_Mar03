package com.GenSci.sample.testLayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PrimaryController {
	// とりあえず使う
	Random gen = new Random();
	// ファイルが読まれた後から設定されてしまうパラメータ
	int GEN; // 世代数
	int EXP; // 実験数。
	double[][] aveDataTable;
	double[][] typeDataTable;
	double[] nowExpAveData; // 実験番号が定まった後から中身が入る平均値
	double[][] nowExpTypeData; // 実験番号が定まった後から中身が入るタイプ別個体比率
	// アプリを立ち上げた時に設定されてしまうパラメータ.これらは initialize()で値が決まる。
	double widthCanvas1; // canvas1 の width
	double heightCanvas1; // canvas1 の height
	double widthCanvas2; // canvas2 の width
	double heightCanvas2; // canvas2 の height
	//描画のときのマージン
	int margin = 10;
	GraphicsContext gc1, gc2;
	//
	int nowGen;
	@FXML
	Canvas canvas1;
	@FXML
	Canvas canvas2;
	@FXML
	Button execBtn;

	@FXML
	Button quitBtn;

	@FXML
	public void quitAction() {
		System.exit(0);
	}

//
	@FXML
	TextArea log;
//
	@FXML
	Slider slider;
	//
	@FXML
	TextField genTextField;
	//
	@FXML
	private Spinner<Integer> expSpinner;

	//
	@FXML
	protected void initialize() {
		// 仮にGENとEXPをここで設定しておく。
		GEN = 200;
		EXP = 10;
		// canvas のサイズを取得
		widthCanvas1 = canvas1.getWidth();
		heightCanvas1 = canvas1.getHeight();
		log.appendText("size=" + widthCanvas1 + "x" + heightCanvas1 + "\n");
		widthCanvas2 = canvas2.getWidth();
		heightCanvas2 = canvas2.getHeight();
		log.appendText("size=" + widthCanvas2 + "x" + heightCanvas2 + "\n");
		//
		gc1 = canvas1.getGraphicsContext2D();
		gc2 = canvas2.getGraphicsContext2D();
		gc1.setFill(Color.WHITE);
		gc1.fillRect(0, 0, widthCanvas1, heightCanvas1);
		gc1.setStroke(Color.BLACK);
		gc1.strokeLine(0, 0, 200, 200);
	}

	//
	@FXML
	public void openAction() {
		// 今はファイルを読まないので、仮定として世代数と実験数を initialize()で設定しておく。
		aveDataTable = new double[GEN][EXP];
		// データを適当に入れる。
		for (int i = 0; i < aveDataTable.length; i++) {
			for (int j = 0; j < aveDataTable[0].length; j++) {
				aveDataTable[i][j] = round(gen.nextDouble());
			}
		}
//		// チェック
//		for (double[] darray : aveDataTable) {
//			for (double d : darray) {
//				log.appendText(d + "\t");
//			}
//			log.appendText("\n");
//		}
		//グラフエリアの上限下限・左右
		int top,bottom,left,right;
		top = margin;
		bottom = (int)heightCanvas1 - margin;
		left = margin;
		right = (int)widthCanvas1 - margin;
		//グラフエリアの高さ・幅
		int gHight,gWidth;
		gHight = bottom - top; //このpixel が値 0.0 - 3.0
		gWidth = right - left; //このpixel 
		// Layer Test
		slider.setMax(GEN);
		slider.setMajorTickUnit(GEN / 10.0);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				nowGen = newValue.intValue();
				genTextField.setText("" + nowGen);
				double coeff = widthCanvas2 / GEN;
				int xPos = (int) (coeff * nowGen);
				if (nowGen != 0 && nowGen != GEN) {
					// gc.setFill(Color.WHITE);
					gc2.clearRect(0, 0, widthCanvas2, heightCanvas2);
					gc2.setStroke(Color.RED);
					gc2.strokeLine(xPos, 0, xPos, heightCanvas2);
				} else {
					gc2.clearRect(0, 0, widthCanvas2, heightCanvas2);
				}
			}

		});
		//
	} // end of method openAction()
	// draw garaphics2D と double 配列を与えられて Canvas1 にグラフを描く
	public void draw(GraphicsContext g, double[] data) {
		
	}
	// translate pixel 幅と double配列を与えられて、pixel値の配列を返す。
	public int[] translate(int height,double[] data) {
		int[] r = new int[data.length];
		double maxValue = 3.0;
		//数値1.0あたりの pixel数
		double par = maxValue / height;
		
		
		
		return r;
	}
	//
	public double round(double in) {
		double after = 0.0;
		after = new BigDecimal(String.valueOf(in)).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return after;
	}

}
