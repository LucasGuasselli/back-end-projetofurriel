package com.lucasguasselli.projetofurriel.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String str){
		try{
			return URLDecoder.decode(str, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			return "";
		}
	}

	// este metodo tem por finalidade pegar o string (varios numeros separados por virgula)
	//e quebrar em varios pedacos e converter em numeros para acicionar na lista
	public static List<Integer> decodeIntList(String str){
		String[] vet = str.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
			//esta linha faz a mesma coisa que as 6 anteriores
			//Arrays.asList - converte o vetor para lista, stream() e map servem para converter o string em int e o collect para adicionar no array
			//return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
			return list;
			
	}
}
