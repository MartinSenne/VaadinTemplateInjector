package com.example.templater.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this class: Determine all elements with "id" attribute.
 */
public class ElementCollector {

    /** Store the assignment of ids (given as string) to its correponding DOM element. */
    private Map<String, Element> idToElement;

    /**
     * Initialize class and determine all descendands element of root {@code elem}, having the "id" attribute.
     * @param elem is the element to start "scanning" at.
     */
    public ElementCollector(Element elem) {
        idToElement = new HashMap<String, Element>();
        if ( elem != null ) {
            gatherElements(elem);
        }
    }

    /**
     * Get element for a given id.
     * @param id
     * @return element for id or null if it is not present.
     */
    public Element getElementForId(String id) {
        Element elem = idToElement.get(id);
        return elem;
    }

    private void gatherElements(Element elem) {
        if (elem.hasAttribute("id")) {
            final String id = elem.getAttribute("id");
            idToElement.put(id, elem);
            elem.setInnerHTML("");
        } else {
            final int len = DOM.getChildCount(elem);
            for (int i = 0; i < len; i++) {
                gatherElements(DOM.getChild(elem, i));
            }
        }
        log("Elements found " + idToElement.size() + ": " + idToElement);
    }

    public static native void log(Object o) /*-{
        console.log(o)
    }-*/;
}
