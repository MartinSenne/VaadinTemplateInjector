package com.example.templater.client;

import com.example.templater.TemplateInjector;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

import java.util.Iterator;
import java.util.List;

/**
 * A container connector.
 */
@Connect(TemplateInjector.class)
public class TemplateInjectorConnector extends AbstractComponentContainerConnector {

    private static final long serialVersionUID = 7364777474592332411L;

    public TemplateInjectorConnector() {
        super();
    }

    @Override
    public VTemplateInjector getWidget() {
        return (VTemplateInjector) super.getWidget();
    }

    @Override
    public TemplateInjectorState getState() {
        return (TemplateInjectorState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        log("onStateChanged");
        super.onStateChanged(stateChangeEvent);

        updateHtml();
        detachWidgets();
        attachWidgets();
    }

    private void updateHtml() {
        log("  updateHtml");
        VTemplateInjector widget = getWidget();
        widget.initializeHTML(getState().html);
    }

    private void attachWidgets() {
        List<ComponentConnector> connectors = getChildComponents();

        VTemplateInjector templateWidget = getWidget();

        for (ComponentConnector child : connectors) {
            String id = getState().connectorsToIds.get(child);
            if (id != null) {
                Widget childWidget = child.getWidget();
                log("Attaching widget " + childWidget + " at id " + id);
                templateWidget.addWidget(id, childWidget);
            }
        }
    }

    private void detachWidgets() {
        VTemplateInjector templateWidget = getWidget();

        Iterator<Widget> widgetIterator = templateWidget.iterator();
        while (widgetIterator.hasNext()) {
            Widget current =  widgetIterator.next();
            log("Detach: Detaching " + current);
            widgetIterator.remove();
        }
    }

    public static native void log(Object o) /*-{
        console.log(o)
    }-*/;

    @Override
    public void updateCaption(ComponentConnector connector) {
        log("updateCaption - not implemented.");
    }

    /**
     * Called if the hierarchy of connectors on client-side changes (e.g. induced by adding a component on server-side to the layout)
     *
     * @param connectorHierarchyChangeEvent
     */
    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        log("onConnectorHierarchyChange");

        // actually we do not need to handle this, as a change of the connector hierarchy is also reflected by a state change
        // and thus is handled in onStateChange (see above).
    }
}








// OLD CODE ======================================================
//
//	@Override
//	public void onStateChanged(StateChangeEvent stateChangeEvent) {
//		super.onStateChanged(stateChangeEvent);
//
//		TemplateInjectorState state = getState();
//
//
//		getWidget().initializeHTML(state.html);
//
//		// HTML w = (HTML)getWidget();
//		// w.setSize(state.width, state.height);
//		// w.getElementForId().setInnerHTML(state.html);
//
//		for (Entry<String, Connector> e: state.connectorsToIds.entrySet()) {
//			ComponentConnector cc = (ComponentConnector)e.getValue();
//
//			Element element = DOM.getElementById(e.getKey());
//			element.removeAllChildren();
//			element.appendChild(cc.getWidget().getElementForId());
//		}
//	}
