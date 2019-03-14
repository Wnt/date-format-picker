package org.vaadin.jonni;

import java.time.LocalDate;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@HtmlImport("frontend://styles/shared-styles.html")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new FormattingDatePicker("default, no preset value"));
        FormattingDatePicker formattingDatePicker = new FormattingDatePicker("default, preset value now()");
        formattingDatePicker.setValue(LocalDate.now());
		add(formattingDatePicker);
        FormattingDatePicker formattingDatePicker2 = new FormattingDatePicker("two digit year, preset value now()");
        formattingDatePicker2.setValue(LocalDate.now());
        formattingDatePicker2.setFormat("dd.MM.yy");
		add(formattingDatePicker2);
        FormattingDatePicker formattingDatePicker3 = new FormattingDatePicker("Custom formatter");
        formattingDatePicker3.setValue(LocalDate.now());
        formattingDatePicker3.setFormat("E d'th of 'MMMM' in the year of 'yyyy GGGG");
		add(formattingDatePicker3);
		
		getChildren().forEach(child -> ((HasSize) child).setWidth("100%"));
		setWidth("100%");
		
    }
}
