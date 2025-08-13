// 전역 변수
//     let carData = [];
// 비동기 통신 저장
function fetchAPI(){
	
	if(!validateForm()){
		return;
	}
	const carBrand = document.getElementById('carBrand').value
	const carModel = document.getElementById('carModel').value
	const carVolume = document.getElementById('carVolume').value
	const carWeight = document.getElementById('carWeight').value
	const carCo2 = document.getElementById('carCo2').value
	
	//자바스크립트 객체로 저장하기. dto 클래스의 변수명과 일치하는 속성이름!!
	const data = {
		car : carBrand,
		model : carModel,
		volume : carVolume,
		weight : carWeight,
		co2 : carCo2
	}
	
	const options = {
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json'
		},
		body: JSON.stringify(data) // JS 객체를 Json 문자열로 변환
		//백엔드(서블릿)에서 받는 데이터는 json 문자열
	}
	const url = 'http://localhost:8080/jspProject/api/cars'
	// 데이터 저장 요청 보내기(비동기)
	fetch(url,options)
	.then(response => {
		if(response.ok){ //응답코드 2xx (200,201...) 이면 ok가 참
			closeModal()
			alert('데이터가 성공적으로 저장되었습니다.')
			window.location.reload()
		}else{
			throw new Error('데이터 저장 실패')
		}
		//return response.json() //json을 JS 객체로 변환
	})
	.catch(error =>{
		console.error("fetch 에러" + error)
	})
}

// 폼 submit
function submitForm(){
	//유효성 검사 통과 확인
	if(!validateForm()){
		return;	//유효성검사 결과 거짓이면 submit 종료
	}
	//form 태그 만들기
	const form = document.createElement('form')
	form.method = 'POST' // 저장은 반드시 POST
	form.action = 'CarSave'
	//name 속성 배열
	const carData = ['carBrand','carModel','carVolume','carWeight','carCo2']
	
	//모달의 모든 input 가져오기
	document.querySelectorAll('.modal-body input').forEach((input,index) =>{
		input.name = carData[index]
		form.appendChild(input)
	})
	
	document.body.appendChild(form)
	form.submit();	//form 요소의 submit() 실행
	close.summit();
	document.body.removeChild(form) // form 변수로 지정된 요소를 제거
}

 // 모달 열기
 function openModal() {
     clearForm();
     document.getElementById('carModal').classList.add('show');
 }

 // 모달 닫기
 function closeModal() {
     clearForm();
     document.getElementById('carModal').classList.remove('show');
	 clearErrors();
 }
 
 // 폼 초기화
 function clearForm() {
     document.getElementById('carBrand').value = '';
     document.getElementById('carModel').value = '';
     document.getElementById('carVolume').value = '';
     document.getElementById('carWeight').value = '';
     document.getElementById('carCo2').value = '';
     
     // 에러 상태 초기화
     // clearErrors();
 }
 
 // 에러 상태 스타일(css) 초기화
 function clearErrors() {
     const inputs = document.querySelectorAll('.form-input');
     const errors = document.querySelectorAll('.error-message');
     
     inputs.forEach(input => input.classList.remove('error'));
     errors.forEach(error => error.style.display = 'none');
 }

 // 유효성 검사
 function validateForm() {
      let isValid = true;
      clearErrors();

      const carBrand = document.getElementById('carBrand').value.trim();
      const carModel = document.getElementById('carModel').value.trim();
      const carVolume = document.getElementById('carVolume').value;
      const carWeight = document.getElementById('carWeight').value;
      const carCo2 = document.getElementById('carCo2').value;
	  
	  // 유효성 검사 내용
      // 자동차 브랜드 검사 : 빈 문자열 ❌❌
      if (!carBrand) {   // 값이 없으면 참
          showError('carBrand', 'carBrandError');
          isValid = false;
      }

      // 모델명 검사 : 빈 문자열 ❌❌
      if (!carModel) {
          showError('carModel', 'carModelError');
          isValid = false;
      }

      // 배기량 검사 :빈 문자열 ❌, \\값이 0보다 작아도 ❌
     if (!carVolume || carVolume <= 0) {
          showError('carVolume', 'carVolumeError');
          isValid = false;
      }

      // 무게 검사
      if (!carWeight || carWeight <= 0) {
          showError('carWeight', 'carWeightError');
          isValid = false;
      }

      // CO2 배출량 검사
      if (!carCo2 || carCo2 <= 0) {
          showError('carCo2', 'carCo2Error');
          isValid = false;
      }

      return isValid;
  }


