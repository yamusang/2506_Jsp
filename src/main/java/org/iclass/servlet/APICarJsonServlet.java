package org.iclass.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.iclass.dao.CarCO2Dao;
import org.iclass.dto.CarDto;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/cars")
public class APICarJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	    public void init() throws ServletException {
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // CORS 헤더 설정 (필요한 경우)
        response.setHeader("Access-Control-Allow-Origin", "*"); //CORS?
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        try {
            CarCO2Dao dao = new CarCO2Dao();
        	List<CarDto> list = dao.getAllCars();
        	Gson gson = new Gson();
        	//list 자바 객체를 json 문자열(String 타입)로 바꾸기
        	String jsonString = gson.toJson(list);
        	
        	PrintWriter out = response.getWriter();
        	out.print(jsonString);
        	out.flush();
            
        } catch (Exception e) {
            // 에러 처리
        	// 오류 코드 500
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter out = response.getWriter();
            out.print("{\"error\": \"데이터 조회 중 오류가 발생했습니다.\"}");
            out.flush();
            e.printStackTrace();
        }
	}
// 요청 json 문자열을 dto 객체로 변환하여 dao 메소드 실행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//		문자열 받아오기
		StringBuilder jsonBuffer = new StringBuilder();
		//문자 기반 입력 스트림 : request로부터 입력 받기
		BufferedReader reader = request.getReader();
		String line;
		while((line = reader.readLine())!=null) {//json 문자열 라인단위 읽기
			jsonBuffer.append(line);
		}
		System.out.println("json 문자열 :" + jsonBuffer.toString());
		//Gson : json 문자열을 dto 객체로 변환
		Gson gson = new Gson();
		CarDto dto = gson.fromJson(jsonBuffer.toString(), CarDto.class);
		//dao
		CarCO2Dao dao = new CarCO2Dao();
		if(dao.insert(dto)==1) {
			response.setStatus(HttpServletResponse.SC_CREATED);//201응답(저장)
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500
		}
		
	}

}
