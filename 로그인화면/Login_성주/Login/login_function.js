function checkFields(obj) {
  if (obj.value === "" || obj.value.length < 8) {
    return false;
  } else if (obj.id === "pw" && !/[!@#$%^&*(),.?":{}|<>]/g.test(obj.value)) {
    return false;
  } else if (obj.id === "id" && !/^[a-zA-Z0-9]+$/.test(obj.value)) {
    return false;
  } else {
    return true;
  }
}
// ID / PW의 입력 필드 값이 8자 이상,공백 미포함,ID - 알파벳과 숫자 | PW - 최소 하나 이상의 특수문자 추가 조건을 충족하면 true.

function validateLoginForm() {
  var id = document.getElementById("id");
  var pw = document.getElementById("pw");
  var errorElem = document.getElementById("error");

  if (!checkFields(id) || !checkFields(pw)) {
    errorElem.innerHTML = "ID 혹은 PW를 잘못 입력하셨습니다.";
    errorElem.style.color = "red";
    return false;
  }

  return true;
}
// 위 checkFileds 함수를 불러와 ID/PW 필드가 모두 조건을 충족하는지 검사하고, 통과하지 못할 경우 errorMessage(false)에 메시지 출력,
// 통과할 경우 로그인 버튼이 유저 정보를 전송합니다.
