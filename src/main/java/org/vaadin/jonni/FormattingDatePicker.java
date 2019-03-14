package org.vaadin.jonni;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Input;

public class FormattingDatePicker extends CustomField<LocalDate> implements HasStyle {
	private DatePicker datePicker;
	private String format = "dd.MM.yyyy";
	private Input formattedDate;

	public FormattingDatePicker(String label) {
		this();
		setLabel(label);
	}

	public FormattingDatePicker() {
		addClassName("formatting-date-picker");

		formattedDate = new Input();

		formattedDate.getElement().addEventListener("click", click -> datePicker.open());
		formattedDate.getElement().setAttribute("slot", "prefix");

		datePicker = new DatePicker();
		datePicker.addClassName("hide-input");
		datePicker.getElement().appendChild(formattedDate.getElement());
		addValueChangeListener(change -> updateFormattedDate());
		add(datePicker);
		datePicker.setWidthFull();
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		// for some reason formattedDate.setReadOnly(true); does not add the readonly
		// attribute into the dom, so we set it via javascript instead
		formattedDate.getElement().executeJavaScript("this.setAttribute('readonly', true);");
	}

	private void updateFormattedDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getFormat());
		String formattedString = datePicker.getValue().format(formatter);
		formattedDate.setValue(formattedString);
	}

	@Override
	protected LocalDate generateModelValue() {
		return datePicker.getValue();
	}

	@Override
	protected void setPresentationValue(LocalDate newPresentationValue) {
		datePicker.setValue(newPresentationValue);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		updateFormattedDate();
	}

}
