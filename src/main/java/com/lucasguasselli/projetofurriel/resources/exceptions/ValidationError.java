package com.lucasguasselli.projetofurriel.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

// esta classe vai ter todos os dados do StandardError mais uma lista de mensagens de Erro
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addError(String fieldName, String message) {
			list.add(new FieldMessage(fieldName, message));
	}


}
