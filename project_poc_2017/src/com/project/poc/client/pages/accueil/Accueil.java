package com.project.poc.client.pages.accueil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class Accueil  extends Composite {

	  interface MyUiBinder extends UiBinder<Widget, Accueil> {}
	  private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	  @UiField ListBox listBox;

	  public Accueil(String... names) {
	    // sets listBox
	    initWidget(uiBinder.createAndBindUi(this));
	    for (String name : names) {
	      listBox.addItem(name);
	    }
	  }
	}


	