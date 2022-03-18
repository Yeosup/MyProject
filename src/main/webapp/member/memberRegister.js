function welcome(){
  let idToken = false
  let emailToken = false
  let phoneToken = false

  const idReg = /^[a-z0-9]{4,20}$/;
  const emailReg =/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  const regExp = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

  const id = document.getElementById('id');
  const email = document.getElementById('email');
  const phone = document.getElementById('mobile');

  id.onkeyup = () => {
    if(!idReg.test(document.getElementById('id').value)){
        document.getElementById('idError').innerText = "아이디는 영 소문자, 숫자 4~20자리로 입력해주세요."
        document.getElementById('idError').style.color = 'red'
        idToken = false;
    }else{
        const nameE = document.getElementById('idError')
        nameE.innerText = '멋진 아이디네요'
        nameE.style.color = 'green'
        idToken = true;
    }
  }

  pw.onkeyup = () => {
    if(!idReg.test(document.getElementById('pw').value)){
      document.getElementById('pwError').innerText = "비밀번호는 영 소문자, 숫자 4~20자리로 입력해주세요."
      document.getElementById('pwError').style.color = 'red'
      idToken = false;
    }else{
      const nameE = document.getElementById('pwError')
      nameE.innerText = ''
      idToken = true;
    }
  }

  pwChk.onkeyup = () => {
    if(document.getElementById('pw').value!=document.getElementById('pwChk').value){
      document.getElementById('pwChkError').innerText = "비밀번호가 일치하지 않습니다."
      document.getElementById('pwChkError').style.color = 'red'
      idToken = false;
    }else{
      const nameE = document.getElementById('pwChkError')
      nameE.innerText = ''
      idToken = true;
    }
  }

  email.onkeyup = () => {

    if(!emailReg.test(document.getElementById('email').value)){
        document.getElementById('emailError').innerText = '이메일을 다시 입력해주세요'
        document.getElementById('emailError').style.color = 'red'
        emailToken = false;
    }else{
        document.getElementById('emailError').innerText = ''
        emailToken = true;
    }
  }

  phone.onkeyup = () => {
    if(!regExp.test(document.getElementById('mobile').value)){
        document.getElementById('mobileError').innerText = '숫자만 입력해주세요.'
        document.getElementById('mobileError').style.color = 'red'
        phoneToken = false;
    }else {
        document.getElementById('mobileError').innerText = ''
        phoneToken = true;
    }
  }
  
  onsubmit=() =>{
  alert("회원가입이 완료 되었습니다.");
  }
}