function checkID(obj) {
  var emailPattern = /^[w-.]+@([w-]+.)+[w-]{2,4}$/;
  if (obj.value !== "" && obj.value.length >= 6) {
    if (obj.id === "id" && emailPattern.test(obj.value)) {
      return true;
    } else return false;
  } else return false;
}

function checkPW(obj) {
  if (obj.value !== "" && obj.value.length >= 8) {
    if (obj.id === "pw" && /[a-zA-Z0-9!@#$%^&*(),.?":{}|<>]/g.test(obj.value)) {
      return true;
    } else return false;
  } else return false;
}

function checkAll() {
  var id = document.getElementById("id");
  var pw = document.getElementById("pw");
  if (!checkID(id)) {
    alert("이메일 형식이 올바르지 않습니다.");
    id.focus();
    return false;
  } else if (!checkPW(pw)) {
    alert(
      "비밀번호는 영문 대소문자, 숫자, 특수문자 중 최소 1개 이상을 포함해야합니다."
    );
    pw.focus();
    return false;
  }
  return true;
}
