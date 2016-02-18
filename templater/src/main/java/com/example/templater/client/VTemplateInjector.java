package com.example.templater.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.BrowserInfo;

/**
 * Client-side widget.
 */
public class VTemplateInjector extends ComplexPanel {

    ElementCollector elementCollector;

    public static final String CLASSNAME = "templateinjector";

    public VTemplateInjector() {

        setElement(Document.get().createDivElement());
        // Clear any unwanted styling
        Style style = getElement().getStyle();
        style.setBorderStyle(Style.BorderStyle.NONE);
        style.setMargin(0, Style.Unit.PX);
        style.setPadding(0, Style.Unit.PX);

        if (BrowserInfo.get().isIE()) {
            style.setPosition(Style.Position.RELATIVE);
        }

        setStyleName(CLASSNAME);

        elementCollector = new ElementCollector(null);
    }

    public static native void log(Object o) /*-{
        console.log(o)
    }-*/;


    public void initializeHTML(String template) {
        getElement().setInnerHTML(template);

        // update id to element
        elementCollector = new ElementCollector(getElement());
    }

    public void addWidget(String id, Widget widget) {
        log("addWidget for " + widget + " and id " + id);
        if (widget == null) { return; }
        if (id == null) { return; }

        Element elem = elementCollector.getElementForId(id);

        if (elem == null) { // no element found
            log("  No HTML element with id '" + id + "' found.");
            return;
        }

        log("addWidget/adding widget: " + widget);
        super.add(widget, elem);
    }
}






// OLD CODE ======================================================
//
//    public void addWidget(String id, Widget widget) {
//        log("addWidget for " + widget + " and id " + id);
//        if (widget == null) { return; }
//        if (id == null) { return; }
//
//        Element elem = idToElement.get(id);
//
//        if (elem == null) { // no element found
//            log("  No element with id '" + id + "' found.");
//            return;
//        }
//
//        Widget oldWidget = idToWidget.get(id);
//
//        log("  Widget: " + widget);
//        log("  Old Widget: " + oldWidget);
//
//        if ( oldWidget != widget ) { // only if old and new widget are different, widget is exchanged
//            if (oldWidget != null) { // old widget present, then remove it
//                idToWidget.remove(id);
//                super.remove(oldWidget);
//                log("addWidget/removing widget: " + widget + " and elem " + elem);
//            }
//
//            // Add widget
//            idToWidget.put(id, widget);
//            super.add(widget, elem);
//            log("addWidget/adding widget: " + widget);
//        }
//        log("addWidget finished.");
//    }
