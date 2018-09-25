package com.yy.learn.day02Solution;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class ImageFileFindListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("Image file finding complete!");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		System.out.println("Image file finding timeout!");
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("Image file finding error!");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("Image file finding start!");
	}
}
