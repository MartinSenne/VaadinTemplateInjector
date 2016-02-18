package com.example.demo;

import javax.servlet.annotation.WebServlet;

import com.example.templater.TemplateInjector;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
@Theme("demo")
public class TemplaterUI extends UI {

	public static final String CLICK_ME = "CLICK ME!!!!";
	public static final String TEST_PLACE = "test-place";
	public static final String TEST_PLACE1 = "test-place1";

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TemplaterUI.class, widgetset = "com.example.demo.DemoWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		TemplateInjector templateInjector = new TemplateInjector();
		templateInjector.setWidth("100%");
		templateInjector.setHeight("100%");
		templateInjector.setHtml("<div style=\"width: 30%; height: 30%; border: red dotted 5px;\" id=\"" + TEST_PLACE + "\"></div>"
				+"<div style=\"width: 30%; height: 30%; border: red dotted 5px;\" id=\"" + TEST_PLACE1 + "\"></div>");

		setContent(templateInjector);

		Button clickMeButton = new Button(CLICK_ME);
		templateInjector.setComponent(clickMeButton, TEST_PLACE);

		TextField panel = new TextField("Fun");
		templateInjector.setComponent(panel, TEST_PLACE1);

		clickMeButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				clickMeButton.setCaption("I have been clicked.");
				panel.setValue("Hello");
			}
		});
	}
}