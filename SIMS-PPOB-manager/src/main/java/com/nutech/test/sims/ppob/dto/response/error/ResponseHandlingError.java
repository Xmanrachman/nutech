package com.nutech.test.sims.ppob.dto.response.error;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.nutech.test.sims.ppob.response.ResponseGeneral;

@Component
public class ResponseHandlingError {

	public ResponseGeneral errorUnAuthoritationExpired() {

		ResponseGeneral response = new ResponseGeneral();

		String data = "null";
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.setMessage("Token tidak valid atau kadaluwarsa");
		response.setData(data);

		return response;
	}

	public ResponseGeneral errorUnAuthorized() {

		ResponseGeneral response = new ResponseGeneral();

		String data = "null";
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.setMessage("Username atau password salah");
		response.setData(data);

		return response;
	}

	public ResponseGeneral errorBadRequest() {

		ResponseGeneral response = new ResponseGeneral();

		Object data = new Object();
		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Data tidak sesuai");
		response.setData(data);

		return response;
	}

	public ResponseGeneral errorMatcheLengthPassword() {

		ResponseGeneral response = new ResponseGeneral();

		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Length password minimal 8 karakter");
		response.setData("null");

		return response;
	}

	public ResponseGeneral errorMatcheLengthEmail() {

		ResponseGeneral response = new ResponseGeneral();

		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Paramter email tidak sesuai format");
		response.setData("null");

		return response;

	}

	public ResponseGeneral errorNotFound() {
		ResponseGeneral response = new ResponseGeneral();

		response.setStatus(HttpStatus.SC_NOT_FOUND);
		response.setMessage("User Not Found");
		response.setData("null");

		return response;

	}
	
	public ResponseGeneral errorBadRequestAmount() {
		ResponseGeneral response = new ResponseGeneral();

		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 0");
		response.setData("null");

		return response;
	}
	
	public ResponseGeneral errorBadRequestService() {

		ResponseGeneral response = new ResponseGeneral();

		Object data = new Object();
		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Service ataus Layanan tidak ditemukan");
		response.setData(data);

		return response;
	}
	
	public ResponseGeneral errorFormatIamge() {
		
		ResponseGeneral response = new ResponseGeneral();

		Object data = new Object();
		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setMessage("Format Image tidak sesuai");
		response.setData(data);

		return response;
		
	}

}
