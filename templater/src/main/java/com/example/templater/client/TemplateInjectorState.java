package com.example.templater.client;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AbstractLayoutState;

import java.util.HashMap;

public class TemplateInjectorState extends AbstractLayoutState {

    public String html;

    public HashMap<Connector, String> connectorsToIds;

}
