package org.iclass.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/api/cars")
public class APICarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Override
	    public void init() throws ServletException {
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // CORS 헤더 설정 (필요한 경우)
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        try {
            
            String jsonString = """ 
[    
  {
    "id": 1,
    "car": "Toyota",
    "model": "Aygo",
    "volume": 1000,
    "weight": 790,
    "co2": 99
  },
  {
    "id": 2,
    "car": "Mitsubishi",
    "model": "Space Star",
    "volume": 1200,
    "weight": 1160,
    "co2": 95
  },
  {
    "id": 3,
    "car": "Skoda",
    "model": "Citigo",
    "volume": 1000,
    "weight": 929,
    "co2": 95
  },
  {
    "id": 4,
    "car": "Mini",
    "model": "Cooper",
    "volume": 1500,
    "weight": 1140,
    "co2": 105
  },
  {
    "id": 5,
    "car": "VW",
    "model": "Up!",
    "volume": 1000,
    "weight": 929,
    "co2": 105
  },
  {
    "id": 6,
    "car": "Skoda",
    "model": "Fabia",
    "volume": 1400,
    "weight": 1109,
    "co2": 90
  },
  {
    "id": 7,
    "car": "Mercedes",
    "model": "A-Class",
    "volume": 1500,
    "weight": 1365,
    "co2": 92
  },
            		]
            		""";
            
            // JSON 응답 전송
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            out.flush();   // 출력 버퍼 비우기
            
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
