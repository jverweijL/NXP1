package com.liferay.nxp1;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.barteksc.pdfviewer.PDFView;
import com.liferay.mobile.screens.viewsets.defaultviews.dlfile.display.PdfDisplayView;
import com.liferay.mobile.screens.viewsets.defaultviews.webcontent.display.WebContentDisplayView;
import java.io.File;

/**
 * @author Víctor Galán Grande
 */

public class PdfDisplayViewCustom extends PdfDisplayView {

	private PDFView pdfView;
	private ProgressBar progressBarHorizontal;
	private TextView progressText;

	public PdfDisplayViewCustom(Context context) {
		super(context);
	}

	public PdfDisplayViewCustom(Context context, AttributeSet attributes) {
		super(context, attributes);
	}

	public PdfDisplayViewCustom(Context context, AttributeSet attributes, int defaultStyle) {
		super(context, attributes, defaultStyle);
	}

	@Override
	public void loadFileEntry(String url) {
		progressBarHorizontal.setVisibility(GONE);
		progressBar.setVisibility(GONE);
		progressText.setVisibility(GONE);

		pdfView.fromFile(new File(url)).load();
	}

	@Override
	public void renderDownloadProgress(int progress) {
		super.renderDownloadProgress(progress);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		pdfView = (PDFView) findViewById(R.id.pdf_view);
		progressText = (TextView) findViewById(com.liferay.mobile.screens.R.id.liferay_asset_progress_number);
		progressBarHorizontal = (ProgressBar) findViewById(com.liferay.mobile.screens.R.id.liferay_asset_progress_horizontal);
	}
}
